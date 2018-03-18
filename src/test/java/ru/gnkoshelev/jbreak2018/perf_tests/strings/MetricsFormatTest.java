package ru.gnkoshelev.jbreak2018.perf_tests.strings;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by kgn on 18.03.2018.
 */
public class MetricsFormatTest {
    private MetricsFormat metricsFormat = new MetricsFormat();
    @Test
    public void testFormatThroughPattern() {
        Assert.assertEquals(
                "project.service.host.hits.count 100500 1521331200000",
                new MetricsFormat().formatThroughPattern(
                        "project.service.host.hits.count",
                        100500,
                        1521331200000L));
    }

    @Test
    public void testFormatThroughMessageFormat() {
        Assert.assertEquals(
                "project.service.host.hits.count 100500 1521331200000",
                MetricsFormat.formatThroughMessageFormat(
                        "project.service.host.hits.count",
                        100500,
                        1521331200000L));
    }
    @Test
    public void testFormatThroughBuilder() {
        Assert.assertEquals(
                "project.service.host.hits.count 100500 1521331200000",
                MetricsFormat.formatThroughBuilder(
                        "project.service.host.hits.count",
                        100500,
                        1521331200000L));
    }
    @Test
    public void testFormatThroughConcatenation() {
        Assert.assertEquals(
                "project.service.host.hits.count 100500 1521331200000",
                MetricsFormat.formatThroughConcatenation(
                        "project.service.host.hits.count",
                        100500,
                        1521331200000L));
    }
}
