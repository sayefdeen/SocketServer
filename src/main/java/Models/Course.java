package Models;

import java.util.UUID;

public class Course {
    private UUID id;
    private String name;

    public Course(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
}
