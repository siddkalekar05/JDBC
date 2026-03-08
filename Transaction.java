import java.sql.*;
import java.util.*;

public class Transaction {
    private static final String url = "jdbc:mysql://localhost:3306/mydb";

    private static final String username = "root";

    private static final String pass = "SIDDhesh@09012005";

    private static final String wQuery = "UPDATE account SET balance = balance - ? WHERE account_no = ? AND balance >= ?";

    private static final String dQuery = "UPDATE account SET balance = balance + ? WHERE account_no = ?";

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded");
        }catch(ClassNotFoundException c){
            System.out.println(c.getMessage());
        }
        try{
            Connection c = DriverManager.getConnection(url,username,pass);
            c.setAutoCommit(false);
            System.out.println("Connection Esstablish");
            PreparedStatement wps = c.prepareStatement(wQuery);
            PreparedStatement dps = c.prepareStatement(dQuery);
            System.out.println("Enter account_no : ");
            String acc_no = sc.nextLine();
            System.out.println("Choose Operation  : ");
            System.out.println("1.Widrawal  \n 2.Deposite ");
            int choice = sc.nextInt();
            sc.nextLine();
            int affectw=0;
            int affectd=0;
            if(choice==1){
                System.out.println("Enter amount for widrawal : ");
                double amount = sc.nextDouble();
                sc.nextLine();
                wps.setDouble(1,amount);
                wps.setString(2,acc_no);
                wps.setDouble(3,amount);
                 affectw = wps.executeUpdate();
            } else if (choice==2) {
                System.out.println("Enter amount for deposit : ");
                double amount = sc.nextDouble();
                sc.nextLine();
                dps.setDouble(1,amount);
                dps.setString(2,acc_no);
                 affectd = dps.executeUpdate();
            }else{
                System.out.println("Invalid choice !!!");
                c.rollback();
                System.exit(0);
            }

            if(affectw>0 || affectd>0){
                c.commit();
                System.out.println("Transaction Successful !!!");
            }else{
                c.rollback();
                System.out.println("Transaction failed !!!");
            }
            dps.close();
            wps.close();
            sc.close();
            c.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
