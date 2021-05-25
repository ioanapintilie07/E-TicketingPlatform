package project.actions;

public class AuditObject {
    protected String message;
    protected String timestamp;
    int auditId;

    public AuditObject(String message, String timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getAuditId() {
        return auditId;
    }

    public void setAuditId(int auditId) {
        this.auditId = auditId;
    }

    @Override
    public String toString() {
        return "Action:'" + message + '\'' +
                ", timestamp='" + timestamp;
    }
}
