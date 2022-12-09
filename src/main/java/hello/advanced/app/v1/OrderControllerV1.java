package hello.advanced.app.v1;

import hello.advanced.trace.TraceStatus;
import hello.advanced.tracking.TrackingV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {

    private final OrderServiceV1 orderService;
    private final TrackingV1 tracking;

    @GetMapping("/v1/request")
    public String request(String itemId) {

        TraceStatus begin = null;
        try {
            begin = tracking.begin("OrderController.request()");
            orderService.orderItem(itemId);
        } catch (Exception e) {
            tracking.exception(begin, e);
        }
        tracking.end(begin);
        return "ok";
    }
}
