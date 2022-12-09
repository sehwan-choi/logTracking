package hello.advanced.app.v2;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.tracking.TrackingV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {

    private final TrackingV2 tracking;

    public void save(TraceId traceId, String itemId) {

        TraceStatus begin = null;
        try {
            begin = tracking.beginSync(traceId, "OrderRepositoryV1.save()");

            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생");
            }
            sleep(1000);

        } catch (Exception e) {
            tracking.exception(begin, e);
            throw e;
        }
        tracking.end(begin);

    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
