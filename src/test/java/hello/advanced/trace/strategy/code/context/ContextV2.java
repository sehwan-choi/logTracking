package hello.advanced.trace.strategy.code.context;

import hello.advanced.trace.strategy.code.strategy.Strategy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContextV2 {

    public void execute(Strategy strategy) {
        long startTime = System.currentTimeMillis();

        strategy.call();

        long entTime = System.currentTimeMillis();

        long resultTime = entTime - startTime;
        log.info("resultTime={}", resultTime);
    }
}
