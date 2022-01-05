package Services;

import java.util.UUID;

public interface CRUD<T> {

    public void add(T object);

    public void update(int id) ;

    public void delete(int id) ;

    public T get(String id) throws Exception;
}
