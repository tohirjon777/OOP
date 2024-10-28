import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList<Account> accounts = new LinkedList<>();

        String file = "/Users/tokhirjonobidov/Desktop/2428279/Accounts.csv"; 
        ReadAccounts readAccounts = new ReadAccounts(file);

        LinkedList<String> firstNames = readAccounts.getFirstNames();
        LinkedList<String> lastNames = readAccounts.getLastNames();
        LinkedList<Integer> accountList = readAccounts.getAccounts();
        LinkedList<Integer> balanceList = readAccounts.getBalances();

        for (int i = 0; i < firstNames.size(); i++) {
            accounts.add(new Account(firstNames.get(i), lastNames.get(i), accountList.get(i), balanceList.get(i)));
        }

        for (Account account : accounts) {
            System.out.println("Account Number: " + account.getAccountNumber());
            System.out.println("Balance: " + account.getBalance());
            System.out.println("First Name: " + account.getFirstName());
            System.out.println("Last Name: " + account.getLastName());
        }

        Account account1 = accounts.get(0);
        Account account2 = accounts.get(1);

        System.out.println("Initial Balance of account1: " + account1.getBalance());
        System.out.println("Initial Balance of account2: " + account2.getBalance());

        account1.deposit(250);
        System.out.println("Balance of account1 after deposit: " + account1.getBalance());

        account2.withdraw(500);
        System.out.println("Balance of account2 after withdrawal: " + account2.getBalance());

        Transaction t = new Transaction();
        t.transfer(account1, account2, 250);

        System.out.println("Balance of account1 after transfer: " + account1.getBalance());
        System.out.println("Balance of account2 after transfer: " + account2.getBalance());
    }
}
