public class CgrRule implements VanillaRule {

    private final double minCgr;

    public CgrRule(double minCgr) {
        this.minCgr = minCgr;
    }

    @Override
    public RuleResult evaluate(StudentProfile profile) {
        if (profile.cgr < minCgr) {
            return RuleResult.fail("CGR below " + minCgr);
        }
        return RuleResult.pass();
    }
}