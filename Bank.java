import java.util.Scanner;
import java.util.ArrayList;

class Transaction {
    private String description;
    private double amount;

    public Transaction(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }
}

class Account {
    private String userId;
    private String userPin;
    private double balance;
    private ArrayList<Transaction> transactionHistory;

    public Account(String userId, String userPin) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = 100.0;
        this.transactionHistory = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public boolean validatePin(String enteredPin) {
        return userPin.equals(enteredPin);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add(new Transaction("Deposit", amount));
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add(new Transaction("Withdrawal", -amount));
            return true;
        }
        return false;
    }

    public boolean transfer(Account recipient, double amount) {
        if (amount > 0 && amount <= balance) {
            if (this != recipient) {
                balance -= amount;
                recipient.deposit(amount);
                transactionHistory.add(new Transaction("Transfer to " + recipient.getUserId(), -amount));
                return true;
            }
        }
        return false;
    }

    public ArrayList<Transaction> getTransactionHistory() {
        return transactionHistory;
    }
}

public class Bank {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create an account
        Account account = new Account("12345", "1234");

        // Prompt user for user id and pin
        System.out.print("Enter User ID: ");
        String enteredUserId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String enteredPin = scanner.nextLine();

        if (account.getUserId().equals(enteredUserId) && account.validatePin(enteredPin)) {
            System.out.println("Welcome to the ATM!");
            boolean isRunning = true;
            while (isRunning) {
                System.out.println("\nSelect an option:");
                System.out.println("1. Transactions History");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. Quit");

                int choice = scanner.nextInt();


                switch (choice) {
                    case 1:
                        displayTransactionHistory(account);
                        break;
                    case 2:
                        withdrawFunds(account, scanner);
                        break;
                    case 3:
                        depositFunds(account, scanner);
                        break;
                    case 4:
                        transferFunds(account, scanner);
                        break;
                    case 5:
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Invalid User ID or PIN. Exiting.");
        }
    }

    private static void displayTransactionHistory(Account account) {
        ArrayList<Transaction> transactions = account.getTransactionHistory();
        System.out.println("\nTransaction History:");
        for (Transaction transaction : transactions) {
            System.out.println("Description: " + transaction.getDescription() +
                    ", Amount: " + transaction.getAmount());
        }
    }

    private static void withdrawFunds(Account account, Scanner scanner) {
        System.out.print("Enter the amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (account.withdraw(amount)) {
            System.out.println("Withdrawal successful. Current balance: " + account.getBalance());
        } else {
            System.out.println("Withdrawal failed. Insufficient funds.");
        }
    }

    private static void depositFunds(Account account, Scanner scanner) {
        System.out.print("Enter the amount to deposit: ");
        double amount = scanner.nextDouble();
        account.deposit(amount);
        System.out.println("Deposit successful. Current balance: " + account.getBalance());
    }

    private static void transferFunds(Account senderAccount, Scanner scanner) {
        System.out.print("Enter recipient's User ID: ");
        String recipientId = scanner.next();
        System.out.print("Enter the amount to transfer: ");
        double amount = scanner.nextDouble();

        Account recipientAccount = new Account("23232", "2323");

        if (senderAccount.transfer(recipientAccount, amount)) {
            System.out.println("Transfer successful. Current balance: " + senderAccount.getBalance());
        } else {
            System.out.println("Transfer failed. insufficient funds.");
        }
    }
}
