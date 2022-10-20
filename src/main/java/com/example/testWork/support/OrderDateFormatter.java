package com.example.testWork.support;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Component
public class OrderDateFormatter {
    private DateTimeFormatter formatter;

    OrderDateFormatter() {
        formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
                .withZone(ZoneId.from(ZoneOffset.UTC));
    }

    public String fromDate(Instant date) {
        return formatter.format(date);
    }

    public Instant toDate(String dateString) {
        var dateTime = LocalDateTime.parse(dateString, formatter).atZone(ZoneId.from(ZoneOffset.UTC));
        return dateTime.toInstant();
    }

}
