/*
Problem Statement:
Delete a specific record from the "student" table in a MySQL database
using JDBC in Java.

The program connects to the database and removes the student record
whose ID is 6.

Example:

Table: student

Before Deletion:
ID | Name   | Age
------------------
1  | Rahul  | 20
6  | Aman   | 21
7  | Neha   | 22

Query Executed:
DELETE FROM student WHERE id = 6;

After Deletion:
ID | Name   | Age
------------------
1  | Rahul  | 20
7  | Neha   | 22


Approach:
1. Define database connection details (URL, username, password).
2. Load MySQL JDBC Driver using Class.forName().
3. Establish connection using DriverManager.getConnection().
4. Create a Statement object to execute SQL query.
5. Execute DELETE query using executeUpdate().
6. executeUpdate() returns number of rows affected.
7. If rows affected > 0 → deletion successful.
8. Otherwise → record not found or deletion failed.
9. Handle SQL exceptions if any error occurs.

Concepts Used:
• JDBC (Java Database Connectivity)
• MySQL Database
• SQL DELETE Query
• Statement Interface
• Exception Handling

Time Complexity:
O(1)
(Database directly deletes record using primary key)

Space Complexity:
O(1)
(No additional data structures used)
*/

import java.sql.*;
public class Delete {
    private static final String url="jdbc:mysql://localhost:3306/mydb";

    private static final String username="root";

    private static final String passsword = "SIDDhesh@09012005";
    private static String query = "DELETE FROM student WHERE id=6;";

    public static void main(String[] args) {
        System.out.println("Hello and welcome!");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        try{
            Connection c = DriverManager.getConnection(url,username,passsword);
            Statement st = c.createStatement();
            int rowsaffect = st.executeUpdate(query);
            if(rowsaffect>0){
                System.out.println("Successfully deleted "+rowsaffect);
            }else {
                System.out.println("Unsuccessfull ");
            }

        }catch(SQLException S){
            System.out.println(S.getMessage());
        }
    }
}
