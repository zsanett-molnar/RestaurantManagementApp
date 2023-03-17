package Data;

import java.io.Serializable;

public class Client extends User implements Serializable {

    public Client(String username, String password) {
        super(username, password, "client");
    }




}
