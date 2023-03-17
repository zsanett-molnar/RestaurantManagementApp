package Data;

import java.io.Serializable;

public class Employee extends User implements Serializable {

    public Employee(String username, String password) {
        super(username, password, "employee");
    }

}
