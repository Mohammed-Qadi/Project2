import java.io.*;
import java.util.ArrayList;
import java.io.Serializable;


public class ClubDealer implements Serializable  {
    private static final long serialVersionUID = 1L;
    public ArrayList< Boat> boatInventory;
    Boat.type addType;
    String addName;
    short addYearOfManufacture;
    String addMake;
    byte addLength;
    double addPurchasePrice;

    public ClubDealer() {
        this.boatInventory = new ArrayList<>();
    }//end of the default constructor

    public void display() {
        int index;
        System.out.println("\"Fleet report:\"");
        for (index = 0; index < boatInventory.size(); index++) {
            if (boatInventory.get(index) != null) {
                System.out.println(boatInventory.get(index));
            }
        }
        System.out.printf("%-53s : Paid $%10.2f : Spent $%10.2f%n", "Total", getTotalPrice(), getTotalExpenses());
    }//end of the display method

    public void addBoat(String csvData) {
        // Split the CSV string by commas
        String[] eachData = csvData.split(",");
        // Parse and assign each part of the CSV data
        addType =  Boat.type.valueOf(eachData[0].toUpperCase());
        addName = eachData[1].trim();
        addYearOfManufacture = Short.parseShort(eachData[2]);
        addMake = eachData[3].trim();
        addLength = Byte.parseByte(eachData[4]);
        addPurchasePrice = Double.parseDouble(eachData[5]);
        Boat newBoat = new  Boat(addType, addName, addYearOfManufacture, addMake, addLength, addPurchasePrice, 0);
        boatInventory.add(newBoat);
    }//end of the addBoat method

    public void removeBoat(String name) {
        int index;
        boolean foundBoat = false;
        for (index = 0; index < boatInventory.size(); index++) {
            if (name.equalsIgnoreCase(boatInventory.get(index).getName())) {
                boatInventory.remove(index);
                foundBoat = true;
                break;
            }
        }
        if (!foundBoat) {
            System.out.println("Cannot find boat " + name);
        }

    }//end of the removeBoat method

    public void spendBoat(String name, double spentAmount) {
        int index;
        boolean foundBoat = false;
        double boatExpense;
        for (index = 0; index < boatInventory.size(); index++) {
            if (name.equalsIgnoreCase(boatInventory.get(index).getName())) {
                if (spentAmount + boatInventory.get(index).getExpenses() <= boatInventory.get(index).getPurchasePrice()) {
                    boatExpense = boatInventory.get(index).getExpenses() + spentAmount;
                    boatInventory.get(index).setExpenses(boatExpense);
                    System.out.println("Expense authorized, $" + spentAmount + " spent.");
                } else {
                    double amountLeft = boatInventory.get(index).getPurchasePrice() - boatInventory.get(index).getExpenses();
                    System.out.printf("Expense not permitted, only $%.2f left to spend.", amountLeft);
                }
                foundBoat = true;
                break;
            }
        }
        if (!foundBoat) {
            System.out.println("Cannot find boat " + name);
        }

    }//end of the spendBoat method

    public  Boat findBoat(String name) {
        int iterateBoat;
        boolean foundBoat = false;
        for (iterateBoat = 0; iterateBoat < boatInventory.size(); iterateBoat++) {
            if (boatInventory.get(iterateBoat).getName().equalsIgnoreCase(name)) {
                foundBoat = true;
                return boatInventory.get(iterateBoat);
            }
        }
        return null;
    }

    private double getTotalPrice() {
        int iterateBoat;
        double totalPrice = 0;
        for (iterateBoat = 0; iterateBoat < boatInventory.size(); iterateBoat++) {
            totalPrice += boatInventory.get(iterateBoat).getPurchasePrice();
        }
        return totalPrice;
    }//end of the getTotalPrice method

    private double getTotalExpenses() {
        int iterateBoat;
        double totalExpenses = 0;
        for (iterateBoat = 0; iterateBoat < boatInventory.size(); iterateBoat++) {
            totalExpenses += boatInventory.get(iterateBoat).getExpenses();
        }
        return totalExpenses;
    }//end of the getTotalExpenses method


}//end of the clubDealer class


