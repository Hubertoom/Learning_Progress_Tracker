package tracker;

public class Student {
    private static int currentId = 10_000;
    private final int studentId;
    private final String name;
    private final String surname;
    private final String email;

    public Student(String name, String surname, String email) {
        this.studentId = currentId++;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }
}
