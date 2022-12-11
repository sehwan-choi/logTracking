package hello.advanced.trace.strategy.code.context;

import hello.advanced.trace.strategy.code.strategy.Strategy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContextV1 {

    private final Strategy strategy;

    public ContextV1(Strategy strategy) {
        this.strategy = strategy;
    }

    public void execute() {
        long startTime = System.currentTimeMillis();

        strategy.call();

        long entTime = System.currentTimeMillis();

        long resultTime = entTime - startTime;
        log.info("resultTime={}", resultTime);
    }
}
