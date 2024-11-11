import java.io.Serializable;

public class Boat implements Serializable {
    private static final long serialVersionUID = 1L;
    public enum type {SAILING, POWER}
    private type typeOfBoat;
    private String name;
    private short yearOfManufacture;
    private String makeModel;
    private byte length;
    private double purchasePrice;
    private double expenses;

    public Boat() {
        type typeOfBoat = null;
        name = "";
        yearOfManufacture = 0;
        makeModel = "";
        length = 0;
        purchasePrice = 0.00;
        expenses = 0.00;
    }

    public Boat(type typeOfBoat, String name, short yearOfManufacture, String makeModel,
                byte length, double purchasePrice, double expenses) {
        this();
        this.typeOfBoat = typeOfBoat;
        this.name = name;
        this.yearOfManufacture = yearOfManufacture;
        this.makeModel = makeModel;
        this.length = length;
        this.purchasePrice = purchasePrice;
        this.expenses = expenses;
    }


    public String toString() {
        return (String.format( "%-10s %-20s %4d %-10s %4d' : Paid $%10.2f : Spent $%10.2f",
                typeOfBoat, name, yearOfManufacture, makeModel, length, purchasePrice, expenses));

    }

    public double getPurchasePrice(){
        return purchasePrice;
    }
    public double getExpenses(){
        return expenses;
    }
    public String getName(){
        return name;
    }
    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }
}

