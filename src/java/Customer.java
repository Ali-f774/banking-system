import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Customer {
    private String name;
    private String email;
    private String phone;

    public Customer(String name, String email, String phone) throws SQLException{
        this.name = name;
        this.email = email;
        this.phone = phone;
        registerCustomer();
    }
    private void registerCustomer() throws SQLException {
        String sql = "INSERT INTO customer (name,email,phone) VALUES (?,?,?)";
        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setString(1,this.name);
        statement.setString(2,this.email);
        statement.setString(3,this.phone);
        statement.addBatch();
        statement.executeBatch();
    }
}
