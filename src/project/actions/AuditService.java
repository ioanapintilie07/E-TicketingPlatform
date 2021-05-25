package project.actions;

import project.DB.AuditRepository;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AuditService {
    private static AuditService instance = null;
    private static final String path = "./src/project/files/audit.csv";
    private static BufferedWriter writer;
    private static AuditRepository auditRepository = new AuditRepository();
    private AuditService() {
    }

    public static AuditService getInstance() {
        if (instance == null) {
            instance = new AuditService();
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
        else if (action == 15) {
            message = "updated first name";
        }
        else if (action == 16) {
            message = "deleted client";
        }
        else if (action == 17) {
            message = "updated name of location";
        }
        else if (action == 18) {
            message = "deleted location";
        }
        else if (action == 19) {
            message = "searched for location";
        }
        else if (action == 20) {
            message = "concerts listed";
        }
        else if (action == 21) {
            message = "looked for concert";
        }
        else if (action == 22) {
            message = "concert updated";
        }
        else if (action == 23) {
            message = "concert deleted";
        }
        else if (action == 24) {
            message = "concert generated";
        }
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
        AuditObject auditObject = new AuditObject(message, timeStamp);
        auditRepository.createAuditObject(auditObject);
    }

    public void show() {
        List<AuditObject>  entries = new ArrayList<>(auditRepository.findAllEntries());
        if (entries.size() == 0) {
            System.out.println("\n Audit empty\n");
        }
        else {
            for (AuditObject entry : entries) {
                System.out.println(entry);
            }
        }
    }

    public void clear() {
        auditRepository.deleteAllEntries();
        System.out.println("\nAudit cleared\n");
    }
}
