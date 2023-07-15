package tracker;

public class Student {
    private static int currentId = 10_000;
    private final String studentId;
    private final String name;
    private final String surname;
    private final String email;

    // courses:
    private int java = 0;
    private int dsa = 0;
    private int database = 0;
    private int spring = 0;

    public Student(String name, String surname, String email) {
        this.studentId = String.valueOf(currentId++);
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public int getPoints(String courseName) {
        return switch (courseName.toLowerCase()) {
            case "java" -> java;
            case "dsa" -> dsa;
            case "databases" -> database;
            case "spring" -> spring;
            default -> -1;
        };
    }

    public String getStudentId() {
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

    public void addJava(int java) {
        this.java += java;
    }

    public void addDsa(int dsa) {
        this.dsa += dsa;
    }

    public void addDatabase(int database) {
        this.database += database;
    }

    public void addSpring(int spring) {
        this.spring += spring;
    }

    @Override
    public String toString() {
        return String.format("%s points: Java=%d; DSA=%d; Databases=%d; Spring=%d"
        ,studentId, java, dsa, database, spring);
    }

    public int getJava() {
        return java;
    }

    public int getDsa() {
        return dsa;
    }

    public int getDatabase() {
        return database;
    }

    public int getSpring() {
        return spring;
    }
}