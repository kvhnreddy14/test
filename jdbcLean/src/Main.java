import java.sql.*;

public class Main {
    public static void main(String[] args)
    {
        String url = "jdbc:mysql://localhost:3306/playground";
        String username = "root";
        String password = "postgres";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, username, password);
            String x = "Select * from employees";

            PreparedStatement stmt = conn.prepareStatement(x);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                System.out.println(rs.getInt("emp_id") + " " + rs.getString("name") + " " + rs.getString("dept")+" "+ rs.getString("dept"));
            }

//            rs.close();
            rs = stmt.executeQuery();
            while(rs.next()){
                System.out.println(rs.getInt("emp_id") + " " + rs.getString("name") + " " + rs.getString("dept")+" "+ rs.getString("dept"));
            }

            String x1 = "Delete from employees where emp_id = ?";
            PreparedStatement stmt1 = conn.prepareStatement(x1);
            stmt1.setInt(1, 2);
            System.out.println(stmt1.executeUpdate());
            String x2 = "Select * from employees";
            PreparedStatement stmt2 = conn.prepareStatement(x2);
            ResultSet rs2 = stmt2.executeQuery();
            rs.close();
            while(rs2.next()){
                System.out.println(rs2.getInt("emp_id") +" " + rs .getString("name") + " " + rs2.getString("dept"));
            }
        }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }
}