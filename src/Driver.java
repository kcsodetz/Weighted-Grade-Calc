import java.math.BigDecimal;
import java.util.*;

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

            System.out.println("Enter the name of the Category " + (i + 1) + ": ");
            tempName = in.nextLine();
            do {
                isDouble = true;
                System.out.print("Enter the percent of category " + tempName + " (E.g: 94.5): ");
                tempGrade = checkDouble(in.nextLine());
                if (tempGrade == 0)
                    isDouble = false;
            } while (!isDouble);

            numArray[0] = tempGrade/100.0;
            
            gradesMap.put(tempName, numArray);
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
                System.out.print("Enter the weight of "+entry.getKey()+": ");
                tempNum = checkDouble(in.nextLine());
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
                    temp = in.nextLine();
                    if (!gradesMap.containsKey(temp)) {
                        notValid = true;
                        System.out.println("Category not found");
                    } else {
                        do {
                            notDouble = false;
                            System.out.print("Enter the new weight for " + temp + ": ");
                            tempNum = checkDouble(in.nextLine());
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
        System.out.println("Category\tPercentage\tWeight");
        for (Map.Entry<String, double[]> entry: gradesMap.entrySet()
             ) {
            double[] temp = entry.getValue();
            String tempStr = Double.toString(temp[0]*100.0);
            BigDecimal bigDecimal = new BigDecimal((tempStr));
            BigDecimal bd = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
            System.out.println(entry.getKey()+"\t\t\t"+bd.toString()+"%"+"\t\t"+temp[1]);
        }
    }

    //main method
    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.inputGrades(driver.getNumberOfCategories());

        driver.display();
        driver.inputWeight();
        driver.display();
    }
}