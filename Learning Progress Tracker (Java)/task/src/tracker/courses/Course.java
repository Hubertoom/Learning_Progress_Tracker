package tracker.courses;

import tracker.Student;

import java.util.LinkedHashMap;
import java.util.Map;

public class Course {
    private String courseName;
    private Map<String, Student> studentsList = new LinkedHashMap<>();
    private long totalPoints = 0;
    private int submissions = 0;
    private int pointsToAccomplish;


    public Course(String courseName, int pointsToAccomplish) {
        this.courseName = courseName;
        this.pointsToAccomplish = pointsToAccomplish;
    }

    public Map<String, Student> getStudentsList() {
        return studentsList;
    }

    public int getPointsToAccomplish() {
        return pointsToAccomplish;
    }

    public int getNumberOfStudents() {
        return studentsList.size();
    }

    public long getTotalPoints() {
        return totalPoints;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getSubmissions() {
        return submissions;
    }

    public void updatePoints(Student student, int points) {
        if (points <= 0) {
            return;
        }

        totalPoints += points;
        submissions++;
        if (!studentsList.containsKey(student.getStudentId())) {
            studentsList.put(student.getStudentId(), student);

        }
    }
}
