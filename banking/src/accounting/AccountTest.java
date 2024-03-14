package accounting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountTest {

    Account testAccount;

    @BeforeEach public void initialize() {
        testAccount = new Account();
    }

    @Test
    void testDeposit() {
        double testDepAmount = 30.5;
        testAccount.deposit(testDepAmount);
        assertEquals(30.5, testAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        double startAmount = 50, withdrawAmount = 40;
        testAccount.deposit(startAmount);

        assertEquals(40, testAccount.withdraw(withdrawAmount));
    }

    @Test
    void testWithdrawTooMuch() {
        double startAmount = 50, withdrawAmount = 100;
        testAccount.deposit(startAmount);
        assertEquals(0, testAccount.withdraw(withdrawAmount));
    }

    @Test
    void testGetBalance() {
        assertEquals(0, testAccount.getBalance());
    }
}