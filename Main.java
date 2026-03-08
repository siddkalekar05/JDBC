import java.sql.*;
public class Main {
    private static final String url="jdbc:mysql://localhost:3306/mydb";

    private static final String username="root";

    private static final String passsword = "SIDDhesh@09012005";

    public static void main(String[] args) {
        System.out.printf("Hello and welcome!");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            Connection c = DriverManager.getConnection(url,username,passsword);
            Statement st = c.createStatement();
            String query = "SELECT *FROM student";
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                int id=rs.getInt("id");
                String name = rs.getString("name");
                int marks = rs.getInt("Marks");
                System.out.println("ID : "+id);
                System.out.println("Name : "+name);
                System.out.println("Marks : "+marks);

            }

        }catch(SQLException S){
            S.printStackTrace();
        }
    }
}