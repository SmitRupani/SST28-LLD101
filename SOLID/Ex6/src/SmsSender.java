public class SmsSender extends NotificationSender {
    public SmsSender(AuditLog audit) { super(audit); }

    @Override
    public boolean canSend(Notification n) {
        return n != null && n.phone != null;
    }

    @Override
    public void send(Notification n) {
        // SMS channel has no subject concept — omitting it is channel-appropriate,
        // not an LSP violation, because the base contract doesn't require subject use.
        System.out.println("SMS -> to=" + n.phone + " body=" + n.body);
        audit.add("sms sent");
    }
}