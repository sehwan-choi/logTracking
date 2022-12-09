package hello.advanced.app.v2;

import hello.advanced.trace.TraceStatus;
import hello.advanced.tracking.TrackingV2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {

    private final OrderServiceV2 orderService;
    private final TrackingV2 tracking;

    @GetMapping("/v2/request")
    public String request(String itemId) {

        TraceStatus begin = null;
        try {
            begin = tracking.begin("OrderController.request()");
            orderService.orderItem(begin.getTraceId(), itemId);
        } catch (Exception e) {
            tracking.exception(begin, e);
        }
        tracking.end(begin);
        return "ok";
    }
}
