import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Welcome to bank!");
        Scanner scanner = new Scanner(System.in);

        BankAccount account = createAccount(scanner);
        System.out.println("Account Created Successfully!");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. View Transactions");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                performDeposit(scanner, account);
            } else if (choice == 2) {
                performWithdraw(scanner, account);
            } else if (choice == 3) {
                checkBalance(account);
            } else if (choice == 4) {
                viewTransactions(scanner, account);
            } else if (choice == 5) {
                System.out.println("Thank you for visiting our bank!");
                break;
            } else {
                System.out.println("Invalid choice. Please try again!");
            }
        }

        scanner.close();
    }

    public static BankAccount createAccount(Scanner scanner) {
        System.out.print("Enter Account Number: ");
        int acc_number = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter account holder name: ");
        String acc_holder = scanner.nextLine();

        System.out.print("Enter opening balance: ");
        double balance = scanner.nextDouble();

        return new BankAccount(acc_number, acc_holder, balance);
    }

    public static void performDeposit(Scanner scanner, BankAccount account) {
        System.out.print("Enter the amount to deposit: ");
        account.deposit(scanner.nextDouble());
    }

    public static void performWithdraw(Scanner scanner, BankAccount account) {
        System.out.print("Enter the amount to withdraw: ");
        account.withdraw(scanner.nextDouble());
    }

    public static void checkBalance(BankAccount account) {
        System.out.println(account.getBalance());
    }

    public static void viewTransactions(Scanner scanner, BankAccount account) {
        System.out.print("Enter no of transactions: ");
        account.getTransactions(scanner.nextInt());
    }
}