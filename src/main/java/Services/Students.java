package Services;

import DataBase.ConnectionPool;
import at.favre.lib.crypto.bcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;


public class Students implements CRUD{

    @Override
    public void add(Object object) throws Exception {
        Connection con = ConnectionPool.getConnection();
        String insertQuery = "INSERT INTO uni.users (uuid,name,password) VALUES (?,?,?)";
        PreparedStatement ps = con.prepareStatement(insertQuery);
        ps.setString(1,(((DAO.Students) object).getId()));
        ps.setString(2,((DAO.Students) object).getName());
        ps.setString(3,((DAO.Students) object).getPassword());
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
    public void get(String id) {

    }

    public DAO.Students getUser(String userName) throws Exception{
        DAO.Students student = null;
        Connection con = ConnectionPool.getConnection();
        String selectQuery = "SELECT uuid,name,password FROM uni.users WHERE name = ? ";
        PreparedStatement ps = con.prepareStatement(selectQuery);
        ps.setString(1,userName);
        ResultSet rs =  ps.executeQuery();
        while(rs.next()){
            student = new DAO.Students();
            student.setId(rs.getString("uuid"));
            student.setName(rs.getString("name"));
            student.setPassword(rs.getString("password"));
        }
        return student;
    }

    public boolean checkPass(String reqPass, String dbPass){
        return BCrypt.verifyer().verify(reqPass.toCharArray(), dbPass).verified;
    }
}
