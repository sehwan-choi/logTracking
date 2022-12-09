package hello.advanced.app.v3;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {

    private final OrderServiceV3 orderService;
    private final LogTrace tracking;

    @GetMapping("/v3/request")
    public String request(String itemId) {

        TraceStatus begin = null;
        try {
            begin = tracking.begin("OrderController.request()");
            orderService.orderItem(itemId);
            tracking.end(begin);
        } catch (Exception e) {
            tracking.exception(begin, e);
        }
        return "ok";
    }
}
