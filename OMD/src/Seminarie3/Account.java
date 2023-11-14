package Seminarie3;

/**
 * Builder Pattern!
 */
public class Account {

    private String accountNumber;
    private String customerId;
    private String bankBranch;
    private double balance;
    private double interestRate;

    private Account(String accountNumber, String customerId, String bankBranch, double balance, double interestRate) {
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.bankBranch = bankBranch;
        this.balance = balance;
        this.interestRate = interestRate;
    }

    public Account(Builder builder) {
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String accountNumber;
        private String customerId;
        private String bankBranch;
        private double balance;
        private double interestRate;

        private Builder () {
        }

        public Builder accountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public Builder customerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder bankBranch(String bankBranch) {
            this.bankBranch = bankBranch;
            return this;
        }

        public Builder balance(double balance) {
            this.balance = balance;
            return this;
        }

        public Builder interestRate(double interestRate) {
            this.interestRate = interestRate;
            return this;
        }

        public Account build() {
            return new Account(this);
        }
    }
}

class Banking {
    public static void main (String[] args) {
        new Banking().run();
    }

    void run() {
        var account = 
            Account
            .builder()
            .accountNumber("123")
            .balance(100)
            .bankBranch("Avanza")
            .customerId("JP")
            .interestRate(20);
    }
}
