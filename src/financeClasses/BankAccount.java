package financeClasses;
import java.util.LinkedList;

public class BankAccount {

    private final LinkedList<Double> transactions = new LinkedList<Double>();
    private String name;
    public BankAccount(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<Double> getTransactions() {
        return transactions;
    }

    private LinkedList<Double> getSortedTransactions() {
        LinkedList<Double> sortedByAmount = new LinkedList<Double>();

        sortedByAmount.addAll(getTransactions());

        mergeSort(sortedByAmount, 0, sortedByAmount.size() - 1);
        return sortedByAmount;
    }
    
    public void getTransaction(double amount) { // finds a specified amount in the transaction list using binary search
        LinkedList<Double> sortedTransactions = getSortedTransactions();
        System.out.println("\nFinding amount in transaction history...");

        int result = binarySearch(sortedTransactions, (double) Math.round(amount * 100) / 100); // rounds amount to two decimal places before checking

        if (result == -1) {
            System.out.println("Transaction not found. Make sure that amount is correct.");
        } else {

            if (sortedTransactions.get(result) > 0) {
                System.out.printf("Deposit of $%.2f found.%n", sortedTransactions.get(result));
            } else {
                System.out.printf("Withdrawal of $%.2f found.%n", Math.abs(sortedTransactions.get(result)));
            }
        }
    }

    private void displayTransactions(LinkedList<Double> sortedTransactions) {

        int limit = Math.min(sortedTransactions.size(), 10);

        System.out.printf("%n%-15s%s%n", "Type", "Amount");
        for (int i = 0 ; i < limit ; i++) {

            if (sortedTransactions.get(i) < 0) {
                System.out.printf("%-15s $%-15.2f%n", "Withdrawal", Math.abs(sortedTransactions.get(i)));
            } else {
                System.out.printf("%-15s $%-15.2f%n", "Deposit", sortedTransactions.get(i));
            }

        }
    }
    
    public void displayByTime() {
        System.out.println("\nTransaction History (Sorted by Time):");
        displayTransactions(getTransactions());
    }
    
    public void displayByAmount() {
    	System.out.println("\nTransaction History (Sorted by Amount):");
    	displayTransactions(getSortedTransactions());
    }
    public void createTransaction(double amount) {

        getTransactions().addLast(amount);

        if (getTransactions().size() > 50) { // can hold up to 50 transactions before removing
            getTransactions().removeFirst();
        }

        String type;

        if (amount < 0) {
            type = "withdrawn";
        } else {
            type = "deposited";
        }

        System.out.printf("$%.2f %s.%n", Math.abs(amount), type);
    }
    
    public double calculateTotal() {
    	double total = 0;
    	
    	for (double i : transactions) {
    		total += i;
    	}
    	
    	return total;
    }

    private void merge(LinkedList<Double> linkedList, int left, int middle, int right) // uses merge sort
    {
        int low = middle - left + 1;                    //size of the left subarray
        int high = right - middle;                      //size of the right subarray

        double[] L = new double[low];                             //create the left and right subarray
        double[] R = new double[high];

        int i = 0, j = 0;

        for (i = 0; i < low; i++)                               //copy elements into left subarray
        {
            L[i] = linkedList.get(left + i);
        }
        for (j = 0; j < high; j++)                              //copy elements into right subarray
        {
            R[j] = linkedList.get(middle + 1 + j);
        }


        int k = left;                                           //get starting index for sort
        i = 0;                                             //reset loop variables before performing merge
        j = 0;

        while (i < low && j < high)                     //merge the left and right subarrays
        {
            if (Math.abs(L[i]) >= Math.abs(R[j]))
            {
                linkedList.set(k, L[i]);
                i++;
            }
            else
            {
                linkedList.set(k, R[j]);
                j++;
            }
            k++;
        }

        while (i < low)                             //merge the remaining elements from the left subarray
        {
            linkedList.set(k, L[i]);
            i++;
            k++;
        }

        while (j < high)                           //merge the remaining elements from right subarray
        {
            linkedList.set(k, R[j]);
            j++;
            k++;
        }
    }

    private void mergeSort(LinkedList<Double> linkedList, int left, int right)       //helper function that creates the sub cases for sorting
    {
        int middle;
        if (left < right) {                             //sort only if the left index is lesser than the right index (meaning that sorting is done)
            middle = (left + right) / 2;

            mergeSort(linkedList, left, middle);                    //left subarray
            mergeSort(linkedList, middle + 1, right);               //right subarray

            merge(linkedList, left, middle, right);                //merge the two subarrays
        }
    }

    private int binarySearch(LinkedList<Double> linkedList, double amount) { // uses binary search to find the amount

            int low = 0, high = linkedList.size() - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                System.out.println(mid);

                // Check if amount is present at mid
                if ((double) Math.round(linkedList.get(mid) * 100) / 100 == amount) // round is used to check at 2 decimal places
                    return mid;

                // If amount is smaller, ignore left half
                if ((double) Math.round(linkedList.get(mid) * 100) > amount) // round is used to check at 2 decimal places
                    low = mid + 1;

                    // If amount is greater, ignore right half
                else
                    high = mid - 1;
                
                
            }

            // If we reach here, then element was
            // not present
            return -1;

    }
}
