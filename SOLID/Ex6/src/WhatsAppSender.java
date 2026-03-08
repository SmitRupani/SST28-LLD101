public class WhatsAppSender extends NotificationSender {
    public WhatsAppSender(AuditLog audit) { super(audit); }

    @Override
    public boolean canSend(Notification n) {
        // E.164 requirement is expressed here, not inside send()
        return n != null && n.phone != null && n.phone.startsWith("+");
    }

    @Override
    public String rejectionReason(Notification n) {
        return "phone must start with + and country code";
    }

    @Override
    public void send(Notification n) {
        // LSP fix: no longer throws — send() only called when canSend() is true
        System.out.println("WA -> to=" + n.phone + " body=" + n.body);
        audit.add("wa sent");
    }
}