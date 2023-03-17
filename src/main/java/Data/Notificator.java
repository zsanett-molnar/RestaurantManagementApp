package Data;

import java.io.File;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Notificator {

    private static final String fileName = "OrderDetails.txt";

    public static void writeDetails(String msg) {
        try {
            java.io.FileWriter myWriter = new java.io.FileWriter(fileName);
            myWriter.write(msg);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static String readDetails() {
        String msg = "";

        try {
            Scanner myReader = new Scanner(new File(fileName));
            while (myReader.hasNextLine()) {
                msg = msg.concat(myReader.nextLine());
                msg = msg.concat("\n");
            }
            myReader.close();
        } catch (FileNotFoundException notFoundException) {
            notFoundException.printStackTrace();
        }

        return msg;
    }
}
