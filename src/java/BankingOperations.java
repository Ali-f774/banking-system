public interface BankingOperations {
    void addAccount(int customerId,long accountNumber);
    void deposit(long accountNumber,double amount);
    void withdraw(long accountNumber,double amount);
    void transfer(long originAccountNumber,long destinationAccountNumber,double amount);
    void deleteAccount(long accountNumber);
}
