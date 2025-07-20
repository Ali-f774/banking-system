import java.sql.SQLException;

public interface BankingOperations {
    void addAccount(int customerId,long accountNumber) throws SQLException;
    void deposit(long accountNumber,double amount) throws SQLException;
    void withdraw(long accountNumber,double amount) throws SQLException;
    void transfer(long originAccountNumber,long destinationAccountNumber,double amount) throws SQLException;
    void deleteAccount(long accountNumber) throws SQLException;
}
