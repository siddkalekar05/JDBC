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