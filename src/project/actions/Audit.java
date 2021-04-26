package project.actions;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Audit {
    private static Audit instance = null;
    private static final String path = "./src/project/files/audit.csv";
    private static BufferedWriter writer;
    private Audit() {
        try {
            writer = new BufferedWriter(new FileWriter(path));
        }
        catch(FileNotFoundException e) {
            System.out.println("Output file does not exist");
        }
        catch(IOException e) {
            System.out.println("Failed to write to file");
        }
    }

    public static Audit getInstance() {
        if (instance == null) {
            instance = new Audit();
        }
        return instance;
    }

    public void add(int action) {
        String message = "";
        if (action == 1) {
            message = "events listed";
        }
        else if (action == 2) {
            message = "event added";
        }
        else if (action == 3) {
            message = "event generated";
        }
        else if (action == 4) {
            message = "locations listed";
        }
        else if (action == 5) {
            message = "location added";
        }
        else if (action == 6) {
            message = "location generated";
        }
        else if (action == 7) {
            message = "clients listed";
        }
        else if (action == 8) {
            message = "client added";
        }
        else if (action == 9) {
            message = "client generated";
        }
        else if (action == 10) {
            message = "event availability checked";
        }
        else if (action == 11) {
            message = "ticket bought";
        }
        else if (action == 12) {
            message = "donation made";
        }
        else if (action == 13) {
            message = "fundraiser donations query";
        }
        else if (action == 14) {
            message = "fundraiser goal query";
        }
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
        try {
            writer.write(message + "," + timeStamp + "\n");
        }
        catch (IOException e) {
            System.out.println("Failed to write to file");
        }
    }

    public void close() {
        try {
            writer.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
