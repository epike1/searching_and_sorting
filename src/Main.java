// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import groceryClasses.*;
import weatherClasses.*;
import java.util.Scanner;
public class Main {


    public static void createWeatherData(WeatherList weatherList) {

        Scanner input = new Scanner(System.in);

        String day = "";
        String weatherType = "";
        double rainChance = 0.0;
        int temperature = 0;

        for (int i = 0 ; i < 5 ; i++) { // creating weather data

            switch (i) {
                case 0:
                    day = "Monday";
                    break;
                case 1:
                    day = "Tuesday";
                    break;
                case 2:
                    day = "Wednesday";
                    break;
                case 3:
                    day = "Thursday";
                    break;
                case 4:
                    day = "Friday";
                    break;

            }

            boolean validInput = false;
            System.out.println();
            do {

                System.out.println("Possible weather options:");
                System.out.println("1. Sunny");
                System.out.println("2. Cloudy");
                System.out.println("3. Rainy");
                System.out.println("4. Thunderstorm");

                System.out.printf("Enter the weather that best fits that %s: ", day);
                int num = 0;

                try {
                    num = input.nextInt();
                } catch (Exception e) {
                    System.out.println("Invalid input. Must be a number from one to four.");
                    input.next();
                    continue;
                }

                switch (num) {
                    case 1:
                        weatherType = "Sunny";
                        validInput = true;
                        break;
                    case 2:
                        weatherType = "Cloudy";
                        validInput = true;
                        break;
                    case 3:
                        weatherType = "Rainy";
                        validInput = true;
                        break;
                    case 4:
                        weatherType = "Thunderstorm";
                        validInput = true;
                        break;
                    default:
                        System.out.println("Invalid input. Must be a number from one to four.");
                        break;
                }
            } while (!validInput);

            System.out.println();
            do {
                System.out.printf("Enter the chance of rain for %s (0.00 to 1.00): ", day);

                try {
                    rainChance = input.nextDouble();
                } catch (Exception e) {
                    System.out.println("Invalid input. Must be a double from 0.00 to 1.00.");
                    input.next();
                    continue;
                }

                if (rainChance <= 1.00 && rainChance >= 0.00) {
                    break;
                } else {
                    System.out.println("Invalid input. Must be a double from 0.00 to 1.00.");
                }
            } while (true);

            System.out.println();
            validInput = false;

            do {

                System.out.printf("Enter the temperature in Celsius for %s: ", day);

                try {
                    temperature = input.nextInt();
                    validInput = true;
                } catch (Exception e) {
                    System.out.println("Invalid input. Must be a number.");
                    input.next();
                }

            } while (!validInput);

            weatherList.addWeatherData(new WeatherData(day, weatherType, rainChance, temperature));
            input.nextLine();
        }
        input.close();
    }
    public static void runWeatherProgram() {
        System.out.println("Enter the weather data for each day of the work week.");

        WeatherList weatherList = new WeatherList();

        createWeatherData(weatherList);
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