package DAO;

public class Students extends User {


    public Students() {
        super();
    }

    public Students(String email, String password,boolean isNew) throws Exception {
        super(email, password,isNew);
    }

    public Students(String uuid,String name, String password) {
        super(uuid,name, password);
    }



}
