package tracker;

import tracker.courses.*;

import java.util.*;

public class LearningTracker {
    private final Map<String, Student> studentsList;
    private final Scanner scanner = new Scanner(System.in);
    private final Statistics statistics;
    private final Course javaCourse = new Course("Java", 600);
    private final Course springCourse = new Course("Spring", 550);
    private final Course dsaCourse = new Course("DSA", 400);
    private final Course databaseCourse = new Course("Databases", 480);
    private final Map<String, Course> coursesList;


    public LearningTracker() {
        this.studentsList = new LinkedHashMap<>();
        this.coursesList = new HashMap<>();
        coursesList.put(javaCourse.getCourseName(), javaCourse);
        coursesList.put(springCourse.getCourseName(), springCourse);
        coursesList.put(dsaCourse.getCourseName(), dsaCourse);
        coursesList.put(databaseCourse.getCourseName(), databaseCourse);

        statistics = new Statistics(studentsList, coursesList);
    }


    public void run() {
        System.out.println("Learning Progress Tracker");

        while (true) {
            String input = scanner.nextLine();
            if (input.isBlank()) {
                System.out.println("No input.");
                continue;
            }

            switch (input) {
                case "add students" -> addStudents();
                case "list" -> displayStudentsList();
                case "add points" -> addPoints();
                case "find" -> findStudent();
                case "statistics" -> getStatistics();
                case "exit" -> {
                    System.out.println("Bye");
                    return;
                }
                case "back" -> System.out.println("Enter 'exit' to exit the program.");
                default -> System.out.println("Error: unknown command!");
            }

        }

    }

    private void getStatistics() {
        System.out.println("Type the name of a course to see details or 'back' to quit:");
        System.out.println(
                "Most popular: " + statistics.getMostPopular() + "\n" +
                        "Least popular: " + statistics.getLeastPopular() + "\n" +
                        "Highest activity: " + statistics.getHighestActivity() + "\n" +
                        "Lowest activity: " + statistics.getLowestActivity() + "\n" +
                        "Easiest course: " + statistics.getEasiestCourse() + "\n" +
                        "Hardest course: " + statistics.getHardestCourse()
        );

        while (true) {
            String userChoice = scanner.nextLine();
            if (userChoice.equals("back")) {
                return;
            }
            if (!coursesList.containsKey(userChoice)) {
                System.out.println("Unknown course.");
                continue;
            }
            statistics.getStatisticsForCourse(userChoice);
        }
    }

    private void findStudent() {
        System.out.println("Enter an id or 'back' to return");
        while (true) {
            String id = scanner.nextLine();
            if (id.equals("back")) {
                return;
            }

            if (!studentsList.containsKey(id)) {
                System.out.printf("No student is found for id=%s\n", id);
                return;
            }

            System.out.println(studentsList.get(id));
        }
    }

    private void addPoints() {
        System.out.println("Enter an id and points or 'back' to return:");
        while (true) {
            String input = scanner.nextLine();
            String[] command = input.split("\\s+");

            if (command[0].equals("back")) {
                break;
            }

            if (command.length != 5 || input.contains("-")) {
                System.out.println("Incorrect points format");
                continue;
            }

            int[] points;
            try {
                points = Arrays.stream(command).skip(1)
                        .mapToInt(Integer::parseInt)
                        .toArray();
            } catch (NumberFormatException e) {
                System.out.println("Incorrect points format");
                continue;
            }

            Optional<Student> studentOptional = studentsList.values()
                    .stream().filter(student -> Objects.equals(student.getStudentId(), command[0]))
                    .findAny();

            if (studentOptional.isEmpty()) {
                System.out.printf("No student is found for id=%s\n.", command[0]);
                continue;
            }

            updatePoints(studentOptional.get(), points[0], points[1], points[2], points[3]);
        }

    }

    private void updatePoints(Student student, int java, int dsa, int database, int spring) {
        student.addJava(java);
        javaCourse.updatePoints(student, java);

        student.addDsa(dsa);
        dsaCourse.updatePoints(student, dsa);

        student.addDatabase(database);
        databaseCourse.updatePoints(student, database);

        student.addSpring(spring);
        springCourse.updatePoints(student, spring);

        System.out.println("Points updated");
    }

    private void displayStudentsList() {
        if (studentsList.isEmpty()) {
            System.out.println("No students found");
            return;
        }
        System.out.println("Students:");
        studentsList.values().stream()
                .map(Student::getStudentId)
                .forEach(System.out::println);
    }

    private void addStudents() {
        int counter = 0;
        System.out.println("Enter student credentials or 'back' to return");

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("back")) {
                System.out.printf("Total %d students have been added.\n", counter);
                return;
            }

            Optional<Student> studentOptional = Validator.validate(input);

            if (studentOptional.isPresent()) {
                Student student = studentOptional.get();

                if (studentsList.values().stream()
                        .anyMatch(s -> s.getEmail().equals(student.getEmail()))
                ) {
                    System.out.println("This email is already taken.");
                } else {
                    studentsList.put(student.getStudentId(), student);
                    counter++;
                    System.out.println("Student has been added.");
                }
            }
        }
    }
}
