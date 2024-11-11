import java.io.*;
import java.util.Scanner;

public class CoconutClub {


    private static final Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        ClubDealer CoconutGrove = new ClubDealer();
        Boat.type csvType;
        String csvName;
        short csvYear;
        String csvMake;
        byte csvLength;
        double csvPrice;
        char option;
        String csvBoat;
        String boatNameToRemove;
        String boatNameToSpend;
        double spentAmount;
        String fileName;

        System.out.println("Welcome to the Fleet Management System");
        System.out.println("--------------------------------------");

        //here is when we have a command line input, boat data is read from the csv file.
        if (args.length > 0) {
            fileName = args[0];
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String boatData;
                while ((boatData = reader.readLine()) != null) {
                    String[] data = boatData.split(",");
                    try {
                        // Parse and create a new boat object
                        csvType =  Boat.type.valueOf(data[0].toUpperCase());
                        csvName = data[1].trim();
                        csvYear = Short.parseShort(data[2]);
                        csvMake = data[3].trim();
                        csvLength = Byte.parseByte(data[4]);
                        csvPrice = Double.parseDouble(data[5]);
                        // Create a new Boat object with the parsed data
                        Boat newBoat = new  Boat(csvType, csvName, csvYear, csvMake, csvLength, csvPrice, 0.0);
                        CoconutGrove.boatInventory.add(newBoat);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error parsing line, skipping: " + e.getMessage());
                    }
                    //here is when we don't have a command line input, boat data is read from the FleetData.db file
                }

            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }//this block will run if there has been given a command line input, namely the FleetData.csv file name
        else {
            fileName = "C:\\Users\\moham\\OneDrive\\Desktop\\FleetData.db";
            ObjectInputStream fromStream = null;
            try {
                fromStream = new ObjectInputStream(new FileInputStream(fileName));
                CoconutGrove = (ClubDealer) fromStream.readObject();

            } catch (IOException e) {
                System.out.println("ERROR loading " + e.getMessage());

            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());

            } finally {
                if (fromStream != null) {
                    try {
                        fromStream.close();
                    } catch (IOException e) {
                        System.out.println("ERROR closing " + e.getMessage());

                    }
                }
            }
        }//this block will run when no command line input has been given, loading the FleetData.db file
        do {
            System.out.println();
            System.out.print("(P)rint, (A)dd, (R)emove, (E)xpense, e(X)it : ");
            option = Character.toUpperCase(keyboard.nextLine().charAt(0));
            switch (option) {
                case 'P':
                    System.out.println();
                    CoconutGrove.display();

                    break;
                case 'A':
                    System.out.print("Please enter the new boat CSV data          :");
                    csvBoat = keyboard.nextLine();
                    CoconutGrove.addBoat(csvBoat);
                    break;
                case 'R':
                    System.out.print("Which boat do you want to remove? : ");
                    boatNameToRemove = keyboard.nextLine();
                    CoconutGrove.removeBoat(boatNameToRemove);

                    break;
                case 'E':
                    System.out.print("Which boat do you want to spend on? ");
                    boatNameToSpend = keyboard.nextLine();

                    // Use findBoat directly to check if the boat exists
                    if (CoconutGrove.findBoat(boatNameToSpend) != null) {
                        System.out.print("How much do you want to spend? ");
                        spentAmount = keyboard.nextDouble();
                        keyboard.nextLine(); // Clear the newline character

                        CoconutGrove.spendBoat(boatNameToSpend, spentAmount);
                    } else {
                        System.out.println("Cannot find boat " + boatNameToSpend);
                    }
                    break;
                case 'X':
                    System.out.println();
                    System.out.println("Exiting the Fleet Management System\n");
                    break;
                default:
                    System.out.println("Invalid menu option, try again");
                    break;
            }//end of the switch loop
        } while (option != 'X');

        fileName = "C:\\Users\\moham\\OneDrive\\Desktop\\FleetData.db";
        ObjectOutputStream toStream = null;
        try {
            toStream = new ObjectOutputStream(new FileOutputStream(fileName));
            toStream.writeObject(CoconutGrove);

        } catch (IOException e) {
            System.out.println("ERROR saving " + e.getMessage());

        } finally {
            if (toStream != null) {
                try {
                    toStream.close();
                } catch (IOException e) {
                    System.out.println("ERROR closing " + e.getMessage());

                }//end of the try-catch block
            }
        }//end of the finally block
    }//end of the main method

}//end of the CoconutClub class





