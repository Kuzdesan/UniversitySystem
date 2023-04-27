import java.util.ArrayList;
import java.util.LinkedList;


public class Group {
    String name;
    String speciality;
    Faculty faculty;
    public ArrayList<Student> students = new ArrayList<>();
    int Number_of_Students;
    ArrayList<Subject> subjects = new ArrayList<>();


    public Group(String group_name, String group_speciality) throws MyException {
        name = group_name;
        speciality = group_speciality;
        for (Group group : Storage.getAll_groups()) {
            if (group.name.equals(this.name)) {
                throw new MyException("Group with such data is already registered in system.");
            }
        }
        Storage.setAll_groups(this);
    }

    //добавление предметов группы
    public void add_subjects_(Subject... sub) throws Exception {
        for (int i = 0; i < sub.length; i++) {
            if (subjects.contains(sub[i])) {
                throw new MyException(sub[i].name + "; " + sub[i].hours + " hours is already added to " + this.name + " group");
            }
            subjects.add(sub[i]);
            for (Student stu : students) {
                if (stu.subjects.contains(sub[i])) {
                    continue;
                }
                stu.add_subjects(sub[i]);
            }
        }
    }

    //добавление студентов в группу
    public void add_students(Student... student_new) throws Exception {
        for (Student stu : student_new) {
            if (stu.group != null) {
                throw new MyException("Student " + stu.name + " is still in the group " + stu.group.name + ". To add student, delete him/her from his/her current group.");
            }
            students.add(stu);
            stu.group = this;
            for (Subject subject : subjects) {
                stu.add_subjects(subject);
            }
        }
        count_students();
    }

    //удаление студентов из группы
    public void delete_students(Student... stu) throws MyException {
        for (Student value : stu) {
            if (value.group != this) {
                throw new MyException(value.name + " isn't from " + this.name + " group. Impossible to delete student.");
            }
            students.remove(value);
            value.group = null;
            value.delete_all_subjects();
        }
        count_students();
    }

    //удаление предмета группы
    public void delete_subject(Subject sub) throws MyException {
        if (subjects.contains(sub)) {
            this.subjects.remove(sub);
            for (Student stu : students) {
                stu.delete_subject(sub);
            }
        } else {
            throw new MyException("group " + this.name + " doesn't have subject " + sub.name + ".");
        }
    }

    public void count_students() {
        Number_of_Students = students.size();
    }

    public void info() {
        System.out.println("------------------------------\nThe information about the group:");
        System.out.printf("the group name: %s\nthe group speciality: %s\nthe group faculty: %s\nthe number of students: %s\nthe group list: \n",
                name, speciality, faculty.name, Number_of_Students);
        int i = 0;
        for (Student stu : students) {
            System.out.println(" " + (++i) + ". " + stu.name);
        }
        System.out.println("The group subjects are : ");
        int j = 0;
        for (Subject sub : subjects) {
            System.out.println(" " + (++j) + ". " + sub.name);
        }
        System.out.println("\n------------------------------\n");
    }
}
