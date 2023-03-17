package Data;

import java.io.Serializable;

public class Administrator extends User implements Serializable {

    public Administrator(String username, String password) {
        super(username,password,"administrator");
    }
}
