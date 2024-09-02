import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ATM {

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.run();
    }

    private Account currentAccount;
    private Scanner scanner;
    private List<Account> accounts;

    // Initialize with demo accounts
    public ATM() {
        scanner = new Scanner(System.in);
        accounts = new ArrayList<>();
        accounts.add(new SavingsAccount("123456", 1000, hashPin("1234"))); // Account number, balance, hashed PIN
        accounts.add(new CurrentAccount("654321", 500, hashPin("5678")));
    }

    public void run() {
        System.out.println("Welcome to the ATM!");

        boolean authenticated = false;
        while (!authenticated) {
            System.out.print("Enter account number: ");
            String accountNumber = scanner.nextLine();
            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine();

            String hashedPin = hashPin(pin);

            for (Account account : accounts) {
                if (account.getAccountNumber().equals(accountNumber) && account.verifyPin(hashedPin)) {
                    currentAccount = account;
                    authenticated = true;
                    break;
                }
            }

            if (!authenticated) {
                System.out.println("Invalid account number or PIN. Please try again.");
            }
        }

        int choice;
        do {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Mini Statement");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    miniStatement();
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private void checkBalance() {
        System.out.println("Your current balance is: $" + currentAccount.getBalance());
    }

    private void deposit() {
        System.out.print("Enter amount to deposit: $");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        if (amount > 0) {
            currentAccount.deposit(amount);
            currentAccount.addTransaction("Deposit", amount);
            System.out.println("Deposited $" + amount + ". New balance is: $" + currentAccount.getBalance());
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    private void withdraw() {
        System.out.print("Enter amount to withdraw: $");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        if (amount > 0 && amount <= currentAccount.getBalance()) {
            currentAccount.withdraw(amount);
            currentAccount.addTransaction("Withdraw", amount);
            System.out.println("Withdrew $" + amount + ". New balance is: $" + currentAccount.getBalance());
        } else {
            System.out.println("Invalid amount or insufficient funds.");
        }
    }

    private void miniStatement() {
        System.out.println("Mini Statement:");
        List<String> transactions = currentAccount.getTransactions();
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (String transaction : transactions) {
                System.out.println(transaction);
            }
        }
    }

    private String hashPin(String pin) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(pin.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    abstract class Account {
        private String accountNumber;
        private double balance;
        private String hashedPin;
        private List<String> transactions;

        public Account(String accountNumber, double balance, String hashedPin) {
            this.accountNumber = accountNumber;
            this.balance = balance;
            this.hashedPin = hashedPin;
            this.transactions = new ArrayList<>();
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public double getBalance() {
            return balance;
        }

        public boolean verifyPin(String hashedPin) {
            return this.hashedPin.equals(hashedPin);
        }

        public void deposit(double amount) {
            this.balance += amount;
        }

        public void withdraw(double amount) {
            this.balance -= amount;
        }

        public void addTransaction(String type, double amount) {
            transactions.add(type + ": $" + amount + " | Balance: $" + getBalance());
        }

        public List<String> getTransactions() {
            return transactions;
        }

        public abstract String getAccountType();
    }

    class SavingsAccount extends Account {
        public SavingsAccount(String accountNumber, double balance, String hashedPin) {
            super(accountNumber, balance, hashedPin);
        }

        @Override
        public String getAccountType() {
            return "Savings";
        }
    }

    class CurrentAccount extends Account {
        public CurrentAccount(String accountNumber, double balance, String hashedPin) {
            super(accountNumber, balance, hashedPin);
        }

        @Override
        public String getAccountType() {
            return "Current";
        }
    }
}
