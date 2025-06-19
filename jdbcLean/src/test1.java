import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class test1 {
    public static void main(String[] args){
        String url = "jdbc:mysql://localhost:3306/playground";
        String username = "root";
        String password = "postgres";

        try{
            Connection conn = DriverManager.getConnection(url, username, password);
            String x = "Select * from employees";
            PreparedStatement st = conn.prepareStatement(x);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                System.out.println(rs.getInt("emp_id")+ " " + rs.getString("name")+" " + rs.getString("dept"));
            }
            String x1 = "delete from employees where name = ? or name= ?";
            PreparedStatement st1 = conn.prepareStatement(x1);
            st1.setString(1,"carol");
            st1.setString(2, "frank");
            System.out.println(st1.executeUpdate());

            rs = st.executeQuery();
            while(rs.next()){
                System.out.println(rs.getInt("emp_id")+ " " + rs.getString("name")+" " + rs.getString("dept"));
            }


        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
