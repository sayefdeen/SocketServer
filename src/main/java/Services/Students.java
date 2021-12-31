package Services;

import DataBase.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;


public class Students implements CRUD{

    @Override
    public void add(Object object) throws Exception {
        Connection con = ConnectionPool.getConnection();
        String insertQuery = "INSERT INTO uni.users (uuid,name,password) VALUES (?,?,?)";
        PreparedStatement ps = con.prepareStatement(insertQuery);
        ps.setString(1,(((Models.Students) object).getId()).toString());
        ps.setString(2,((Models.Students) object).getName());
        ps.setString(3,((Models.Students) object).getPassword());
        ps.execute();
        con.close();
    }

    @Override
    public void update(int id) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void get(int id) {

    }
}
