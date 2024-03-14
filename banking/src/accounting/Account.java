package accounting;

public class Account {
    private double balance;

    public Account() {
        this.balance = 0;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public double withdraw(double amount) {
        if(amount <= this.balance) {
            this.balance -= amount;
            return amount;
        }
        return 0;
    }

    public double getBalance() {
        return balance;
    }
}
