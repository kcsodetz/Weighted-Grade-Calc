import java.util.*;

public class Driver{

    private HashMap <String, double[]> gradesMap;
    private Scanner in = new Scanner(System.in);


    public Driver(){
        gradesMap = new HashMap<>();
    }

    private void inputGrades(int line) {

        Boolean isDouble;
        double tempGrade = 0;
        for (int i = 0; i < line; i++) {

            double[] numArray = new double[2];

            System.out.print("Enter the name of the Category " + (i + 1) + ": ");
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

            numArray[0] = tempGrade/100;
            
            gradesMap.put(temp, numArray);
        }
    }

    private void inputWeight(){
        System.out.println("Category\tPercentage");
        for (Map.Entry<String, double[]> entry: gradesMap.entrySet()
                ) {
            double[] tempArray = entry.getValue();
            System.out.println(entry.getKey()+"\t\t\t"+tempArray[0]*100+"%");
        }
        String temp;
        for (Map.Entry<String, double[]> entry: gradesMap.entrySet()
             ) {
            boolean isInt;
            double tempNum = 0;
            do {
                isInt = true;
                System.out.print("Enter the weight of "+entry.getKey()+": ");
                in.nextLine();
                temp = in.nextLine();
                try {
                    tempNum = Double.parseDouble(temp);
                } catch (Exception e){
                    isInt = false;
                    System.out.println("Weight must be a number");
                    //in.next();
                }
            } while (!isInt);
            double[] tempArray = entry.getValue();
            tempArray[1] = tempNum/100;
            gradesMap.put(entry.getKey(), tempArray);
        }
        double total = totalWeight();
        do {
            if (!(Math.abs(1 - total) < .00000001)) {
                System.out.println("Weighted total must be equal to 1");
                display();
                do {
                    System.out.print("Enter a category to edit: ");
                    temp = in.nextLine();
                    if (!gradesMap.containsKey(temp))
                        System.out.println("The category does not exist");
                    else {
                        System.out.print("Enter the new Weight for " +temp+ ": ");

                    }
                } while (!gradesMap.containsKey(temp));
            }
            total = totalWeight();
        } while (!(Math.abs(1 - total) < .00000001));

    }

    private double totalWeight(){
        double total = 0;
        for (Map.Entry<String, double[]> entry: gradesMap.entrySet()
                ) {
            double[] tempArray = entry.getValue();
            total += tempArray[1];
        }
        return total;
    }

    private double checkDouble(String input){


        return 0;
    }

    private double checkDouble(double num){

        return 0;
    }


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
                //in.next();
            }
        } while (!isInt);
        return temp;
    }
    
    private void display(){
        System.out.println("Category\tPercentage\tWeight");
        for (Map.Entry<String, double[]> entry: gradesMap.entrySet()
             ) {
            double[] temp = entry.getValue();
            System.out.println(entry.getKey()+"\t\t\t"+temp[0]*100+"%"+"\t\t"+temp[1]);
        }
    }
    

    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.inputGrades(driver.getNumberOfCategories());

        driver.display();
        driver.inputWeight();
        driver.display();
    }
}