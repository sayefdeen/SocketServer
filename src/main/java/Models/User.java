package Models;

import at.favre.lib.crypto.bcrypt.BCrypt;

import java.io.Serializable;
import java.util.UUID;

public abstract class User implements Serializable {

    private UUID id;
    private String name;
    private String password;

    public User(String name, String password,boolean isNew) {
        this.id = UUID.randomUUID();;
        this.name = name;
        if(isNew && checkIfNotThere()){
            this.password = bcryptPass(password);
        }else {
            this.password = password;
        }
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    private String bcryptPass(String password) {
        return BCrypt.withDefaults().hashToString(5,password.toCharArray());
    }

    private boolean checkIfNotThere(){
        return true;
    }

}
