package ru.gnkoshelev.jbreak2018.perf_tests.strings;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

import static ru.gnkoshelev.jbreak2018.perf_tests.strings.MetricsFormat.formatThroughBuilder;
import static ru.gnkoshelev.jbreak2018.perf_tests.strings.MetricsFormat.formatThroughConcatenation;
import static ru.gnkoshelev.jbreak2018.perf_tests.strings.MetricsFormat.formatThroughMessageFormat;
import static ru.gnkoshelev.jbreak2018.perf_tests.strings.MetricsFormat.formatThroughPattern;

/**
 * Created by kgn on 18.03.2018.
 */
@Fork(value = 3, warmups = 0)
@Warmup(iterations = 5, time = 1_500, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 1_500, timeUnit = TimeUnit.MILLISECONDS)
@OutputTimeUnit(value = TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
public class MetricsFormatBenchmark {
    public String metrics;
    public int value;
    public long timestamp;

    @Setup
    public void setup() {
        metrics = "my-awesome-project"
                + ".my-awesome-microservice-of-my-awesome-project"
                + ".my-host-for-my-awesome-microservice-of-my-awesome-project"
                + ".my-custom-metric-from-my-host-of-my-awesome-microservice-of-my-awesome-project"
                + ".p99-for-my-custom-metric-of-my-awesome-microservice-of-my-awesome-project";
        value = 1;
        timestamp = 1521331200000L;
    }

    @Benchmark
    public void formatThroughPatternBenchmark(Blackhole bh) {
        bh.consume(formatThroughPattern(metrics, value, timestamp));
    }

    @Benchmark
    public void formatThroughConcatenationBenchmark(Blackhole bh) {
        bh.consume(formatThroughConcatenation(metrics, value, timestamp));
    }

    @Benchmark
    public void formatThroughBuilderBenchmark(Blackhole bh) {
        bh.consume(formatThroughBuilder(metrics, value, timestamp));
    }

    @Benchmark
    public void formatThroughMessageFormatBuilder(Blackhole bh) {
        bh.consume(formatThroughMessageFormat(metrics, value, timestamp));
    }
}
