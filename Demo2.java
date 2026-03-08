import java.sql.*;
public class Demo2 {
    private static final String url="jdbc:mysql://localhost:3306/mydb";

    private static final String username="root";

    private static final String passsword = "SIDDhesh@09012005";
    private static String query = "INSERT INTO student(id,name,marks) VALUES (6,'Rohit',6);";

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
            int roseAffec = st.executeUpdate(query);
            if(roseAffec>0){
                System.out.println("INSERT SUCCESSFULL "+roseAffec);
            }else{
                System.out.println("unsuccessfull");
            }
        }catch(SQLException S){
            System.out.println(S.getMessage());
        }
    }
}