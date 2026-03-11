```
SOLID/Ex1/src/
├── Main.java
├── OnboardingService.java
├── StudentRecord.java
├── ParsedInput.java
├── IdUtil.java
├── Printer.java
├── TextTable.java
├── ConsoleInput.java
│
├── VanillaParser.java                   [interface]
│   └── ParsedInput parse(String raw)
│       └── StudentInputParser.java      [implements VanillaParser]
│
├── VanillaValidator.java                [interface]
│   └── List<String> validate(ParsedInput input)
│       └── StudentValidator.java        [implements VanillaValidator]
│
└── StudentRepository.java               [interface]
    ├── void save(StudentRecord record)
    └── int count()
        └── FakeDb.java                  [implements StudentRepository]
```