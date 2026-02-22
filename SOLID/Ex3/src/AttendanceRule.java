public class AttendanceRule implements VanillaRule {

    private final int minAttendance;

    public AttendanceRule(int minAttendance) {
        this.minAttendance = minAttendance;
    }

    @Override
    public RuleResult evaluate(StudentProfile profile) {
        if (profile.attendancePct < minAttendance) {
            return RuleResult.fail("attendance below " + minAttendance);
        }
        return RuleResult.pass();
    }
}