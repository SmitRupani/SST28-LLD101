public abstract class Button {
    protected boolean isPressed;
    protected boolean isEnabled;

    public Button() {
        this.isPressed = false;
        this.isEnabled = true;
    }

    public void press() {
        if (isEnabled) {
            isPressed = true;
            onPress();
        }
    }

    public void release() {
        isPressed = false;
    }

    public boolean isPressed() {
        return isPressed;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    protected abstract void onPress();
}
