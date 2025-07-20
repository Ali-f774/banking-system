import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Customer customer1 = new Customer("Ali","ali@gmail.com","09021234567");
        Customer customer2 = new Customer("Omid","omid@gmail.com","09101234587");
        Customer customer3 = new Customer("Zahra","zahra@gmail.com","09221234654");
        Customer customer4 = new Customer("Sara","sara@gmail.com","09371234867");
        Customer customer5 = new Customer("Reza","reza@gmail.com","09361265267");

        BankManagement bankManagement = new BankManagement();

        bankManagement.addAccount(1,10);
        bankManagement.addAccount(2,20);
        bankManagement.addAccount(3,30);
        bankManagement.addAccount(4,40);
        bankManagement.addAccount(5,50);

        bankManagement.deposit(10,100);
        bankManagement.deposit(20,100);
        bankManagement.deposit(30,100);
        bankManagement.deposit(40,100);
        bankManagement.deposit(50,100);

        bankManagement.withdraw(50,77);
        bankManagement.withdraw(10,70.5);

        bankManagement.transfer(30,10,10.5);
        bankManagement.transfer(20,50,90);

        bankManagement.deleteAccount(40);

    }
}
