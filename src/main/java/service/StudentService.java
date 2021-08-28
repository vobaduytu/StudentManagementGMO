package service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import model.Student;
import repository.IStudentRepository;
import utils.ExcelUtils;
import utils.Validate;

/**
 * this class to add students
 */
public class StudentService implements IStudentService {
    private final IStudentRepository studentRepository;
    //private static final int RECORDS_PER_SHEET = 34;

    public StudentService(IStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void addStudent() {
        System.out.println("1.Enter the number of students ");
        System.out.println("2.Exit");
        while (true) {
            int option = Validate.validateChonMenu("enter selection");

            if (option == 1) {
                int numberOfStudents = Validate.validateSelection("Number of new students:", 1000);
                enterStudent(numberOfStudents);
            } else if (option == 2) {
                return;
            }
        }
    }

    private void enterStudent(int numberOfStudents) {
        for (int i = 1; i <= numberOfStudents; i++) {
            String newName = Validate.validateName("Enter student name " + i);
            String newGender = Validate.validateGender("enter gender");
            String newDob = Validate.dob("enter date of birth");
            Integer newPhone = Validate.phone("Enter  phone number");

            studentRepository.addStudent(new Student(newName, newDob, newGender, newPhone));
            System.out.println("Add student success!!!");
        }
    }

    @Override
    public void showStudent() {
        System.out.format("%-3.5s | ", "Id");
        System.out.format("%-40s | ", "StudentName");
        System.out.format("%-15s | ", "Dob");
        System.out.format("%-10s | ", "Gender");
        System.out.format("%-15s | ", "PhoneNumber");
        System.out.format("%-15s | ", "address");
        System.out.format("%-15s | ", "email");
        System.out.format("\n");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
        for (Student student : studentRepository.getStudents()) {
            System.out.format("%-3.5s | ", student.getId());
            System.out.format("%-40s | ", student.getName());
            System.out.format("%-15s | ", student.getDob());
            System.out.format("%-10s | ", student.getGender());
            System.out.format("%-15s | ", student.getPhoneNumber());
            System.out.format("%-15s | ", student.getAddress());
            System.out.format("%-15s | ", student.getEmail());

            System.out.format("\n");
        }
    }

    @Override
    public void exportToExcel() {
        File outputFile = new File( "report_" + new Date().getTime() +".xlsx");
        // create output file
        try {
            boolean result = outputFile.createNewFile();
            if (!result) {
                System.out.println("Create new file failed");
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // export data to output file
        try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
        	List<Student> students = studentRepository.getStudents();
            ExcelUtils.tryExport(outputStream, students);
            System.out.println("Exported file success");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exported file failed");
        }
    }


}
