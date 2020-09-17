import java.sql.*;

public class jdbcConector {
    public static Connection getJBDC(){
        final  String url = "jdbc:mysql://localhost:3306/mydb";
        final String user = "root";
        final String password ="123456";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) throws SQLException {

        Connection connection = jdbcConector.getJBDC();

        if (connection != null) {
            System.out.println("Database connected !");
        } else {
            System.out.println("Fail !");
        }
        Statement stmt = connection.createStatement();

        String sql ="select * from country;";
        ResultSet resultSet =  stmt.executeQuery(sql);
    }
}
