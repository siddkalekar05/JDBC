import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
public class ImageDemo1 {
    private static final String url="jdbc:mysql://localhost:3306/image";

    private static final String username="root";

    private static final String passsword = "SIDDhesh@09012005";
    private static final String image = "C:\\Users\\Siddhesh\\OneDrive\\Pictures\\Screenshots\\Screenshot 2025-02-20 191401.png";
    public static void main(String[] args)throws IOException {
      try{
          Class.forName("com.mysql.cj.jdbc.Driver");
          System.out.println("Driver_loaded");
      }catch(ClassNotFoundException ce){
          System.out.println(ce.getMessage());
      }
      try{
          String query = "INSERT INTO image_table(image_data)VALUES (?)";
          Connection c = DriverManager.getConnection(url,username,passsword);
          System.out.println("Connection_Establish");
          PreparedStatement ps = c.prepareStatement(query);
          FileInputStream fis = new FileInputStream(image);
          byte[] imagestore = new byte[fis.available()];
          ps.setBytes(1,imagestore);
          int affect = ps.executeUpdate();
          if(affect>0){
              System.out.println("Insertion successfull");
          }else{
              System.out.println("Failed");
          }
      }catch(SQLException s){
          System.out.println(s.getMessage());
      }
    }
}
