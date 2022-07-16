package nz.co.joyhu.acceptance.domain;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class DateTimeFactory {

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MMMM dd, yyyy", Locale.US);

    public OffsetDateTime parseDateFromUI(String dateTime) {
        return LocalDate.parse(dateTime, DATE_FORMATTER).atStartOfDay(ZoneId.systemDefault()).toOffsetDateTime();
    }
}
