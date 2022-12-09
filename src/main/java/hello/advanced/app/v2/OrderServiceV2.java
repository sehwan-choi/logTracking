package hello.advanced.app.v2;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.tracking.TrackingV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

    private final OrderRepositoryV2 orderRepository;

    private final TrackingV2 tracking;

    public void orderItem(TraceId traceId, String itemId) {
        TraceStatus begin = null;
        try {
            begin = tracking.beginSync(traceId, "OrderServiceV1.orderItem()");
            orderRepository.save(begin.getTraceId(), itemId);
        } catch (Exception e) {
            tracking.exception(begin, e);
            throw e;
        }
        tracking.end(begin);
    }
}
