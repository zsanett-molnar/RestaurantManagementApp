package Data;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class FileWriter {


    public static void writeInFile(String text, String fileName) {
    try {
        java.io.FileWriter myWriter = new java.io.FileWriter(fileName);
        myWriter.write(text);
        myWriter.close();
        System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }
}


}
