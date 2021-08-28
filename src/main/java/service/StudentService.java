package service;

import model.Student;
import org.apache.poi.hssf.usermodel.HeaderFooter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import repository.IStudentRepository;
import utils.Validate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * this class to add students
 */
public class StudentService implements IStudentService {
    private final IStudentRepository studentRepository;
    private static final int RECORDS_PER_SHEET = 34;

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
            tryExport(outputStream);
            System.out.println("Exported file success");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Exported file failed");
        }
    }

    private void tryExport(FileOutputStream outputStream) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();

        CellStyle titleStyle = createStyle(workbook, true, false);
        CellStyle headerStyle = createStyle(workbook, true, true);
        CellStyle bodyStyle = createStyle(workbook, false, true);

        List<Student> students = studentRepository.getStudents();

        XSSFSheet sheet = workbook.createSheet("Students report");

        sheet.setAutobreaks(true);
        PrintSetup printSetup = sheet.getPrintSetup();
        printSetup.setFitHeight((short)10);
        printSetup.setFitWidth((short)5);

        sheet.setColumnWidth(1, 6000);
        sheet.setColumnWidth(3, 5000);
        sheet.setColumnWidth(2, 5000);
        sheet.setColumnWidth(4, 5000);

//        Footer footer = sheet.getFooter();
//        footer.setRight( "Page " + HeaderFooter.page() + " of " + HeaderFooter.numPages() );
//        System.out.println(footer.getRight());
        // create title
        createRow(sheet, 0, titleStyle, 2, "List students");

        // create header
        createRow(sheet, 2, headerStyle, 0, "Id", "Name", "Gender", "Dob", "Phone");

        int rowNum = 3;
        for (Student student : students) {

            List<String> rowValues = new ArrayList<>();
            rowValues.add(String.valueOf(student.getId()));
            rowValues.add(student.getName());
            rowValues.add(student.getGender());
            rowValues.add(student.getDob());
            rowValues.add(String.valueOf(student.getPhoneNumber()));

            createRow(sheet, rowNum++, bodyStyle, 0, rowValues.toArray(new String[5]));
        }

        workbook.write(outputStream);
        workbook.close();
    }

    private void createRow(XSSFSheet sheet, int rowNumber, CellStyle rowStyle, int startCell, String... values)
    {
        Row row = sheet.createRow(rowNumber);

        for (String value : values) {
            Cell cell = row.createCell(startCell++);
            cell.setCellValue(value);
            cell.setCellStyle(rowStyle);
        }
    }

    private CellStyle createStyle(XSSFWorkbook workbook, boolean isFontBold, boolean isBorder)
    {
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 14);
        font.setFontName("Tahoma");
        font.setBold(isFontBold);

        CellStyle style = workbook.createCellStyle();
        style.setFont(font);

        if (isBorder) {
            short blackColorIndex = IndexedColors.BLACK.getIndex();

            style.setBorderTop(BorderStyle.THIN);
            style.setTopBorderColor(blackColorIndex);
            style.setBorderBottom(BorderStyle.THIN);
            style.setBottomBorderColor(blackColorIndex);
            style.setBorderRight(BorderStyle.THIN);
            style.setRightBorderColor(blackColorIndex);
            style.setBorderLeft(BorderStyle.THIN);
            style.setLeftBorderColor(blackColorIndex);
        }

        return style;
    }
}
