package com.github.wildtooth.plush.log;

import com.github.wildtooth.plush.Plush;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class Log {

    private final Plush plugin;
    private final Logger LOGGER;

    private Calendar lastLogDate;

    public Log(@NotNull Plush plugin) {
        this.plugin = plugin;
        this.LOGGER = plugin.getLogger();
        setLogFile();
    }

    public void init() {
        setLogFile();
    }

    public void info(String message) {
        LOGGER.info(message);
    }

    public void warning(String message) {
        LOGGER.warning(message);
    }

    public void severe(String message) {
        LOGGER.severe(message);
    }

    private void setLogFile() {
        Calendar currentDate = Calendar.getInstance();
        if (lastLogDate == null || currentDate.get(Calendar.DAY_OF_YEAR) != lastLogDate.get(Calendar.DAY_OF_YEAR)) {
            try {
                // Remove the FileHandler from the Logger object returned by plugin.getLogger()
                for (Handler handler : plugin.getLogger().getHandlers()) {
                    if (handler instanceof FileHandler) {
                        plugin.getLogger().removeHandler(handler);
                    }
                }

                // Get the current date
                int year = currentDate.get(Calendar.YEAR);
                int month = currentDate.get(Calendar.MONTH) + 1;
                int day = currentDate.get(Calendar.DAY_OF_MONTH);

                // Create the log directory if it doesn't exist
                File logDirectory = new File(getPlugin().getDataFolder(), "log");
                if (!logDirectory.exists()) {
                    logDirectory.mkdirs();
                }

                // Create the log file
                File logFile = new File(logDirectory, year + "-" + month + "-" + day + ".log");
                if (!logFile.exists()) {
                    logFile.createNewFile();
                }

                // Create a FileHandler for the log file
                FileHandler fileHandler = new FileHandler(logFile.getAbsolutePath(), true);
                LOGGER.setUseParentHandlers(false);
                fileHandler.setFormatter(new CustomFormatter());
                LOGGER.addHandler(fileHandler);

                // Update the last log date
                lastLogDate = currentDate;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() {
        for (Handler handler : LOGGER.getHandlers()) {
            if (handler instanceof FileHandler) {
                handler.close();
            }
        }
    }

    private Logger getLogger() {
        return this.LOGGER;
    }

    private Plush getPlugin() {
        return this.plugin;
    }
}
