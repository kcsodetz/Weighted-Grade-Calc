import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringJoiner;

public class Driver{

    private HashMap <String, Double> gradesMap;
    private Scanner in = new Scanner(System.in);
    private double[] numArray;

    public Driver(){
        gradesMap = new HashMap<>();
        numArray= new double[2];
    }

    private void getGrades(int line){

        Boolean isDouble;
        double tempGrade = 0;
        for (int i = 0; i < line; i++) {
            System.out.print("Enter the name of the Category " +(i+1)+ ": ");
            in.nextLine();
            String temp = in.nextLine();
            do {
                try {
                    isDouble = true;
                    System.out.print("Enter the percent of category " + temp + " (E.g: 94.5): ");
                    tempGrade = in.nextDouble();
                } catch (InputMismatchException e) {
                    System.out.println("Percentage must be a Number");
                    isDouble = false;
                    in.next();
                }
            } while (!isDouble);
            tempGrade = tempGrade / 100;
            gradesMap.put(temp, tempGrade);
        }
    }

    private int getNumberOfCategories(){
        int temp = 0;
        Boolean isInt;
        do {
            isInt = true;
            try {
                System.out.print("Enter the number of weighted categories: ");
                temp = in.nextInt();
            } catch (InputMismatchException e){
                isInt = false;
                System.out.println("Number of categories must be an integer");
                in.next();
            }
        } while (!isInt);
        return temp;
    }

    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.getGrades(driver.getNumberOfCategories());
    }
}