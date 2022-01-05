package DAO;

import at.favre.lib.crypto.bcrypt.BCrypt;

import java.io.Serializable;
import java.util.UUID;

public abstract class User implements Serializable {

    private  String id;
    private  String email;
    private  String password;

    public User() {}

    public User(String email, String password,boolean isNew) {
        this.email = email;
        this.id = UUID.randomUUID().toString();
        if(isNew){
            this.password = bcryptPass(password);
        }else{
            this.password = password;
        }
    }

    public User(String id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id.toString();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String bcryptPass(String password) {
        return BCrypt.withDefaults().hashToString(5,password.toCharArray());
    }

}
