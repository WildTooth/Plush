package com.github.wildtooth.plush.log;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class CustomFormatter extends Formatter {
    public CustomFormatter() {
        super();
    }

    /** {@inheritDoc} */
    @Override
    public String format(@NotNull LogRecord record) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        String time = String.format("%02d:%02d:%02d", hour, minute, second);
        return "[" + time + "]: " + record.getMessage().replace("[Plush] ", "") + System.lineSeparator();
    }
}
