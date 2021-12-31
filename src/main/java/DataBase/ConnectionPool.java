package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionPool {
    private static Connection con;

    private ConnectionPool(){}

    public static Connection getConnection() throws Exception{
        if(con == null){
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students?useSSL=false&serverTimezone=UTC","root","0000");
            return con;
        }
        return con;
    }
}
