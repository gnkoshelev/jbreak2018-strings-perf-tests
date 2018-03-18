package ru.gnkoshelev.jbreak2018.perf_tests.strings;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by kgn on 17.03.2018.
 */
@Fork(value = 3, warmups = 0)
@Warmup(iterations = 5, time = 1_500, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 1_500, timeUnit = TimeUnit.MILLISECONDS)
@OutputTimeUnit(value = TimeUnit.MICROSECONDS)
@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
public class ImmutableStringBenchmark {
    @Param({"10", "100", "1000", "10000"})
    public int N;

    public String[] strings;

    @Setup
    public void setup() {
        Random rand = new Random(42);
        strings = new String[N];
        for (int i = 0; i < N; i++) {
            strings[i] = String.valueOf(rand.nextInt());
        }
    }

    @Benchmark
    public void concatenationBenchmark(Blackhole bh) {
        bh.consume(constructThroughConcatenation(strings));
    }

    @Benchmark
    public void builderBenchmark(Blackhole bh) {
        bh.consume(constructThroughBuilder(strings));
    }

    public static String constructThroughConcatenation(String[] strings) {
        String result = "";
        for (String s : strings) {
            result = result + s;
        }
        return result;
    }

    public static String constructThroughBuilder(String[] strings) {
        StringBuilder sb = new StringBuilder();
        for (String s : strings) {
            sb.append(s);
        }
        return sb.toString();
    }
}
