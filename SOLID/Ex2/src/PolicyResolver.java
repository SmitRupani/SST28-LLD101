import java.util.Map;

public class PolicyResolver {
    private final Map<String, CustomerPolicy> policies;
    private final CustomerPolicy defaultPolicy;

    public PolicyResolver(Map<String, CustomerPolicy> policies, CustomerPolicy defaultPolicy) {
        this.policies = policies;
        this.defaultPolicy = defaultPolicy;
    }

    public CustomerPolicy resolve(String customerType) {
        return policies.getOrDefault(customerType.toLowerCase(), defaultPolicy);
    }
}