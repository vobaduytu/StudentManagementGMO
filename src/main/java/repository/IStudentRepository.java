package repository;

import model.Student;

import java.util.List;

public interface IStudentRepository {
    List<Student> getStudents();
    Student getStudentId(int id);
    void addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(int id);
}
