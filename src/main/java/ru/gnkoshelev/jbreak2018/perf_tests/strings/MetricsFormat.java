package ru.gnkoshelev.jbreak2018.perf_tests.strings;

import java.text.MessageFormat;

/**
 * Created by kgn on 18.03.2018.
 */
public class MetricsFormat {
    public static String formatThroughPattern(String metrics, int value, long timestamp) {
        return String.format(
                "%s %d %d",
                metrics, value, timestamp);
    }

    public static String formatThroughConcatenation(String metrics, int value, long timestamp) {
        return metrics + " " + value + " " + timestamp;
    }

    public static String formatThroughBuilder(String metrics, int value, long timestamp) {
        return new StringBuilder(metrics)
                .append(" ")
                .append(value)
                .append(" ")
                .append(timestamp)
                .toString();
    }

    public static String formatThroughMessageFormat(String metrics, int value, long timestamp) {
        return MessageFormat.format(
                "{0} {1} {2}",
                metrics, String.valueOf(value), String.valueOf(timestamp));
    }
}
