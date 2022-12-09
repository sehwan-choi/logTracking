package hello.advanced.app.v1;

import hello.advanced.trace.TraceStatus;
import hello.advanced.tracking.TrackingV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {

    private final OrderRepositoryV1 orderRepository;

    private final TrackingV1 tracking;

    public void orderItem(String itemId) {
        TraceStatus begin = null;
        try {
            begin = tracking.begin("OrderServiceV1.orderItem()");
            orderRepository.save(itemId);
        } catch (Exception e) {
            tracking.exception(begin, e);
            throw e;
        }
        tracking.end(begin);
    }
}
