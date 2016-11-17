import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import java.sql.*;


@RestController
@EnableAutoConfiguration
public class Example {

    @RequestMapping("/")
    String home() {
        return connect();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Example.class, args);
    }
    
    public String connect(){
        try{
         String url="jdbc:mysql://172.28.2.17:3306/siteweb";
         Connection conn = DriverManager.getConnection(url,"root","MYSECRET");
             Statement stmt = conn.createStatement();
            ResultSet rs;
 
            rs = stmt.executeQuery("SELECT * from test");
            while ( rs.next() ) {
                String lastName = rs.getString("colonne1");
                return lastName;
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
            return e.getMessage();
        }
        return "erreur..";
    }

}
