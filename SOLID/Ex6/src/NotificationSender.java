/**
 * Base contract (LSP):
 *   - send(n) must not throw; only call it after canSend(n) returns true.
 *   - canSend(n) expresses channel-specific constraints without tightening send().
 *   - rejectionReason(n) explains why canSend() returned false.
 *   - Callers use the safe() pattern: check canSend() first, never need instanceof.
 */
public abstract class NotificationSender {
    protected final AuditLog audit;
    protected NotificationSender(AuditLog audit) { this.audit = audit; }

    /** Returns true if this sender can handle the notification as-is. */
    public abstract boolean canSend(Notification n);

    /** Human-readable reason why canSend() returned false. */
    public String rejectionReason(Notification n) {
        return getClass().getSimpleName() + " cannot handle this notification";
    }

    /** Sends the notification. Only call when canSend() is true. Never throws. */
    public abstract void send(Notification n);
}