public class AttendanceScanner implements AttendanceScannable {
    @Override public void powerOn() { System.out.println("AttendanceScanner ON"); }
    @Override public void powerOff() { System.out.println("AttendanceScanner OFF"); }

    @Override public int scanAttendance() { return 3; }
}
