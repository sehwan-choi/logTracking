package hello.advanced.app.v3;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {

    private final OrderRepositoryV3 orderRepository;

    private final LogTrace tracking;

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
