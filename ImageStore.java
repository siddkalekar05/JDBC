import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.*;
public class ImageStore {
    private static final String url="jdbc:mysql://localhost:3306/image";

    private static final String username="root";

    private static final String passsword = "SIDDhesh@09012005";
    private static final String folder = "C:\\Users\\Siddhesh\\OneDrive\\Desktop\\JDBC_IMAGEDATA\\";

    public static void main(String[] args)throws IOException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded Successfully");
        }catch(ClassNotFoundException c){
            System.out.println(c.getMessage());
        }
        try{
            Connection c = DriverManager.getConnection(url,username,passsword);
            System.out.println("Connection Establish Successfully");
            String query = "SELECT *FROM image_table WHERE image_id=(?)";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1,1);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                byte image_data[] = rs.getBytes("image_data");
                String image_path = folder+" Extrated_image";
                OutputStream os = new FileOutputStream(image_path);
                os.write(image_data);
                System.out.println("Images extracted Successfully !");
            }else{
                System.out.println("Image not found");
            }
        }catch(SQLException s ){
            System.out.println(s.getMessage());
        }
    }
}
