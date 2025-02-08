package com.bank;
import java.sql.Connection;
// import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

// import com.bank.account.BankAccount;
import com.bank.account.User;
import com.bank.dao.UserDAO;
import com.bank.utils.DatabaseConnection;


public class BankApp {
    private static Scanner scanner = new Scanner(System.in);
    private static UserDAO userDAO;

    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            System.out.println("Connected to database!");
            userDAO = new UserDAO(conn);
            startCLI();
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }


        // Scanner scanner = new Scanner(System.in);

        // BankAccount account = createAccount(scanner);
        // System.out.println("Account Created Successfully!");

        // while (true) {
        //     System.out.println("\nMenu:");
        //     System.out.println("1. Deposit");
        //     System.out.println("2. Withdraw");
        //     System.out.println("3. Check Balance");
        //     System.out.println("4. View Transactions");
        //     System.out.println("5. Exit");

        //     System.out.print("Enter your choice: ");
        //     int choice = scanner.nextInt();

        //     if (choice == 1) {
        //         performDeposit(scanner, account);
        //     } else if (choice == 2) {
        //         performWithdraw(scanner, account);
        //     } else if (choice == 3) {
        //         checkBalance(account);
        //     } else if (choice == 4) {
        //         viewTransactions(scanner, account);
        //     } else if (choice == 5) {
        //         System.out.println("Thank you for visiting our bank!");
        //         break;
        //     } else {
        //         System.out.println("Invalid choice. Please try again!");
        //     }
        // }

        // scanner.close();
    }

    private static void startCLI() {
        while (true) {
            System.out.println("Welcome to bank!");
            System.out.println("\\n" + //
                                "1. Register\\n" + //
                                "2. Login\\n" + //
                                "3. Exit");

            System.out.print("Choose an action: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    loginUser();
                    break;
                case 3:
                    System.out.println("Exiting!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again!");
            }
        }
    }

    private static void registerUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        // Use console in the future for showing password in asterisk !!!
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = new User(username, password);
        if (userDAO.registerUser(user)) {
            System.out.println("User registered successfully!");
        } else {
            System.out.println("Registration Failed!");
        }
    }

    private static void loginUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        // Use console in the future for showing password in asterisk !!!
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User loggedInUser = userDAO.authenticateUser(username, password);
        if (loggedInUser != null) {
            System.out.println("Login Successful! Welcome, " + loggedInUser.getUsername());
        } else {
            System.out.println("Invalid Credentials. Please try again.");
        }
    }

    // public static BankAccount createAccount(Scanner scanner) {
    //     System.out.print("Enter Account Number: ");
    //     int acc_number = scanner.nextInt();
    //     scanner.nextLine();

    //     System.out.print("Enter account holder name: ");
    //     String acc_holder = scanner.nextLine();

    //     System.out.print("Enter opening balance: ");
    //     double balance = scanner.nextDouble();

    //     return new BankAccount(acc_number, acc_holder, balance);
    // }

    // public static void performDeposit(Scanner scanner, BankAccount account) {
    //     System.out.print("Enter the amount to deposit: ");
    //     account.deposit(scanner.nextDouble());
    // }

    // public static void performWithdraw(Scanner scanner, BankAccount account) {
    //     System.out.print("Enter the amount to withdraw: ");
    //     account.withdraw(scanner.nextDouble());
    // }

    // public static void checkBalance(BankAccount account) {
    //     System.out.println(account.getBalance());
    // }

    // public static void viewTransactions(Scanner scanner, BankAccount account) {
    //     System.out.print("Enter no of transactions: ");
    //     account.getTransactions(scanner.nextInt());
    // }
}