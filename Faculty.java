import java.util.ArrayList;

public class Faculty {
    String name;
    ArrayList<Group> groups = new ArrayList<>();
    int Number_of_Groups;
    int Number_of_all_students;


    public Faculty(String Faculty_Name) throws MyException {
        this.name = Faculty_Name;
        for (int i = 0; i < Storage.getAll_faculties().size(); i++) {
            if (Storage.getAll_faculties().get(i).name.equals(this.name)) {
                throw new MyException("Faculty " + this.name + " has been already created.");
            }
        }
        Storage.setAll_faculties(this);
    }

    //добавление группы на факультет
    public void add_groups(Group... group) throws MyException {
        for (Group gr : group) {
            if (gr.faculty != null) {
                throw new MyException(gr.name + " group is already contained in the faculty " + gr.faculty.name);
            }
            groups.add(gr);
            gr.faculty = this;
            Number_of_Groups = groups.size();
        }
    }

    //показ групп и состава групп факультета
    public void show_groups_and_students() {
        int num = 0;
        System.out.println("------------------------------\nThe information about " + this.name + "'s groups and students:\n");
        for (int i = 0; i < groups.size(); i++) {
            System.out.println((i + 1) + "st Group is: " + groups.get(i).name);
            for (int j = 0; j < groups.get(i).students.size(); j++) {
                num++;
                System.out.println("  " + (i + 1) + "." + (j + 1) + " " + groups.get(i).students.get(j).name);
            }
        }
        System.out.println("------------------------------\n");
        Number_of_all_students = num;
    }

    //основная информация о факультете
    public void faculty_info() {
        System.out.println("------------------------------\nThe information about the faculty:");
        System.out.printf("the faculty name: %s\nthe number of groups: %s\nthe number of all students: %s\n",
                this.name, this.Number_of_Groups, this.Number_of_all_students);
        System.out.println("\n------------------------------\n");
    }
}
