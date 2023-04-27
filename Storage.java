import java.util.LinkedList;

public class Storage {
    private static LinkedList<Student> all_students = new LinkedList<>();
    private static LinkedList<Group> all_groups = new LinkedList<>();
    private static LinkedList<Faculty> all_faculties = new LinkedList<>();

    public static LinkedList<Student> getAll_students() {
        return all_students;
    }

    public static void setAll_students(Student stu) {
        all_students.add(stu);
    }

    public static LinkedList<Group> getAll_groups() {
        return all_groups;
    }

    public static void setAll_groups(Group gru) {
        all_groups.add(gru);
    }

    public static LinkedList<Faculty> getAll_faculties() {
        return all_faculties;
    }

    public static void setAll_faculties(Faculty fuculty) {
        all_faculties.add(fuculty);
    }
}
