import java.sql.*;
import java.util.*;

public class Credit_Debit {

    private static final String url = "jdbc:mysql://localhost:3306/mydb";
    private static final String username = "root";
    private static final String pass = "SIDDhesh@09012005";

    private static final String DQuery =
            "UPDATE account SET balance = balance - ? WHERE account_no = ? AND balance >= ?";

    private static final String CQuery =
            "UPDATE account SET balance = balance + ? WHERE account_no = ?";

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            Connection c = DriverManager.getConnection(url,username,pass);
            c.setAutoCommit(false);

            System.out.println("Enter Sender account_no : ");
            String sender = sc.nextLine();

            System.out.println("Enter Receiver account_no : ");
            String receiver = sc.nextLine();

            System.out.println("Enter amount : ");
            double amount = sc.nextDouble();

            if(sender.equals(receiver)){
                System.out.println("Cannot transfer to same account!!!");
                return;
            }

            PreparedStatement dps = c.prepareStatement(DQuery);
            dps.setDouble(1,amount);
            dps.setString(2,sender);
            dps.setDouble(3,amount);

            int debitRows = dps.executeUpdate();

            if(debitRows == 0){
                c.rollback();
                System.out.println("Insufficient balance or sender not found!");
                return;
            }

            PreparedStatement cps = c.prepareStatement(CQuery);
            cps.setDouble(1,amount);
            cps.setString(2,receiver);

            int creditRows = cps.executeUpdate();

            if(creditRows == 0){
                c.rollback();
                System.out.println("Receiver account not found!");
                return;
            }

            c.commit();
            System.out.println("Transaction successful !!!");

            dps.close();
            cps.close();
            c.close();
            sc.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}