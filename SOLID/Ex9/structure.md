```
SOLID/Ex9/src/
├── Main.java
├── EvaluationPipeline.java
├── Submission.java
├── Rubric.java
├── SimpleConsole.java
├── TempFiles.java
│
├── IPlagiarismChecker.java              [interface]
│   └── int check(Submission s)
│       └── PlagiarismChecker.java       [implements IPlagiarismChecker]
│
├── ICodeGrader.java                     [interface]
│   └── int grade(Submission s, Rubric r)
│       └── CodeGrader.java              [implements ICodeGrader]
│
└── IReportWriter.java                   [interface]
    └── String write(Submission s, int plag, int code)
        └── ReportWriter.java            [implements IReportWriter]
```