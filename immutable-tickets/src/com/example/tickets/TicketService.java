package com.example.tickets;

import java.util.ArrayList;
import java.util.List;

import com.example.tickets.IncidentTicket.Builder;

/**
 * Service layer that creates tickets.
 *
 * CURRENT STATE (BROKEN ON PURPOSE):
 * - creates partially valid objects
 * - mutates after creation (bad for auditability)
 * - validation is scattered & incomplete
 *
 * TODO (student):
 * - After introducing immutable IncidentTicket + Builder, refactor this to stop mutating.
 */
public class TicketService {

    public IncidentTicket createTicket(String id, String reporterEmail, String title) {
        // // scattered validation (incomplete on purpose)
        // if (id == null || id.trim().isEmpty()) throw new IllegalArgumentException("id required");
        // if (reporterEmail == null || !reporterEmail.contains("@")) throw new IllegalArgumentException("email invalid");
        // if (title == null || title.trim().isEmpty()) throw new IllegalArgumentException("title required");

        IncidentTicket t = new IncidentTicket.Builder()
                .id(id)
                .reporterEmail(reporterEmail)
                .title(title)
                .priority("MEDIUM")
                .source("CLI")
                .customerVisible(false)
                .build();

        
        List<String> tags = new ArrayList<>();
        tags.add("NEW");

        IncidentTicket newt = Builder.from(t).tags(tags).build();

        // IncidentTicket t = new IncidentTicket(id, reporterEmail, title);

        // BAD: mutating after creation
        // t.setPriority("MEDIUM");
        // t.setSource("CLI");
        // t.setCustomerVisible(false);



        // t.setTags(tags);

        return newt;
    }

    public IncidentTicket escalateToCritical(IncidentTicket t) {
        // BAD: mutating ticket after it has been "created"
        // t.setPriority("CRITICAL");
        // t.getTags().add("ESCALATED"); // list leak

         List<String> newTags = new ArrayList<>(t.getTags());
        newTags.add("ESCALATED");
        // new IncidentTicket.Builder();
        return Builder.from(t).priority("CRITICAL").tags(newTags).build();
    }

    public IncidentTicket assign(IncidentTicket t, String assigneeEmail) {
        // new IncidentTicket.Builder();
        // scattered validation
        // if (assigneeEmail != null && !assigneeEmail.contains("@")) {
        //     throw new IllegalArgumentException("assigneeEmail invalid");
        // }
        return Builder.from(t).assigneeEmail(assigneeEmail).build();
    }
}