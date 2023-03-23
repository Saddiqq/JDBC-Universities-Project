package fetchData;

import java.sql.SQLException;

import java.util.List;
import java.util.Scanner;



public class MainApp {
    private static final Scanner inputScanner = new Scanner(System.in);
    
    private static final  DBManager dbManager = new DBManager();
    private static final APIManager apiHandler = new APIManager(dbManager);
    
    public static void main(String[] args) throws SQLException {
        boolean exit = false;
        while (!exit) {
            displayMenu();
            String option = inputScanner.nextLine();
            switch (option) {
                case "1":
                    createDatabase();
                    break;
                case "2":
                    saveDatabaseBackup();
                    break;
                case "3":
                    removeTable();
                    break;
                case "4":
                    retrieveDataFromAPI();
                    break;
                case "5":
                    getDataFromDatabase();
                    break;
                case "6":
                    exportDataToFile();
                    break;
                case "7":
                    showUniversitiesByCountry();
                    break;
                case "8":
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        System.out.println("Goodbye!");
    }

    // Display the menu options to the user
    private static void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Create Database");
        System.out.println("2. Save Database Backup");
        System.out.println("3. Remove Tables");
        System.out.println("4. Retrieve Data From API");
        System.out.println("5. Get Data From Database");
        System.out.println("6. Export Data To File");
        System.out.println("7. Show Universities By Country");
        System.out.println("8. Exit");
        System.out.print("Please enter your option: ");
    }

    // Initialize the database with the given name
    private static void createDatabase() {
        System.out.print("Please enter the name of the database: ");
        String databaseName = inputScanner.nextLine();
        dbManager.initializeDatabase(databaseName);
    }

    // Save a backup of the database to a file
    private static void saveDatabaseBackup() {
        System.out.print("Please enter the name of the backup file: ");
        String backupFileName = inputScanner.nextLine();
        //dbManager.saveBackup(backupFileName);
    }

    // Remove a table from the database
    private static void removeTable() throws SQLException {
        System.out.println("Enter the name of the table to be removed: ");
        String tableNameToDelete = inputScanner.nextLine();
        DBManager.removeTable(tableNameToDelete);
    }
    
    // Fetch data from the API based on the country name
    private static void retrieveDataFromAPI() {
        System.out.print("Please enter the name of the country: ");
        String country = inputScanner.nextLine();
        apiHandler.fetchUniversitiesByCountry(country);
    }

    // Get data from the database based on the country name
    private static void getDataFromDatabase() {
        System.out.print("Please enter the name of the country: ");
        String country = inputScanner.nextLine();
        dbManager.fetchUniversitiesByCountry(country);
    }

    // Export data from the database to a file
    private static void exportDataToFile() {
        System.out.print("Please enter the name of the file: ");
        String outputFileName = inputScanner.nextLine();
        dbManager.dumpDataToFile(outputFileName);
    }

    // Print the universities by country name
    private static void showUniversitiesByCountry() {
        System.out.print("Please enter the name of the country: ");
        String country = inputScanner.nextLine();
       
        dbManager.fetchUniversitiesByCountry(country);
    }

    // Retrieve all universities from the database and print them
    private static void fetchAllUniversities() {
        List<University> universityList = dbManager.getAllUniversities();
        for (University uni : universityList) {
            System.out.println(uni);
        }
    }
}
