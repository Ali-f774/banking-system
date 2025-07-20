import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BankManagement implements BankingOperations{
    private String sql;

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
        System.out.println("Successful Deposit");

    }

    @Override
    public void withdraw(long accountNumber, double amount) throws SQLException{
        sql = "UPDATE account SET balance = balance - "+amount+" WHERE account_number = "+accountNumber;
        Statement statement = DBConnection.getConnection().createStatement();
        statement.executeUpdate(sql);
        System.out.println("Successful Withdraw");
    }

    @Override
    public void transfer(long originAccountNumber, long destinationAccountNumber, double amount)throws SQLException {
        sql = "UPDATE account SET balance = balance ? "+amount+" WHERE account_number = ?";
        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setString(1,"-");
        statement.setString(2,originAccountNumber+"");
        statement.addBatch();
        statement.setString(1,"+");
        statement.setString(2,destinationAccountNumber+"");
        statement.addBatch();
        statement.executeBatch();
        System.out.println("Successful Transfer");
    }

    @Override
    public void deleteAccount(long accountNumber)throws SQLException {
        sql = "DELETE FROM account WHERE account_number = "+accountNumber;
        Statement statement = DBConnection.getConnection().createStatement();
        statement.executeUpdate(sql);
        System.out.println("delete account is successfully");
    }
}
