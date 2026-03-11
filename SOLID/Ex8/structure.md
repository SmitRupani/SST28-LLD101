```
SOLID/Ex8/src/
├── Main.java
├── ClubConsole.java
├── BudgetLedger.java
├── MinutesBook.java
├── EventPlanner.java
│
├── Finance.java                         [interface]
│   ├── void addIncome(double amt, String note)
│   └── void addExpense(double amt, String note)
│       └── TreasurerTool.java           [implements Finance]
│
├── Minutes.java                         [interface]
│   └── void addMinutes(String text)
│       └── SecretaryTool.java           [implements Minutes]
│
└── Event.java                           [interface]
    ├── void createEvent(String name, double budget)
    └── int getEventsCount()
        ├── EventLeadTool.java           [implements Event]
        └── PublicityTool.java           [implements Event]
```