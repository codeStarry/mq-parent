package org.lsy.learn.rabbitmq.config;

public interface Constants {

    //--------------直连交换机----------------------

    /**
     * 直连交换机名称
     */
    String DIRECT_EXCHANGE_KEY = "testDirectExchange";
    /**
     * 直连交换机队列名称
     */
    String DIRECT_QUEUE_KEY = "testDirectQueue";
    /**
     * 直连交换机匹配键
     */
    String DIRECT_ROUTING_KEY = "testDirectRouting";
    /**
     * 测试回调函数所用
     */
    String DIRECT_TEST_EXCHANGE = "testErrorDirectExchange";

    //--------------主题交换机----------------------

    String TOPIC_EXCHANGE_KEY = "testTopicExchange";
    String TOPIC_MAN_QUEUE_KEY = "topic.man";
    String TOPIC_WOMAN_QUEUE_KEY = "topic.woman";
    /**
     * #：匹配任意字符    例：*.TT.* 匹配 B.TT.C
     * *：匹配单个字符    例：TT.#   匹配 CS.TT.BDS
     */
    String TOPIC_ROUTING_KEY = "topic.#";

    //--------------扇形交换机----------------------

    String FANOUT_EXCHANGE_KEY = "testFanoutExchange";
    String FANOUT_ONE_QUEUE_KEY = "oneFanoutQueue";
    String FANOUT_TWO_QUEUE_KEY = "twoFanoutQueue";
    String FANOUT_THREE_QUEUE_KEY = "threeFanoutQueue";

    //--------------rabbit延迟队列----------------------

    String DELAY_EXCHANGE_KEY = "testDelayExchange";
    String DELAY_QUEUE_KEY = "testDelayQueue";
    String DELAY_ROUTING_KEY = "testDelayRouting";
}
