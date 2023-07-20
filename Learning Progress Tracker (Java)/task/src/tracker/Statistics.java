package tracker;

import tracker.courses.*;

import java.util.*;
import java.util.stream.Collectors;

public class Statistics {
    private final Map<String, Student> studentList;
    private final Map<String, Course> coursesList;

    public Statistics(Map<String, Student> studentsList, Map<String, Course> coursesList) {
        this.studentList = studentsList;
        this.coursesList = coursesList;
    }

    public String getMostPopular() {
        if (!checkIsAtLeastOneCourseIsActive()) {
            return "n/a";
        }

        return coursesList.values().stream()
                .sorted(Comparator.comparingInt(Course::getNumberOfStudents)
                        .reversed())
                .map(Course::getCourseName)
                .collect(Collectors.joining(", "));

    }

    public String getLeastPopular() {
        if (checkIfCoursesAreEqual(coursesList.values().stream().toList())) {
            return "n/a";
        }

        return coursesList.values().stream()
                .sorted(Comparator.comparing(Course::getNumberOfStudents))
                .limit(1)
                .map(Course::getCourseName)
                .collect(Collectors.joining());
    }

    public String getHighestActivity() {
        if (!checkIsAtLeastOneCourseIsActive()) {
            return "n/a";
        }

        return coursesList.values().stream()
                .sorted(Comparator.comparingInt(Course::getSubmissions)
                        .reversed())
                .map(Course::getCourseName)
                .collect(Collectors.joining(", "));
    }

    private boolean checkIfCoursesAreEqual(List<Course> courses) {
        if (courses.isEmpty()) {
            return true;
        }
        int max = courses.get(0).getSubmissions();
        for (Course course : courses) {
            if (max > course.getSubmissions() || max < course.getSubmissions()) {
                return false;
            }
        }
        return true;
    }

    public String getLowestActivity() {
        if (checkIfCoursesAreEqual(coursesList.values().stream().toList())) {
            return "n/a";
        }

        return coursesList.values().stream()
                .sorted(Comparator.comparingInt(Course::getSubmissions))
                .limit(1)
                .map(Course::getCourseName)
                .collect(Collectors.joining());
    }

    public String getEasiestCourse() {
        if (!checkIsAtLeastOneCourseIsActive()) {
            return "n/a";
        }
        return coursesList.values().stream()
                .sorted(Comparator.comparingLong(Course::getTotalPoints)
                        .reversed())
                .limit(1)
                .map(Course::getCourseName)
                .collect(Collectors.joining());
    }

    public String getHardestCourse() {
        if (!checkIsAtLeastOneCourseIsActive()) {
            return "n/a";
        }
        return coursesList.values().stream()
                .sorted(Comparator.comparingLong(Course::getTotalPoints))
                .limit(1)
                .map(Course::getCourseName)
                .collect(Collectors.joining());
    }

    public void getStatisticsForCourse(String courseName) {
        System.out.println(courseName);
        System.out.println("id  points  completed");

        if (!coursesList.containsKey(courseName)) {
            System.out.println("Unknown course.");
            return;
        }

        if (!checkIsAtLeastOneCourseIsActive()) {
            return;
        }

        Course course = coursesList.get(courseName);
        course.getStudentsList()
                .values().stream()
                .sorted(Comparator.<Student>comparingInt(student -> student.getPoints(courseName)).reversed())
                .forEach(student -> System.out.printf("%s %d        %.1f%%\n"
                        , student.getStudentId()
                        , student.getPoints(courseName)
                        , (double) student.getPoints(courseName) / course.getPointsToAccomplish() * 100.0
                ));
    }

    private boolean checkIsAtLeastOneCourseIsActive() {
        return coursesList.values().stream()
                .anyMatch(course -> course.getNumberOfStudents() != 0);
    }
}
