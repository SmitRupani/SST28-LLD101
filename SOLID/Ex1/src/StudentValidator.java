import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StudentValidator implements VanillaValidator {
    private static final Set<String> ALLOWED_PROGRAMS =
        new HashSet<>(Arrays.asList("CSE", "AI", "SWE"));

    @Override
    public List<String> validate(ParsedInput input) {
        List<String> errors = new ArrayList<>();
        if (input.name.isBlank())
            errors.add("name is required");
        if (input.email.isBlank() || !input.email.contains("@"))
            errors.add("email is invalid");
        if (input.phone.isBlank() || !input.phone.chars().allMatch(Character::isDigit))
            errors.add("phone is invalid");
        if (!ALLOWED_PROGRAMS.contains(input.program))
            errors.add("program is invalid");
        return errors;
    }
}