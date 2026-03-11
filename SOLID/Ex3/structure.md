```
SOLID/Ex3/src/
├── Main.java
├── EligibilityEngine.java
├── EligibilityEngineResult.java
├── FakeEligibilityStore.java
├── ReportPrinter.java
├── StudentProfile.java
├── RuleResult.java
├── RuleInput.java
├── LegacyFlags.java
├── Numbers.java
│
└── VanillaRule.java                     [interface]
    └── RuleResult evaluate(StudentProfile profile)
        ├── AttendanceRule.java          [implements VanillaRule]
        ├── CgrRule.java                 [implements VanillaRule]
        ├── CreditsRule.java             [implements VanillaRule]
        └── DisciplinaryRule.java        [implements VanillaRule]
```