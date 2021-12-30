package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionPool {
    private static Connection con;

    private ConnectionPool(){}

    public static Connection getConnection() throws Exception{
        if(con == null){
            con = DriverManager.getConnection("jdbc:mysql://localhost:33066/students","root","root");
            return con;
        }
        return con;
    }
}
