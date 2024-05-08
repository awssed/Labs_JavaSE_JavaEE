package org.example.lab10_jsx.classes;

public class Student {
    private int StudentId;
    private String StudentFio;
    private int Course;
    private int Group;

    public Student(int studentId, String studentFio, int studentCourse, int studentGroup) {
        StudentId = studentId;
        StudentFio = studentFio;
        Course = studentCourse;
        Group = studentGroup;
    }

    public int getStudentId() {
        return StudentId;
    }

    public void setStudentId(int studentId) {
        StudentId = studentId;
    }

    public String getStudentFio() {
        return StudentFio;
    }

    public void setStudentFio(String studentFio) {
        StudentFio = studentFio;
    }

    public int getCourse() {
        return Course;
    }

    public void setCourse(int course) {
        Course = course;
    }

    public int getGroup() {
        return Group;
    }

    public void setGroup(int group) {
        Group = group;
    }
}
