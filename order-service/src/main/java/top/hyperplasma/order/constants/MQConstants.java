package top.hyperplasma.order.constants;

public interface MQConstants {
    String DELAY_EXCHANGE_NAME = "order.delay.direct";
    String DELAY_ORDER_QUEUE_NAME = "order.delay.order.queue";
    String DELAY_ORDER_KEY = "delay.order.query";
}
