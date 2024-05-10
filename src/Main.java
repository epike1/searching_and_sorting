// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import groceryClasses.*;
import weatherClasses.*;
import java.util.Scanner;
public class Main {



    public static void runFinanceProgram() {
        System.out.println("Enter the weather data for each day of the work week.");

        WeatherList weatherList = new WeatherList();

        System.out.println("Weather data created.\n");
        weatherList.displayList();
    }
    public static void runGroceryProgram() {

        GroceryList groceryList = new GroceryList();
        Scanner input = new Scanner(System.in);
        String currentItem = "";

        do {

            System.out.print("Enter the name of an item (enter exit to quit): ");

            currentItem = input.nextLine();
            currentItem = currentItem.trim().toLowerCase();

            if (currentItem.isEmpty()) {
                System.out.println("Must enter a character.");
            } else if (currentItem.equals("exit")) {
                break;
            }else if (groceryList.checkItem(currentItem)) {
                System.out.printf("%s already found in list.%n", currentItem);
            } else {
                System.out.printf("%s not found in list. Add item? (Y/N): ", currentItem);
                String answer = input.nextLine();
                answer = answer.trim().toLowerCase();

                if (answer.equals("y")) { // counts any other answer as a no
                    groceryList.addItem(currentItem);
                    System.out.printf("%s added to list.%n", currentItem);
                }
            }
        } while(true);

        input.close();

        System.out.print("Original List: ");
        System.out.println(groceryList.getItemList());

        groceryList.sortList();

        System.out.print("Sorted List: ");
        System.out.println(groceryList.getItemList());
    }

    public static void main(String[] args) {
        runGroceryProgram();
        //runWeatherProgram();
    }
}