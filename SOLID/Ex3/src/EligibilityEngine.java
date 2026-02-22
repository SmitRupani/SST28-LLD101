import java.util.ArrayList;
import java.util.List;

public class EligibilityEngine {
    private final FakeEligibilityStore store;
    private final List<VanillaRule> rules;

    public EligibilityEngine(FakeEligibilityStore store, List<VanillaRule> rules) {
        this.store = store;
        this.rules = rules;
    }

    public void runAndPrint(StudentProfile s) {
        EligibilityEngineResult result = evaluate(s);
        new ReportPrinter().print(s, result);
        store.save(s.rollNo, result.status);
    }

    public EligibilityEngineResult evaluate(StudentProfile s) {
        List<String> reasons = new ArrayList<>();
        String status = "ELIGIBLE";

        for (VanillaRule rule : rules) {
            RuleResult result = rule.evaluate(s);
            if (!result.passed) {
                status = "NOT_ELIGIBLE";
                reasons.add(result.reason);
                break;
            }
        }

        return new EligibilityEngineResult(status, reasons);
    }
}