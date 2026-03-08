public class EmailSender extends NotificationSender {
    public EmailSender(AuditLog audit) { super(audit); }

    @Override
    public boolean canSend(Notification n) {
        return n != null && n.email != null;
    }

    @Override
    public void send(Notification n) {
        // LSP fix: body delivered as-is — no silent truncation that changes meaning
        System.out.println("EMAIL -> to=" + n.email + " subject=" + n.subject + " body=" + n.body);
        audit.add("email sent");
    }
}