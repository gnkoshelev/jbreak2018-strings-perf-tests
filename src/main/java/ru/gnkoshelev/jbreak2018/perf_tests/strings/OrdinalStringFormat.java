package ru.gnkoshelev.jbreak2018.perf_tests.strings;

import java.text.MessageFormat;

/**
 * Created by kgn on 16.03.2018.
 */
public class OrdinalStringFormat {
    public static String formatThroughPattern(String user, String grade, String company, String message) {
        return String.format(
                "Он, %s, придумал такие %s задачи. Приду на стенд %s и скажу ему %s",
                user, grade, company, message);
    }

    public static String formatThroughConcatenation(String user, String grade, String company, String message) {
        return "Он, " + user
                + ", придумал такие " + grade
                + " задачи. Приду на стенд " + company
                + " и скажу ему " + message;
    }

    public static String formatThroughBuilder(String user, String grade, String company, String message) {
        return new StringBuilder("Он, ")
                .append(user)
                .append(", придумал такие ")
                .append(grade)
                .append(" задачи. Приду на стенд ")
                .append(company)
                .append(" и скажу ему ")
                .append(message)
                .toString();
    }

    public static String formatThroughMessageFormat(String user, String grade, String company, String message) {
        return MessageFormat.format(
                "Он, {0}, придумал такие {1} задачи. Приду на стенд {2} и скажу ему {3}",
                user, grade, company, message);
    }
}
