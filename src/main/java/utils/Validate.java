package utils;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * this class for error checking
 */
public class Validate {
    private static final Scanner scanner = new Scanner(System.in);

    //    format date of birth
    static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    static {
        formatter.setLenient(false);
    }
    //   check birth year
    public static void checkYear(String date) throws Exception {
        String[] a = date.split("/");
        int year = Integer.parseInt(a[2]);
        if (year <= 1930 || year >= 2021)
            throw new Exception("Invalid year of birth");
    }
//    check selection student

    public static int validateSelection(String message)
    {
        return validateSelection(message, 3);
    }

    public static int validateSelection(String message, int maxRange) {
        System.out.println(message);
        try {
            int num = Integer.parseInt(scanner.nextLine());
            if (num > maxRange || num < 0) {
                System.out.println("The selection must be within the given range");
                return validateSelection(message);
            }
            return num;
        } catch (Exception e) {
            return validateSelection(message);
        }
    }



    /**
     * check date of birth
     * @param meseage
     * @return
     */

    //check date of birth
    public static String dob(String meseage) {
        System.out.println(meseage);
        System.out.println("dd/MM/yyyy");
        while (true) {
            String date = scanner.nextLine();
            if (date.length() == 10){
                try {
                    checkYear(date);
                    formatter.parse(date);

                    return date;
                } catch (Exception e) {
                    System.out.println("Invalid date of birth: " + e.getMessage());
                }
            }else {
                System.out.println("Invalid input! Please enter the date 10 char");
            }
        }
    }

    /**
     *  check menu selection add student ----> 1.Enter student name  2.exit
     * @param meesage
     * @return
     */
//    check menu selection add student ----> 1.Enter student name  2.exit
    public static int validateChonMenu(String meesage) {
        System.out.println(meesage);
        try {
            int num = Integer.parseInt(scanner.nextLine());
            if (num > 2 || num < 0) {
                System.out.println("The selection must be within the given range");
                return validateChonMenu(meesage);
            }
            return num;
        } catch (Exception e) {
            return validateChonMenu(meesage);
        }
    }

    //   format nameStudent
    static Pattern namePattern = Pattern.compile("^[\\pL\\pZ]{1,40}$");
    public static String validateName(String mess) {
        System.out.println(mess);
        try {
            String name = scanner.nextLine().trim().replaceAll("\\s+", " ");
            if (!namePattern.matcher(name).matches())
                throw new Exception("invalid name");
            String[] word = name.split(" ");
            StringBuilder result = new StringBuilder();
            for (String s : word) {
                result.append(s.substring(0, 1).toUpperCase()).append(s.substring(1)).append(" ");
            }
            return result.toString().trim();
        } catch (Exception e) {
            System.err.println("wrong name format");
            return validateName(mess);
        }
    }


    /**
     * check gender selection + uppercase letter
     * @param name
     * @return
     */

//check gender selection + uppercase letter
    public static String validateGender(String name) {
        System.out.println(name);
        System.out.println("Male/Female");
        while (true)
        {
            String newName = scanner.nextLine().trim();
            newName = newName.substring(0, 1).toUpperCase() + newName.substring(1).toLowerCase();
            if (newName.equals("Male") || newName.equals("Female"))
                return newName;
        }
    }

    /**
     * check phone number must be number
     * @param meesage
     * @return
     */
// check phone number must be number
    public static int phone(String meesage) {
        System.out.println(meesage);
        try {
            int num = Integer.parseInt(scanner.nextLine());
            return num;
        } catch (Exception e) {
            return phone(meesage);
        }
    }
}
