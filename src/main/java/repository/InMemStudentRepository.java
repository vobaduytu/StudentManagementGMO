package repository;

import model.Student;

import java.util.ArrayList;
import java.util.List;

public class InMemStudentRepository implements IStudentRepository {
    public static List<Student> students = new ArrayList<>();
    public static int currentId = 0;
    // generate students for testing
    static {
        for (int i = 0; i < 54; i++) {
            students.add(new Student(currentId++, "name " + i, i + "/01/2000", "male", 1946124 + i));
        }
    }

    @Override
    public List<Student> getStudents() {
        return students;
    }

    @Override
    public Student getStudentId(int id) {
        for (Student student : students) {
            if (student.getId() == id)
                return student;
        }
        return null;
    }

    @Override
    public void addStudent(Student student) {
        student.setId(currentId++);
        students.add(student);
    }

    @Override
    public void updateStudent(Student student) {

    }

    @Override
    public void deleteStudent(int id) {

    }
}
