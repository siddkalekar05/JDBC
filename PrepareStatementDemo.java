import java.sql.*;

import static java.lang.Class.forName;

class PrepareStatementDemo{
    private static final String url="jdbc:mysql://localhost:3306/mydb";

    private static final String username="root";

    private static final String passsword = "SIDDhesh@09012005";
    private static final String query = "SELECT *FROM student WHERE name=? and marks=?";
    public static  void main(String[] args){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivaer load Successfully");

        }catch(ClassNotFoundException c){
            System.out.println(c.getMessage());
        }
        try{
            Connection c = DriverManager.getConnection(url,username,passsword);
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1,"Siddhesh");
            ps.setInt(2,9);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int marks = rs.getInt("marks");
                System.out.println("ID : "+id);
                System.out.println("NAME : "+name);
                System.out.println("MARKS : "+marks);
            }
            c.close();
            ps.close();

        }catch(SQLException s){
            System.out.println(s.getMessage());
        }
    }
}