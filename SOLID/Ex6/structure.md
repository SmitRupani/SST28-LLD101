```
SOLID/Ex6/src/
├── Main.java
├── Notification.java
├── AuditLog.java
├── SenderConfig.java
├── ConsolePreview.java
│
└── NotificationSender.java              [abstract class]
    ├── abstract boolean canSend(Notification n)
    ├── String rejectionReason(Notification n)
    └── abstract void send(Notification n)
        ├── EmailSender.java             [extends NotificationSender]
        ├── SmsSender.java               [extends NotificationSender]
        └── WhatsAppSender.java          [extends NotificationSender]
```