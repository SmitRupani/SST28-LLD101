public class CreditsRule implements VanillaRule {
    private final int minCredits;

    public CreditsRule(int minCredits) { this.minCredits = minCredits; }

    @Override
    public RuleResult evaluate(StudentProfile profile) {
        if (profile.earnedCredits < minCredits)
            return RuleResult.fail("credits below " + minCredits);
        return RuleResult.pass();
    }
}