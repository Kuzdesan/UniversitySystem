import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception {

        Faculty IFTEB = new Faculty("IFTEB");
        Faculty ICIS = new Faculty("ICIS");
        Group C21_712 = new Group("C21-712", "Information and analytical security systems");
        Group B21_505 = new Group("B21-505", "Information Security");
        Group M22_507 = new Group("M22-507", "Cryptography");
        ICIS.add_groups(B21_505, M22_507);
        IFTEB.add_groups(C21_712);

        Student Tom = new Student("Tom", "25.05.2001", "120", C21_712);
        Student Ann = new Student("Ann", "30.01.2002", "890", C21_712);
        Student Katy = new Student("Katy", "25.03.2001", "093", C21_712);
        Student Liz = new Student("Liza", "25.01.2001", "123", B21_505);
        Student Den = new Student("Den", "14.07.2001", "198", B21_505);
        Student Nick = new Student("Nick", "28.08.2001", "945", B21_505);
        Student Mary = new Student("Mary", "07.10.2001", "308", B21_505);

        Subject literature = new Subject("literature", 15);
        Subject fizika = new Subject("fizika", 45);
        Subject math = new Subject("math", 60);
        Subject info = new Subject("info", 50);
        Subject english = new Subject("english", 30);
        Subject chemistry = new Subject("chemistry", 37);
        Subject economy = new Subject("economy", 25);
        Subject prog = new Subject("prog", 80);


//                                  TESTING EXCEPTIONS

//      1. ------if it is "==" in Student(28) instead of "equals" it works incorrectly--------------

//        Student Tom = new Student("Harry", "25.05.2001",new String("test"), C21_712);
//        Student Ann = new Student("Una", "30.01.2002", "test", C21_712);
//        System.out.println("current Storage");
//        System.out.println( Storage.getAll_students().get(0).name + " " +  Storage.getAll_students().get(1).name);
//        System.out.println("equality for .equal() " + Harry.getPassport().equals(Una.getPassport()));
//        System.out.println("equality for == " + (Harry.getPassport() == Una.getPassport()));
//        System.out.println("Removing Una");
//        Ann.deduct_student();
//        System.out.println("current Storage");
//        System.out.println(Storage.getAll_students().get(0).name);

//      2.-------------Student.change_group--------------

//        Tom.change_group(C21_712);
//            or
//          Tom.change_group(B21_505);
//
//      3.------------Student.leave_group---------------

//        Tom.leave_group();
//        Tom.leave_group();
//          or
//        C21_712.delete_students(Tom);
//        Tom.leave_group();
//          or
//        Tom.deduct_student();
//        Tom.leave_group();

//      4.---------Student.add_subject-----------

//        Tom.leave_group();
//        Tom.add_subjects(math);
//          or
//        Tom.add_subjects(math, chemistry);
//        Tom.add_subjects(chemistry);
//          or
//        C21_712.add_subjects_(math, literature);
//        Tom.add_subjects(math);
//          or
//        C21_712.add_subjects_(math, chemistry);
//        B21_505.add_subjects_(literature, fizika, prog);
//        Tom.leave_group();
//        Tom.change_group(B21_505);
//        Tom.add_subjects(fizika);
//        Tom.see_progress();


//      5.-------------Student.delete_subjects----------------
//        Tom.add_subjects(fizika, english, literature);
//        Tom.leave_group();
//        Tom.delete_subject(english);
//          or
//        B21_505.add_subjects_(math, literature, chemistry);
//        C21_712.add_subjects_(literature, fizika, economy);
//        C21_712.delete_students(Tom);
//        Tom.change_group(B21_505);
//        Tom.delete_subject(fizika);
//          or
//        C21_712.add_subjects_(math, literature, chemistry);
//        C21_712.delete_subject(literature);
//        Tom.delete_subject(literature);

//      6.----------Student.add_mark-----------

//        Tom.add_subjects(math, fizika, literature);
//        Tom.add_mark("literature", 8);
//          or
//          Tom.add_subjects(math, fizika, literature);
//          Tom.add_mark("literature", 4, 5, 3, -2);
//           or
//        C21_712.add_subjects_(math, literature, economy);
//        Tom.add_mark("fizika", 5);
//           or
//        C21_712.add_subjects_(literature, economy);
//        B21_505.add_subjects_(fizika, math, english);
//        C21_712.delete_students(Tom);
//        B21_505.add_students(Tom);
//        Tom.add_mark("literature", 4);

//      7. --------Student.delete_mark---------

//        Tom.add_subjects(math, fizika, literature);
//        Tom.add_mark(literature.name, 4);
//        Tom.delete_mark(literature.name, 3);
//            or
//        C21_712.add_subjects_(fizika, math, chemistry);
//        Tom.delete_mark(literature.name, 3);

//      8. -------Group = new Group---------

//        Group Some = new Group(new String("B22-015"), "testing");
//        Group Any = new Group("B22-015", "checking");
//
//      9. -----------Group.add_subjects_--------------

//        C21_712.add_subjects_(math, literature, fizika);
//        C21_712.add_subjects_(fizika, literature);
//          or
//        C21_712.add_subjects_(fizika, economy, economy);

//      10.----------Group.add_students------------

//        B21_505.add_students(Tom);
//          or
//        B21_505.add_students(new Student("Ann", "30.01.2002", "891", C21_712));
//          or
//        C21_712.add_students(Tom);

//        11.----------Group.delete_students-----------

//        B21_505.delete_students(Tom);
//          or
//        Tom.deduct_student();
//        C21_712.delete_students(Tom);
//          or
//        C21_712.delete_students(new Student("Some person", "01.01.2001", "098", B21_505));

//        12.-----------Group.delete_subject------------------

//        C21_712.add_subjects_(math, literature, economy);
//        C21_712.delete_subject(fizika);
//
//        13.-----------Faculty = new Faculty--------------

//        Faculty Some = new Faculty(new String("test"));
//        Faculty Any = new Faculty("test");

//        14.-------------Faculty.add_groups------------

//        IFTEB.add_groups(B21_505);
//          or
//        IFTEB.add_groups(C21_712);


//                                      SOME METHODS
//        1.
//        Tom.add_subjects(math, chemistry, literature, economy);
//        Tom.add_mark(math.name, 3, 4 ,5, 2);
//        Tom.add_mark(chemistry.name, 2, 3, 1, 0, 4);
//        Tom.add_mark(economy.name, 3, 2, 1, 5);
//        Tom.see_progress();

//        2.
//        Tom.add_subjects(math, chemistry, literature, economy);
//        Tom.add_mark(math.name, 3, 4, 5, 2);
//        Tom.add_mark(chemistry.name, 2, 3, 1, 0, 4);
//        Tom.add_mark(economy.name, 3, 2, 1, 5);
//        Tom.see_progress();
//        Tom.delete_subject(chemistry);
//        Tom.see_progress();

//        3.
//        C21_712.add_subjects_(math, literature, fizika);
//        Tom.add_mark(math.name, 5, 3, 2, 5);
//        Tom.add_mark(literature.name, 4, 2, 1, 0 ,3);
//        Tom.see_progress();
//        C21_712.delete_subject(literature);
//        Tom.see_progress();

//        4. Если в групповые предметы добавляется предмет, который уже есть у студента этой группы, то у студента этот предмет и
//        оценки по нему сохраняются в том виде, какой был до добавления предмета в группу
//        Tom.add_subjects(math, literature, english, chemistry);
//        Tom.add_mark(math.name, 5, 3, 2, 5);
//        Tom.add_mark(literature.name, 4, 2, 1, 0 ,3);
//        Tom.add_mark(chemistry.name, 1, 4, 3, 2);
//        Tom.see_progress();
//        C21_712.add_subjects_(economy, math);
//        Tom.see_progress();

//        5.
//        C21_712.info();
//        C21_712.delete_students(Ann);
//        C21_712.info();
//        Mary.leave_group();
//        Liz.leave_group();
//        C21_712.add_students(Mary, Liz);
//        C21_712.info();
//        Den.leave_group();
//        Den.change_group(C21_712);
//        C21_712.info();
//        Tom.deduct_student();
//        C21_712.info();

//        6.
//        C21_712.add_subjects_(math, economy, fizika);
//        C21_712.info();
//        C21_712.delete_subject(fizika);
//        C21_712.info();

//        7.
//        Tom.see_progress();
//        Tom.student_info();
//        Tom.add_subjects(math, economy, english);
//        Tom.see_progress();
//        Tom.student_info();
//        Tom.add_mark(math.name, 3, 4, 2);
//        Tom.add_mark(english.name, 1, 0, 3, 4);
//        Tom.see_progress();
//        Tom.student_info();
//        Tom.add_mark(economy.name, 1 ,2 ,3, 5);
//        Tom.see_progress();
//        Tom.student_info();
//        Tom.add_mark(math.name, 1, 0, 1);
//        Tom.see_progress();
//        Tom.student_info();
//        C21_712.add_subjects_(literature, fizika);
//        Tom.see_progress();
//        Tom.student_info();
//        Tom.add_mark(literature.name, 3, 4 ,2);
//        Tom.see_progress();
//        Tom.student_info();
//        Tom.delete_mark(english.name, 4);
//        Tom.see_progress();
//        Tom.student_info();
//        Tom.delete_subject(english);
//        Tom.see_progress();
//        Tom.student_info();
//        C21_712.delete_subject(literature);
//        Tom.see_progress();
//        Tom.student_info();
//        Tom.leave_group();
//        Tom.see_progress();
//        Tom.student_info();
//        B21_505.add_subjects_(chemistry, info, prog);
//        Tom.change_group(B21_505);
//        Tom.see_progress();
//        Tom.student_info();
//        B21_505.delete_students(Tom);
//        Tom.see_progress();
//        Tom.student_info();
//        Tom.deduct_student();
//        Tom.see_progress();
//        Tom.student_info();

//        8.
//        ICIS.show_groups_and_students();
//        ICIS.faculty_info();
//
//        C21_712.delete_students(Ann, Tom);
//        M22_507.add_students(Ann, Tom);
//        ICIS.show_groups_and_students();
//        ICIS.faculty_info();
//
//        Liz.leave_group();
//        ICIS.show_groups_and_students();
//        ICIS.faculty_info();
//
//        B21_505.delete_students(Mary, Nick);
//        ICIS.show_groups_and_students();
//        ICIS.faculty_info();
//
//        Nick.change_group(M22_507);
//        ICIS.show_groups_and_students();
//        ICIS.faculty_info();
//
//        Tom.deduct_student();
//        ICIS.show_groups_and_students();
//        ICIS.faculty_info();
    }
}