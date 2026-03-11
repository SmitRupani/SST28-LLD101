```
proxy-reports/src/com/example/reports/
├── App.java
├── QuickCheck.java
├── ReportViewer.java
├── AccessControl.java
├── User.java
│
└── Report.java                          [interface]
    └── void display(User user)
        ├── RealReport.java              [implements Report]
        └── ReportProxy.java             [implements Report]
```