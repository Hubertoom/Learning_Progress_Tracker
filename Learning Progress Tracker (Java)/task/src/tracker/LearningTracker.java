package tracker;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class LearningTracker {
    private final List<Student> studentsList = new ArrayList<>();
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
        List<Student> studentTemp = new LinkedList<>();
        System.out.println("Enter student credentials or 'back' to return");

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("back")) {
                System.out.printf("Total %d students have been added.", studentTemp.size());
                this.studentsList.addAll(studentTemp);
                return;
            }

            if (Validator.validate(input)) {
                String[] student = input.split("\\s+");
                studentTemp.add(new Student(student[0], student[1], student[2]));
                System.out.println("Student has been added.");
            }

        }
    }
}
