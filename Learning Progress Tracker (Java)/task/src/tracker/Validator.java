package tracker;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public class Validator {
    private static final String NAME_REGEX = "^[A-Za-z]+[-']?[a-zA-Z]+";
    private static final String SURNAME_REGEX = "(\\w+[- ']?)+\\w+";
    private static final String EMAIL_REGEX = "((\\w+)|(\\w+[.-]?\\w+))@\\w+\\.\\w+";

    public static Optional<Student> validate(String student) {
        String[] studArr = student.strip().split("\\s+");

        if (studArr.length < 3) {
            System.out.println("Incorrect credentials");
            return Optional.empty();
        }

        String name = studArr[0];
        String email = studArr[studArr.length - 1];
        String surname = Arrays.stream(studArr, 1, studArr.length - 1)
                .collect(Collectors.joining());

        if (!name.matches(NAME_REGEX)) {
            System.out.println("Incorrect first name");
            return Optional.empty();
        }
        if (!surname.matches(SURNAME_REGEX)) {
            System.out.println("Incorrect last name name");
            return Optional.empty();
        }
        if (!email.matches(EMAIL_REGEX)) {
            System.out.println("Incorrect email");
            return Optional.empty();
        }
        return Optional.of(new Student(name, surname, email));
    }
}
