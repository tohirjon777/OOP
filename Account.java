public class Account extends Customer {
    private int balance;
    private int accountNumber;

    public Account(String FName, String LName, int accNum, int accBal) {
        setFirstName(FName);
        setLastName(LName);
        this.accountNumber = accNum;
        this.balance = accBal;
    }

    public int getBalance() {
        return balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void deposit(int amount) {
        this.balance += amount;
    }

    public void withdraw(int amount) {
        this.balance -= amount;
    }
}
