import repository.IStudentRepository;
import repository.InMemStudentRepository;
import service.IStudentService;
import service.StudentService;

import java.io.IOException;
import java.util.Scanner;

public class StudentManager {
    public static final int ADD_STUDENT = 1;
    public static final int DISPLAY = 2;
    public static final int EXIT = 3;
    public static final int EXPORT_EXCEL = 4;

    private final IStudentService studentService;
    private final Scanner scanner;

    public StudentManager(IStudentService studentService, Scanner scanner) {
        this.studentService = studentService;
        this.scanner = scanner;
    }

    public void run() {
        while (true) {
            try {
                System.out.println("1: Add students");
                System.out.println("2: Display");
                System.out.println("3: Exit");
                System.out.println("4: file Excel");
                int option = scanner.nextInt();

                switch (option) {
                    case ADD_STUDENT -> studentService.addStudent();
                    case DISPLAY -> studentService.showStudent();
                    case EXIT -> {
                        System.out.println("Goodbye......");
                        return;
                    }
                    case EXPORT_EXCEL -> studentService.exportToExcel();
                    default -> System.err.println("wrong input - choose again");
                }
            } catch (Exception e) {
                System.err.println("choose again:");
            }
        }
    }
}
