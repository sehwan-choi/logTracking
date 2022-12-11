package hello.advanced.app.v4;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {

    private final OrderRepositoryV4 orderRepository;

    private final LogTrace tracking;

    public void orderItem(String itemId) {

        new AbstractTemplate<Void>(tracking) {
            @Override
            protected Void call() {
                orderRepository.save(itemId);
                return null;
            }
        }.execute("OrderServiceV1.orderItem()");
    }
}
