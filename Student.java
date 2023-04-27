import java.io.IOException;
import java.util.LinkedList;

public class Student {
    String name;
    String date_of_birth;
    private String passport;
    Group group;
    LinkedList<Subject> subjects = new LinkedList<>();
    LinkedList<Pair<String, LinkedList<Integer>>> subjects_and_marks = new LinkedList<Pair<String, LinkedList<Integer>>>();
    LinkedList<Pair<String, Double>> list_of_all_subject_gpa = new LinkedList<>();
    double the_result_gpa;

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public boolean equals (Student student) {
        if (student.passport.equals(this.passport)) {
            return true;
        } else return false;

    }

    public Student(String Students_name, String Student_birth, String Student_passport, Group Student_group) throws MyException {
        this.name = Students_name;
        this.date_of_birth = Student_birth;
        this.passport = Student_passport;
        this.group = Student_group;

        for (Student stu : Storage.getAll_students()){
            if (stu.equals(this)){
                throw new MyException("Student with such passport data is already registered in system.");
            }
        }
        Storage.setAll_students(this);
        this.group.students.add(this);
        this.group.count_students();
    }


    public void change_group(Group new_group) throws Exception {
        if (new_group.equals(this.group)){
            throw new MyException("Student " + this.name + " is already in this group " + new_group.name);
        }
        this.delete_all_subjects();
        new_group.add_students(this);
        this.group = new_group;
    }

    public void leave_group() throws MyException {
        if (this.group != null) {
            group.students.remove(this);
            this.group = null;
            delete_all_subjects();
        } else {
            throw new MyException(name + " does not have group. Student can't leave group.");
        }
    }

    //добавление предметов
    public void add_subjects(Subject... sub) throws MyException {
        if (this.group == null) {
            throw new MyException("Student " + this.name + " has no group, impossible add subjects.");
        }
        for (int i = 0; i < sub.length; i++) {
            if (subjects.contains(sub[i])) {
                throw new MyException("The subject has been already added to student");
            } else {
                subjects.add(sub[i]);
                Pair<String, LinkedList<Integer>> pair = new Pair<String, LinkedList<Integer>>(sub[i].name, new LinkedList<Integer>());
                subjects_and_marks.add(pair);
                Pair<String, Double> pair2 = new Pair<String, Double>(sub[i].name, 0.0);
                list_of_all_subject_gpa.add(pair2);
            }
        }
    }

    //отчислить студента
    public void deduct_student() throws MyException {
        if (this.group != null) {
            this.group.delete_students(this);
        }
        for (int i = 0; i < Storage.getAll_students().size(); i++) {
            if (Storage.getAll_students().get(i).passport.equals(this.passport)) {
                Storage.getAll_students().remove(i);
            }
        }
    }

    //удаление всех предметов
    public void delete_all_subjects() {
        subjects.clear();
        subjects_and_marks.clear();
        list_of_all_subject_gpa.clear();
        count_subject_GPA();
    }

    //удаление одного предмета
    public void delete_subject(Subject sub) throws MyException {
        if (subjects.contains(sub)) {
            this.subjects.remove(sub);
        } else {
            throw new MyException("Student " + this.name + " doesn't study " + sub.name);
        }

        for (int i =0; i<list_of_all_subject_gpa.size(); i++){
            if (list_of_all_subject_gpa.get(i).first.equals(sub.name)) list_of_all_subject_gpa.remove(i);}
        for (int j =0; j<subjects_and_marks.size(); j++){
            if (subjects_and_marks.get(j).first.equals(sub.name)) subjects_and_marks.remove(j);
        }
        count_subject_GPA();
    }

