package hello.advanced.tracking;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

import java.nio.channels.IllegalChannelGroupException;

import static org.junit.jupiter.api.Assertions.*;

class TrackingV1Test {

    @Test
    void begin_end() {
        TrackingV1 trackingV1 = new TrackingV1();
        TraceStatus traceStatus = trackingV1.begin("hello");
        trackingV1.end(traceStatus);
    }

    @Test
    void begin_exception() {
        TrackingV1 trackingV1 = new TrackingV1();
        TraceStatus traceStatus = trackingV1.begin("hello");
        trackingV1.exception(traceStatus, new IllegalStateException());
    }
}