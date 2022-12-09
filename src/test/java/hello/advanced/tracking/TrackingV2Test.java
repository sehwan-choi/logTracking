package hello.advanced.tracking;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class TrackingV2Test {

    @Test
    void begin_end() {
        TrackingV2 trackingV1 = new TrackingV2();
        TraceStatus traceStatus = trackingV1.begin("hello");
        TraceStatus traceStatus2 = trackingV1.beginSync(traceStatus.getTraceId(), "hello2");
        trackingV1.end(traceStatus2);
        trackingV1.end(traceStatus);
    }

    @Test
    void begin_exception() {
        TrackingV2 trackingV1 = new TrackingV2();
        TraceStatus traceStatus = trackingV1.begin("hello");
        TraceStatus traceStatus2 = trackingV1.beginSync(traceStatus.getTraceId(), "hello2");
        trackingV1.exception(traceStatus2,  new IllegalStateException());
        trackingV1.exception(traceStatus, new IllegalStateException());
    }
}