/*
Problem Statement:
Implement a bank transaction system that transfers money from one account to another
using JDBC and MySQL while ensuring database consistency.

The system should:
1. Debit amount from sender's account.
2. Credit the same amount to receiver's account.
3. Prevent transfer if sender has insufficient balance.
4. Prevent transfer if sender and receiver accounts are the same.
5. Maintain transaction safety using commit and rollback.

Example:

Sender Account Balance: 5000
Receiver Account Balance: 2000
Transfer Amount: 1000

After Transaction:

Sender Balance: 4000
Receiver Balance: 3000

Approach:
1. Establish a connection with MySQL database using JDBC.
2. Disable auto-commit mode to manually control the transaction.
3. Take sender account number, receiver account number, and amount as input.
4. Validate that sender and receiver are not the same.
5. Execute Debit Query:
      UPDATE account
      SET balance = balance - amount
      WHERE account_no = sender AND balance >= amount
6. If debit fails (0 rows affected), rollback transaction (insufficient balance).
7. Execute Credit Query:
      UPDATE account
      SET balance = balance + amount
      WHERE account_no = receiver
8. If credit fails, rollback transaction.
9. If both operations succeed, commit the transaction.
10. Close resources (PreparedStatement, Connection, Scanner).

Concepts Used:
• JDBC (Java Database Connectivity)
• PreparedStatement
• Transactions
• Commit & Rollback
• MySQL Database
• ACID Properties

Time Complexity:
O(1)
(Database update operations on indexed account number)

Space Complexity:
O(1)
(No extra data structures used)
*/

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
