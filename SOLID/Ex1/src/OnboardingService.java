import java.util.List;

public class OnboardingService {
    private final StudentRepository repo;
    private final VanillaParser parser;
    private final VanillaValidator validator;
    private final Printer printer;

    public OnboardingService(StudentRepository repo,
                             VanillaParser parser,
                             VanillaValidator validator,
                             Printer printer) {
        this.repo = repo;
        this.parser = parser;
        this.validator = validator;
        this.printer = printer;
    }

    public void registerFromRawInput(String raw) {
        printer.printInput(raw);

        ParsedInput input = parser.parse(raw);
        List<String> errors = validator.validate(input);

        if (!errors.isEmpty()) {
            printer.printErrors(errors);
            return;
        }

        String id = IdUtil.nextStudentId(repo.count());
        StudentRecord rec = new StudentRecord(id, input.name, input.email, input.phone, input.program);
        repo.save(rec);

        printer.printSuccess(rec, repo.count());
    }
}