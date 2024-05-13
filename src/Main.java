// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import groceryClasses.*;
import financeClasses.*;
import java.util.Scanner;
public class Main {

	private static Scanner input = new Scanner(System.in);

	
	private static void transactionHistory(BankAccount bankAccount) {
		
		System.out.print("\nSelect a sorting method. Possible options are time, amount or type anything else to go back: ");
		
		input.nextLine();
		String type = input.nextLine().trim().toLowerCase();
		
		switch(type) {
			case "amount":
				bankAccount.displayByAmount();
				break;
			case "time":
				bankAccount.displayByTime();
				break;
		}
	}
	
	private static double getAmount() {
		
		double amount = 0.00; 
		do {
			System.out.print("\nEnter the amount: ");
			try {
				amount = input.nextDouble();
			} catch (Exception e) {
				System.out.println("Invalid input. Must be a number.");
				input.next();
				continue;
			}
			
			if (amount > 0) {
				return amount;
			}
			
			System.out.println("Invalid amount. Must be greater than $0.00.");
			
		} while (true);
	}
	
	private static double getTransactionInfo() {
		double amount = 0.00;
		
		amount = getAmount();
		input.nextLine();
		String type = "";
		do {
			System.out.print("\nAre you looking for a withdrawal or a deposit?: ");
			type = input.nextLine().trim().toLowerCase();
			
			switch(type) {
				case "deposit":
					return amount;
				case "withdrawal":
					amount *= -1;
					return amount;
				default:
					System.out.println("Invalid choice. Must be deposit or withdrawal.");
					break;
			}
		} while (true);
	}
	
    private static void runFinanceProgram() {
        
    	System.out.print("Enter your name: ");
    	String name = input.nextLine();
    	
    	BankAccount bankAccount = new BankAccount(name);
    	
    	System.out.printf("Welcome, %s.%n", bankAccount.getName());
    	
    	int choice = 0;
    	boolean quit = false;
    	do {
    		
    		System.out.println("\nAccount Options:");
    		System.out.println("1. Deposit");
			System.out.println("2. Withdraw");
			System.out.println("3. View Transaction History");
			System.out.println("4. Search for Transaction Amount");
			System.out.println("5. Quit program");
			System.out.print("Enter your choice: ");
    		try {
    			choice = input.nextInt();
    		} catch (Exception e) {
    			System.out.println("Invalid choice.");
    			input.next();
    			continue;
    		}
    		
    		switch (choice) {
	    		case 1:
	    			bankAccount.createTransaction(getAmount());
	    			break;
	    		case 2:
	    			bankAccount.createTransaction(getAmount() * -1);
	    			break;
	    		case 3:
	    			transactionHistory(bankAccount);
	    			break;
	    		case 4:
	    			bankAccount.getTransaction(getTransactionInfo());
	    			break;
	    		case 5:
	    			quit = true;
	    			break;
	    		default:
	    			System.out.println("Invalid choice. Must be 1 - 5.");
	    			break;
    		}
    		
    		
    	} while (!quit);
    	
    	double totalAmount = bankAccount.calculateTotal();
    	
    	if (totalAmount < 0) {
    		System.out.printf("You are $%.2f in debt.", Math.abs(totalAmount));
    	} else {
    		System.out.printf("You have $%.2f in your account.", totalAmount);
    	}
    }
    
    private static void runGroceryProgram() {

        GroceryList groceryList = new GroceryList();
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

        System.out.print("Original List: ");
        System.out.println(groceryList.getItemList());

        groceryList.sortList();

        System.out.print("Sorted List: ");
        System.out.println(groceryList.getItemList());
    }

    public static void main(String[] args) {
        //runGroceryProgram();
        runFinanceProgram();
        input.close();
    }
}