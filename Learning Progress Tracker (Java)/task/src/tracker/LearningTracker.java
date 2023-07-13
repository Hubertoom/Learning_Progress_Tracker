package tracker;

import java.util.*;

public class LearningTracker {
    private final Map<String, Student> studentsList = new LinkedHashMap<>();
    private final Scanner scanner = new Scanner(System.in);

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
                case "exit" -> {
                    System.out.println("Bye");
                    return;
                }
                case "back" -> System.out.println("Enter 'exit' to exit the program.");
                default -> System.out.println("Error: unknown command!");
            }

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
        student.addDsa(dsa);
        student.addDatabase(database);
        student.addSpring(spring);
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
                System.out.printf("Total %d students have been added.", counter);
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
