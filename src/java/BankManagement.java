import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class BankManagement implements BankingOperations{
    private String sql = "INSERT INTO transaction (account_number,type,amount,date,details) VALUES (?,?,?,?,?)";
    private final PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql);

    public BankManagement() throws SQLException {
    }

    @Override
    public void addAccount(int customerId, long accountNumber) throws SQLException {
        sql = "INSERT INTO account(customer_id,account_number,balance) VALUES ("+customerId+","+accountNumber+",0)";
        Statement statement = DBConnection.getConnection().createStatement();
        statement.executeUpdate(sql);
        System.out.println("Adding Account Successfully");
    }

    @Override
    public void deposit(long accountNumber, double amount) throws SQLException{
        sql = "UPDATE account SET balance = balance + "+amount+" WHERE account_number = "+accountNumber;
        Statement statement = DBConnection.getConnection().createStatement();
        statement.executeUpdate(sql);
        addTransaction(accountNumber,"Deposit",amount, Date.valueOf(LocalDate.now()),"deposit money");
        System.out.println("Successful Deposit");

    }

    @Override
    public void withdraw(long accountNumber, double amount) throws SQLException{
        sql = "UPDATE account SET balance = balance - "+amount+" WHERE account_number = "+accountNumber;
        Statement statement = DBConnection.getConnection().createStatement();
        statement.executeUpdate(sql);
        addTransaction(accountNumber,"Withdraw",amount, Date.valueOf(LocalDate.now()),"withdraw money");
        System.out.println("Successful Withdraw");
    }

    @Override
    public void transfer(long originAccountNumber, long destinationAccountNumber, double amount)throws SQLException {
        sql = "UPDATE account SET balance = balance - "+amount+" WHERE account_number = "+originAccountNumber;
        Statement statement = DBConnection.getConnection().createStatement();
        statement.executeUpdate(sql);
        addTransaction(originAccountNumber,"Transfer",-amount, Date.valueOf(LocalDate.now()),"Transfer money");
        sql = "UPDATE account SET balance = balance + "+amount+" WHERE account_number = "+destinationAccountNumber;
        statement.executeUpdate(sql);
        addTransaction(destinationAccountNumber,"Receive",amount, Date.valueOf(LocalDate.now()),"Receive money");
        System.out.println("Successful Transfer");
    }

    @Override
    public void deleteAccount(long accountNumber)throws SQLException {
        sql = "DELETE FROM account WHERE account_number = "+accountNumber;
        Statement statement = DBConnection.getConnection().createStatement();
        statement.executeUpdate(sql);
        System.out.println("delete account is successfully");
    }
    private void addTransaction(long accountNumber, String type, double amount, Date date, String details) throws SQLException{
        preparedStatement.setLong(1,accountNumber);
        preparedStatement.setString(2,type);
        preparedStatement.setDouble(3,amount);
        preparedStatement.setDate(4,date);
        preparedStatement.setString(5,details);
        preparedStatement.addBatch();
    }
    public void registerTransaction() throws SQLException {
        preparedStatement.executeBatch();
    }
}
