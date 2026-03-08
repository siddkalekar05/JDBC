import java.sql.*;

import static java.lang.Class.forName;

class PrepareDemo2{
    private static final String url="jdbc:mysql://localhost:3306/mydb";

    private static final String username="root";

    private static final String passsword = "SIDDhesh@09012005";
    private static final String query = "INSERT INTO student(id,name,marks)VALUES(?,?,?)";
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
            ps.setInt(1,6);
            ps.setString(2,"Chetan");
            ps.setInt(3,8);
          int changes = ps.executeUpdate();
            if(changes>0){
                System.out.println("Data is Successfully Updated");
            }else{
                System.out.println("DATA updation failed");
            }
            c.close();
            ps.close();

        }catch(SQLException s){
            System.out.println(s.getMessage());
        }
    }
}