package hello.advanced.trace.template;

import hello.advanced.trace.template.code.AbstractTemplate;
import hello.advanced.trace.template.code.SubClassroomLogin1;
import hello.advanced.trace.template.code.SubClassroomLogin2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.cassandra.DataCassandraTest;

@Slf4j
public class TemplateMethodTest {

    @Test
    void templateMethodV0() {
        logic1();
        logic2();
    }

    private void logic1() {
        long startTime = System.currentTimeMillis();

        log.info("비즈니스 로직1 실행");

        long entTime = System.currentTimeMillis();

        long resultTime = entTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();

        log.info("비즈니스 로직2 실행");

        long entTime = System.currentTimeMillis();

        long resultTime = entTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    @Test
    void templateMethodV1() {
        AbstractTemplate template = new SubClassroomLogin1();
        template.execute();
        AbstractTemplate template2 = new SubClassroomLogin2();
        template2.execute();
    }

    @Test
    void templateMethodV2() {
        AbstractTemplate template = new AbstractTemplate() {

            @Override
            protected void call() {
                log.info("비지니스 로직1 실행");
            }
        };

        log.info("클래스 이름1{}", template.getClass());
        template.execute();

        AbstractTemplate template2 = new AbstractTemplate() {

            @Override
            protected void call() {
                log.info("비지니스 로직2 실행");
            }
        };

        log.info("클래스 이름2{}", template2.getClass());
        template2.execute();
    }
}
