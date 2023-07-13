package tracker;

import java.util.*;

public class LearningTracker {
    private static int id;
    private final Map<Integer, Student> studentsList = new HashMap<>();
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
                case "exit" -> {
                    System.out.println("Bye");
                    return;
                }
                case "back" -> System.out.println("Enter 'exit' to exit the program.");
                default -> System.out.println("Error: unknown command!");
            }

        }

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
                    studentsList.put(id, student);
                    id++;
                    counter++;
                    System.out.println("Student has been added.");
                }
            }
        }
    }
}
