package com.example.tickets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public final class Validation {

    private static final Pattern EMAIL     = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
    private static final Pattern TICKET_ID = Pattern.compile("^[A-Z0-9-]+$");

    private Validation() {}

    public static Validator of(String value, String fieldName) {
        return new Validator(value, fieldName);
    }

    public static Validator ofInt(Integer value, String fieldName) {
        return new Validator(value == null ? null : String.valueOf(value), fieldName);
    }

    public static final class Validator {
        private final String value;
        private final String fieldName;
        private final List<String> errors = new ArrayList<>();

        private Validator(String value, String fieldName) {
            this.value     = value;
            this.fieldName = fieldName;
        }

        public Validator nonBlank() {
            if (value == null || value.trim().isEmpty())
                errors.add(fieldName + " must not be blank");
            return this;
        }

        public Validator maxLen(int max) {
            if (value != null && value.length() > max)
                errors.add(fieldName + " must be <= " + max + " chars");
            return this;
        }

        public Validator email() {
            if (value != null && !EMAIL.matcher(value).matches())
                errors.add(fieldName + " must be a valid email");
            return this;
        }

        public Validator matchesPattern(Pattern pattern) {
            if (value != null && !pattern.matcher(value).matches())
                errors.add(fieldName + " must match pattern " + pattern.pattern());
            return this;
        }

        public Validator oneOf(String... allowed) {
            if (value != null && Arrays.stream(allowed).noneMatch(value::equals))
                errors.add(fieldName + " must be one of: " + String.join(", ", allowed));
            return this;
        }

        public Validator range(int min, int max) {
            if (value != null) {
                int intVal = Integer.parseInt(value);
                if (intVal < min || intVal > max)
                    errors.add(fieldName + " must be between " + min + " and " + max);
            }
            return this;
        }

        public void validate() {
            if (!errors.isEmpty())
                throw new IllegalArgumentException(String.join("; ", errors));
        }
    }

    public static void validateIncidentTicket(String id, String reporterEmail, String title,
                                              String assigneeEmail, String priority, Integer slaMinutes) {
        of(id,            "id"           ).nonBlank().maxLen(20).matchesPattern(TICKET_ID).validate();
        of(reporterEmail, "reporterEmail").nonBlank().email()                             .validate();
        of(title,         "title"        ).nonBlank().maxLen(80)                          .validate();

        if (assigneeEmail != null)
            of(assigneeEmail, "assigneeEmail").email().validate();

        of(priority,      "priority"     ).oneOf("LOW", "MEDIUM", "HIGH", "CRITICAL").validate();
        ofInt(slaMinutes, "slaMinutes"   ).range(5, 7200).validate();
    }
}