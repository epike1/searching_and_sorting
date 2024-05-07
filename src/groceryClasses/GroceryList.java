package groceryClasses;

import java.util.ArrayList;
public class GroceryList {

    private ArrayList<String> itemList = new ArrayList<String>();

    public GroceryList() {

    }

    public ArrayList<String> getItemList() {
        return itemList;
    }

    public void addItem(String item) {
        itemList.add(item);
    }
    public boolean checkItem(String name) {

        for (String i : itemList) { // linear search
            if (i.equals(name))
                return true;
        }
        return false;
    }
    public void sortList() {

        int size = itemList.size();
        for (int i = 0; i < size-1; i++) // uses selection sort
        {

            int min = i;

            for (int j = i+1; j < size; j++) {
                if (itemList.get(j).compareTo(itemList.get(min)) < 0) {
                    min = j;
                }
            }

            String item = itemList.get(min); // swaps places
            itemList.set(min, itemList.get(i));
            itemList.set(i, item);
        }
    }
}
