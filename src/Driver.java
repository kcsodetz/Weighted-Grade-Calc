import java.math.BigDecimal;
import java.util.*;
import java.util.stream.DoubleStream;

/**
 * Created by Ken Sodetz on 12/14/2016.
 */

public class Driver{

    private HashMap <String, double[]> gradesMap;
    private Scanner in = new Scanner(System.in);

    //constructor
    public Driver(){
        gradesMap = new HashMap<>();
    }

    //method to get and check the initial percentages
    private void inputGrades(int line) {

        Boolean isDouble;

        String tempName;
        double tempGrade = 0;
        for (int i = 0; i < line; i++) {

            double[] numArray = new double[2];

            System.out.print("Enter the name of the Category " + (i + 1) + ": ");
            tempName = in.next();
            do {
                isDouble = true;
                System.out.print("Enter the percent of category " + tempName + " (E.g: 94.5): ");
                tempGrade = checkDouble(in.next());
                if (tempGrade == 0)
                    isDouble = false;
            } while (!isDouble);

            numArray[0] = tempGrade/100.0;
            
            gradesMap.put(tempName.toUpperCase(), numArray);
        }
    }

    //method to get and check the weights of the categories
    private void inputWeight(){

        //Displays Current Categories and Percentages
        display();

        //Holder temp variables
        String temp;
        double tempNum = 0;

        //Get the initial weights of the categories
        for (Map.Entry<String, double[]> entry: gradesMap.entrySet()
             ) {
            boolean isInt;
            do {
                isInt = true;
                System.out.print("Enter the weight of "+entry.getKey()+"(E.g: 15): ");
                tempNum = checkDouble(in.next());
                if (tempNum == 0)
                    isInt = false;
            } while (!isInt);

            //place weights into the Hashmap
            double[] tempArray = entry.getValue();
            tempArray[1] = tempNum/100;
            gradesMap.put(entry.getKey(), tempArray);
        }

        //checks to see if the total added up is equal to 1
        boolean notValid, notDouble;
        if (checkEquality(totalWeight())){
            //do method
        }
        else {
            do {
                System.out.println("Total wights must be equal to 1");
                do {
                    notValid = false;
                    display();
                    System.out.print("Enter a category to edit its weight: ");
                    temp = in.next();
                    if (!gradesMap.containsKey(temp.toUpperCase())) {
                        notValid = true;
                        System.out.println("Category not found");
                    } else {
                        do {
                            notDouble = false;
                            System.out.print("Enter the new weight for " + temp + "(E.g: 15): ");
                            tempNum = checkDouble(in.next());
                            if (tempNum == 0)
                                notDouble = true;
                            double[] tempArray = gradesMap.get(temp);
                            tempArray[1] = tempNum/100.0;
                            gradesMap.put(temp, tempArray);
                        } while (notDouble);
                    }
                } while (notValid);
            } while (!checkEquality(totalWeight()));
        }

    }

    //adds up the total weights
    private double totalWeight(){
        double total = 0;
        for (Map.Entry<String, double[]> entry: gradesMap.entrySet()
                ) {
            double[] tempArray = entry.getValue();
            total += tempArray[1];
        }
        return total;
    }

    //parses and checks if the input is a valid double
    private double checkDouble(String input){
        try {
            double num = Double.parseDouble(input);
            return num;
        } catch (Exception e){
            System.out.println(input + " is an invalid input. Must be a standard number.");
        }
        return 0;
    }

    //checks to see if the given double is equal to 1 within a 3 decimal place of error
    private boolean checkEquality(double num){
        return (Math.abs(1 - num) < .001);
    }

    //gets the number of categories for the user to input
    private int getNumberOfCategories(){
        int temp = 0;
        Boolean isInt;
        do {
            isInt = true;
            try {
                System.out.print("Enter the number of weighted categories: ");
                temp = in.nextInt();
                if ((temp > 10) || (temp < 1)){
                    throw new IntegerOutOfBoundsException("Number of categories must be between 1 and 10");
                }
            } catch (InputMismatchException e){
                isInt = false;
                System.out.println("Number of categories must be an integer");
                in.next();
            } catch (IntegerOutOfBoundsException e){
                System.out.println("Number of categories must be between 1 and 10");
                isInt = false;
            }
        } while (!isInt);
        return temp;
    }

    //displays the category name, percent for the category, anc the weight of the category, for all categories given
    private void display(){
        System.out.printf("%-15s %-15s %-15s %n", "Category", "Percentage", "Weight");
        for (Map.Entry<String, double[]> entry: gradesMap.entrySet()
             ) {
            double[] temp = entry.getValue();
            String tempStr = Double.toString(temp[0]*100.0);
            BigDecimal bigDecimal = new BigDecimal((tempStr));
            BigDecimal bd = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
            System.out.printf("%-15s %-15s %-15s %n", entry.getKey(), bd.toString()+"%", temp[1]);
        }
    }

    //calculated the final numeric total
    private void calculateGrade(){ //TODO finish method
        int i = 0;
        double[] tempAdd = new double[10];
        for (Map.Entry<String, double[]> entry: gradesMap.entrySet()
             ) {
            double[] temp = entry.getValue();
            tempAdd[i] = temp[0] * temp[1];
            i++;
        }
        double sum = DoubleStream.of(tempAdd).sum();
        double grade = (sum/totalWeight()*100);
        String tempStr = Double.toString(grade);
        BigDecimal bigDecimal = new BigDecimal((tempStr));
        BigDecimal bd = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println("The final weighted total is "+bd.toString()+"%");
    }

    //main method
    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.inputGrades(driver.getNumberOfCategories());
        driver.inputWeight();
        driver.display();
        driver.calculateGrade();
    }
}