package repository;

import model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InMemStudentRepository implements IStudentRepository {
    public static List<Student> students = new ArrayList<>();
    public static int currentId = 0;
    
    // generate students for testing
    static {
    	Random random = new Random();
    	
        for (int i = 1; i <= 54; i++) {
        	String dob = (1 + random.nextInt(28)) + "/" + (1 + random.nextInt(12)) + "/" + (random.nextInt(20) + 1990);
            students.add(new Student(currentId++, "name " + i, dob, "male", 1946124 + i));
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
