package Services;

import DataBase.ConnectionPool;
import Log.Logger;
import at.favre.lib.crypto.bcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class StudentsService implements CRUD{

    @Override
    public void add(Object object){
        try{
            Connection con = ConnectionPool.getConnection();
            String insertQuery = "INSERT INTO uni.users (uuid,email,password) VALUES (?,?,?)";
            PreparedStatement ps = con.prepareStatement(insertQuery);
            ps.setString(1,(((DAO.Students) object).getId()));
            ps.setString(2,((DAO.Students) object).getEmail());
            ps.setString(3,((DAO.Students) object).getPassword());
            ps.execute();
        }catch (Exception e){
            Logger.getLogger().addLog("Something went Bad " + e.getMessage());
        }
    }

    @Override
    public void update(int id) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Object get(String id) {
        return new Object();
    }

    public DAO.Students getUser(String userName) {
        DAO.Students student = null;
        try{
            Connection con = ConnectionPool.getConnection();
            String selectQuery = "SELECT uuid,email,password FROM uni.users WHERE email = ? ";
            PreparedStatement ps = con.prepareStatement(selectQuery);
            ps.setString(1,userName);
            ResultSet rs =  ps.executeQuery();
            while(rs.next()){
                student = new DAO.Students();
                student.setId(rs.getString("uuid"));
                student.setEmail(rs.getString("email"));
                student.setPassword(rs.getString("password"));
            }
        }catch (Exception e){
            Logger.getLogger().addLog("Something went Bad " + e.getMessage());
        }
        return student;
    }

    public boolean checkPass(String reqPass, String dbPass){
        return BCrypt.verifyer().verify(reqPass.toCharArray(), dbPass).verified;
    }
}
