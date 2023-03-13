/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Thanh Hai
 */
public class Utils {

    public static String getString(String welcome) {
        boolean check = true;
        String result = "";
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print(welcome);
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.out.println("Input text!!!");
            } else {
                check = false;
            }
        } while (check);
        return result;
    }

    public static String getString(String welcome, int minCharacter, int maxCharacter) {
        boolean check = true;
        String result = "";
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print(welcome);
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.out.println("Input text!!!");
            } else if (result.length() < minCharacter || result.length() > maxCharacter) {
                System.out.println("Your description must be above 3 and below 50 characters.");
            } else {
                check = false;
            }
        } while (check);
        return result;
    }

    public static String getString(String welcome, String formatter) {
        boolean check = true;
        String result = "";
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print(welcome);
            result = sc.nextLine();
            if (!result.matches(formatter)) {
                System.out.println("Invalid ID format. Please enter a valid ID.");
            } else {
                if (result.isEmpty()) {
                    System.out.println("Input text!!!");
                } else {
                    check = false;
                }
            }
        } while (check);
        return result;
    }

    public static int getInt(String welcome, int min, int max) {
        boolean check = true;
        int number = 0;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                number = Integer.parseInt(sc.nextLine());
                check = false;
            } catch (NumberFormatException e) {
                System.out.println("Input number!!!");
            }
        } while (check || number > max || number < min);
        return number;
    }

    public static void exitMenu(String welcome) {
        String key = "";
        Scanner sc = new Scanner(System.in);
        System.out.print(welcome);
        key = sc.nextLine();
    }

    //get valid date
    public static String inputDate(String mess) {
        Scanner sc = new Scanner(System.in);
        System.out.print(mess);
        //set format of date
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.setLenient(false);
        //force user input exectly a date
        while (true) {
            String input = sc.nextLine();
            try {
                Date date = dateFormat.parse(input);
                //get current date
//                Date curDate = Calendar.getInstance().getTime();
                //check range of date
//                if (curDate.compareTo(date) < 0) {
//                    System.out.print("Please input date that before current date: ");
//                    continue;
//                }
                dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                return dateFormat.format(date);
            } catch (Exception e) {
                System.out.print("Input valid date (MM/dd/yyyy): ");
            }
        }
    }

    public static boolean getBoolean(String welcome) {
        boolean check = true;
        boolean result = false;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print(welcome);
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("true")) {
                result = true;
                check = false;
            } else if (input.equalsIgnoreCase("false")) {
                result = false;
                check = false;
            } else {
                System.out.println("Please enter either 'true' or 'false'");
            }
        } while (check);
        return result;
    }

    public static int getInt(String welcome, int min) {
        boolean check = true;
        int number = 0;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                String tmp = sc.nextLine();
                if (tmp.isEmpty()) {
                    check = false;
                } else {
                    number = Integer.parseInt(tmp);
                    check = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input number!!!");
            }
        } while (check || number < min);
        return number;
    }

    public static int updateInt(String welcome, int oldData) {
        boolean check = true;
        int newData = oldData;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                String input = sc.nextLine();
                if (input.isEmpty()) {
                    check = false;
                } else {
                    newData = Integer.parseInt(input);
                    if (newData <= 0) {
                        System.out.println("Input must be above 0!");
                    } else {
                        check = false;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Input must be a number!!!");
            }
        } while (check);
        return newData;
    }

    public static int getInt(String welcome) {
        boolean check = true;
        int number = 0;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                String tmp = sc.nextLine();
                if (tmp.isEmpty()) {
                    check = false;
                } else {
                    number = Integer.parseInt(tmp);
                    check = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input number!!!");
            }
        } while (check);
        return number;
    }

    public static String getStringNumber(String welcome, int minCharacter, int maxCharacter) {
        Scanner sc = new Scanner(System.in);
        System.out.print(welcome);
        while (sc.hasNext()) {
            String input = sc.next();
            if (input.matches("\\d{" + minCharacter + "," + maxCharacter + "}")) {
                return input;
            } else {
                System.out.print("Invalid input. Please enter a number with " + minCharacter + " to " + maxCharacter + " digits: ");
            }
        }
        return null; // return null if input is not valid
    }

    public static String updateString(String welcome, String oldData) {
        Scanner sc = new Scanner(System.in);
        System.out.print(welcome);
        String input = sc.nextLine();
        if (input.trim().isEmpty()) {
            input = oldData;
        }
        return input;
    }
    public static String updateDate(String welcome, String oldData) {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.setLenient(false);

        while (true) {
            System.out.print(welcome);
            String input = sc.nextLine();
            if (input.isEmpty()) {
                return oldData;
            } else {
                try {
                    Date date = dateFormat.parse(input);
                    return dateFormat.format(date);
                } catch (Exception e) {
                    System.out.print("Input valid date (MM/dd/yyyy): ");
                }
            }
        }
    }
    public static String updateString(String welcome, String oldData, String formatter) {
        boolean check = true;
        String result = oldData;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print(welcome);
            String input = sc.nextLine();
            if (input.isEmpty()) {
                check = false;
            } else if (!input.matches(formatter)) {
                System.out.println("Invalid input format. Please enter a valid input.");
            } else {
                result = input;
                check = false;
            }
        } while (check);
        return result;
    }
    public static boolean updateBoolean(String welcome, boolean oldData) {
        Scanner sc = new Scanner(System.in);
        boolean newData = oldData;
        boolean validInput = false;
        do {
            System.out.print(welcome);
            String input = sc.nextLine();
            if (input.isEmpty()) {
                validInput = true; // do not update data
            } else if (input.equalsIgnoreCase("true")) {
                newData = true;
                validInput = true;
            } else if (input.equalsIgnoreCase("false")) {
                newData = false;
                validInput = true;
            } else {
                System.out.println("Please enter either 'true' or 'false'");
            }
        } while (!validInput);
        return newData;
    }
}
