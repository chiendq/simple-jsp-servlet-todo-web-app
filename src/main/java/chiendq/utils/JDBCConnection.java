package chiendq.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/simple_todo_app";
    private static final String user = "root";
    private static final String password = "";
    private static Connection connection;

    public JDBCConnection() {
    }

    public static Connection getConnection()  {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, user, password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(){
        if(connection != null){
            try{
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