    //добавление оценки
    public void add_mark(String sub_name, int... mark) throws MyException {
        int temp = 0;
        for (Pair<String, LinkedList<Integer>> pair : subjects_and_marks) {
            if (pair.first.equals(sub_name)) {
                temp++;
                for (int i : mark) {
                    if (i<0 | i>5){
                        throw new MyException("The mark " + i +" is out of range");
                    }
                    pair.second.add(i);
                    count_subject_GPA();
                }
                break;
            }
        }
        if (temp == 0) {
            throw new MyException("Subject " + sub_name + " hasn't been found");
        }
    }

    //удаление оценки
    public void delete_mark(String sub_name, int mark) throws MyException {
        int temp = 0;
        for (Pair<String, LinkedList<Integer>> pair : subjects_and_marks) {
            if (pair.first.equals(sub_name)) {
                temp++;
                if (pair.second.contains(mark)) {
                    pair.second.removeFirstOccurrence(mark);
                } else {
                    throw new MyException("Such mark hasn't been found");
                }
                break;
            }
        }
        if (temp == 0) {
            throw new MyException("Subject " + sub_name + " hasn't been found");
        }
        count_subject_GPA();
    }

    //подсчет среднего балла по предметам
    public void count_subject_GPA() {
        for (Pair<String, Double> pair : list_of_all_subject_gpa) {
            double sum = 0;
            for (Pair<String, LinkedList<Integer>> pair2 : subjects_and_marks) {
                if (pair.first.equals(pair2.first)) {
                    for (int i = 0; i < pair2.second.size(); i++) sum += pair2.second.get(i);
                    if (pair2.second.size() != 0) {
                        sum = sum / pair2.second.size();
                        pair.second = sum;
                    } else {
                        pair.second = sum;
                    }
                }
            }
        }
        count_the_result_gpa();
    }

    //подсчет итогового среднего балла по всем предметам
    public void count_the_result_gpa() {
        double res = 0;
        int temp = 0;
        if (list_of_all_subject_gpa.size() != 0) {
            for (Pair<String, Double> pair : list_of_all_subject_gpa) {
                if (pair.second != 0) {
                    temp++;
                    res += pair.second;
                }
            }
        } else {
            temp = 1;
        }
        the_result_gpa = res / temp;
    }

    //успеваемость студента
    public void see_progress() {
        int a = list_of_all_subject_gpa.size();
        System.out.println("------------------------------\n" + this.name + "'s study progress: ");
        for (int i = 0; i < a; i++) {
            String gpa = String.format("%.2f", list_of_all_subject_gpa.get(i).second);
            System.out.println("subject: " + list_of_all_subject_gpa.get(i).first +
                    ";      marks: " + subjects_and_marks.get(i).second.toString() + ";      GPA: " + gpa);
        }
        String res_GPA = String.format("%.2f", the_result_gpa);
        System.out.println("Final GPA is: " + res_GPA + "\n------------------------------\n");
    }

    //основная информация о студенте
    public void student_info() {
        System.out.println("Information about the student:");
        String res_GPA = String.format("%.2f", the_result_gpa);
        if (this.group != null) {
            System.out.printf("1.Full name: %s\n2.Date of Birth: %s\n3.The group: %s\n4.The speciality: %s\n5.The GPA: %s\n",
                    name, date_of_birth, group.name, group.speciality, res_GPA);
        } else {
            System.out.printf("1.Full name: %s\n2.Date of Birth: %s\n3.The group: %s\n4.The speciality: %s\n5.The GPA: %s\n",
                    name, date_of_birth, "no group", "no speciality", res_GPA);
        }
        System.out.println("5.Subjects: ");
        for (int i = 0; i < subjects.size(); i++) {
            String gpa = String.format("%.2f", list_of_all_subject_gpa.get(i).second);
            System.out.println((i + 1) + ". " + subjects.get(i).name + "; hours: " + subjects.get(i).hours +
                    "; the GPA is: " + gpa);
        }
        System.out.println("\n------------------------------\n");
    }

    static class Pair<A, B> {
        public final A first;
        public B second;

        public Pair(final A first, B second) {
            this.first = first;
            this.second = second;
        }
    }
}

