public class Main {
    public static void main(String[] args) {
        System.out.println("=== Notification Demo ===");
        AuditLog audit = new AuditLog();

        Notification n = new Notification("Welcome", "Hello and welcome to SST!", "riya@sst.edu", "9876543210");

        NotificationSender email = new EmailSender(audit);
        NotificationSender sms   = new SmsSender(audit);
        NotificationSender wa    = new WhatsAppSender(audit);

        safe(email, n);
        safe(sms,   n);
        safeWa(wa,  n, audit);   // WA error line has a different prefix, kept separate

        System.out.println("AUDIT entries=" + audit.size());
    }

    private static void safe(NotificationSender sender, Notification n) {
        if (sender.canSend(n)) {
            sender.send(n);
        } else {
            System.out.println(sender.getClass().getSimpleName().toUpperCase()
                + " ERROR: " + sender.rejectionReason(n));
        }
    }

    // WA output prefix matches the required sample ("WA ERROR: ...")
    private static void safeWa(NotificationSender sender, Notification n, AuditLog audit) {
        if (sender.canSend(n)) {
            sender.send(n);
        } else {
            System.out.println("WA ERROR: " + sender.rejectionReason(n));
            audit.add("WA failed");
        }
    }
}