package Services;

import DataBase.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Testing {
    private int id;
    private String name;

    public Testing(){}

    public Testing(int id, String name){
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Testing> selectQuery() throws Exception{
        Connection connectionPool = ConnectionPool.getConnection();
        ArrayList<Testing> testing = new ArrayList<>();

        try{
            Statement stmt = connectionPool.createStatement();
            String sql = "SELECT * FROM users";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Testing testing1 = new Testing(id,name);
                testing.add(testing1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return testing;

    }
}
