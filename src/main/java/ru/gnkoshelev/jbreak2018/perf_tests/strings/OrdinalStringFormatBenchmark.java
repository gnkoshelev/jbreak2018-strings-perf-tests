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

import static ru.gnkoshelev.jbreak2018.perf_tests.strings.OrdinalStringFormat.formatThroughBuilder;
import static ru.gnkoshelev.jbreak2018.perf_tests.strings.OrdinalStringFormat.formatThroughConcatenation;
import static ru.gnkoshelev.jbreak2018.perf_tests.strings.OrdinalStringFormat.formatThroughMessageFormat;
import static ru.gnkoshelev.jbreak2018.perf_tests.strings.OrdinalStringFormat.formatThroughPattern;

/**
 * Created by kgn on 16.03.2018.
 */
@Fork(value = 3, warmups = 0)
@Warmup(iterations = 5, time = 1_500, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 1_500, timeUnit = TimeUnit.MILLISECONDS)
@OutputTimeUnit(value = TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
public class OrdinalStringFormatBenchmark {
    public String user;
    public String grade;
    public String message;
    public String company;

    @Setup
    public void setup() {
        user = "молодец";
        grade = "хорошие";
        company = "Контура";
        message = "спасибо";
    }

    @Benchmark
    public void formatThroughPatternBenchmark(Blackhole bh) {
        bh.consume(formatThroughPattern(user, company, grade, message));
    }

    @Benchmark
    public void formatThroughConcatenationBenchmark(Blackhole bh) {
        bh.consume(formatThroughConcatenation(user, company, grade, message));
    }

    @Benchmark
    public void formatThroughBuilderBenchmark(Blackhole bh) {
        bh.consume(formatThroughBuilder(user, company, grade, message));
    }

    @Benchmark
    public void formatThroughMessageFormatBenchmark(Blackhole bh) {
        bh.consume(formatThroughMessageFormat(user, company, grade, message));
    }
}
