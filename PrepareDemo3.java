import java.sql.*;
import java.util.*;
class PrepareDemo3 {
    private static final String url = "jdbc:mysql://localhost:3306/mydb";

    private static final String username = "root";

    private static final String passsword = "SIDDhesh@09012005";

    private static final String query = "DELETE FROM student WHERE id=?";

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException ce){
            System.out.println(ce.getMessage());
        }
        try{
            Connection c = DriverManager.getConnection(url,username,passsword);
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1,7);
            int affect = ps.executeUpdate();
            if(affect>0){
                System.out.println("Updation successful");
            }else{
                System.out.println("Failed");
            }
        }catch(SQLException s){
            System.out.println(s.getMessage());
        }
    }

}