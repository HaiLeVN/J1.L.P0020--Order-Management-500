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
            }
            else{
                check = false;
            }
        } while (check);
        return result;
    }
    public static String getString(String welcome, String oldData) {
        String result = oldData;
        Scanner sc = new Scanner(System.in);
        System.out.print(welcome);
        String tmp = sc.nextLine();
        if (!tmp.isEmpty()) {
            result = tmp;
        }
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

    public static int getInt(String welcome, int min, int max, int oldData) {
        boolean check = true;
        int number = oldData;
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
        } while (check || number > max || number < min);
        return number;
    }
    
    public static String getCategory(String input_category) {
        Scanner sc = new Scanner(System.in);
        
        while(true) {
            System.out.print(input_category);
            String result = sc.nextLine();
            if(result.equalsIgnoreCase("cat")
                    || result.equalsIgnoreCase("dog")
                    || result.equalsIgnoreCase("parrot")) {
                return result;
            }
            System.err.println("There are only three categories: Cat, Dog, Parrot");
            System.out.println("Input category (Cat, Dog, Parrot)");
        }
    }
    //get valid date
    public static String inputDate(String mess) {
        Scanner sc = new Scanner(System.in);
        System.out.print(mess);
        //set format of date
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        //force user input exectly a date
        while(true) {
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
                dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                return dateFormat.format(date);
            } catch (Exception e) {
                System.out.print("Input valid date (dd/MM/yyyy): ");
            }
        }
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
}
