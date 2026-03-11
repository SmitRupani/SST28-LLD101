```
immutable-tickets/src/
├── TryIt.java
└── com/example/tickets/
    ├── Validation.java
    ├── TicketService.java
    └── IncidentTicket.java
        └── [inner] Builder
            ├── Builder id(String id)
            ├── Builder reporterEmail(String reporterEmail)
            ├── Builder title(String title)
            ├── Builder description(String description)
            ├── Builder priority(String priority)
            ├── Builder tags(List<String> tags)
            ├── Builder assigneeEmail(String assigneeEmail)
            ├── Builder customerVisible(boolean customerVisible)
            ├── Builder slaMinutes(Integer slaMinutes)
            ├── Builder source(String source)
            ├── static Builder from(IncidentTicket ticket)
            └── IncidentTicket build()
```