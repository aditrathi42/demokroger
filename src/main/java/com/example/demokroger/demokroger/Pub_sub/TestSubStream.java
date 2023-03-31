package com.example.demokroger.demokroger.Pub_sub;


import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.PubsubMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


@Component
@RequiredArgsConstructor
public class TestSubStream {

    @Value("${subscriptionId}")
    private String subscriptionId;
    @Value("${projectId}")
    private String projectId;

    private Subscriber subscriber;

    public void subscribeAsyncExample(){
        ProjectSubscriptionName subscriptionName =
                ProjectSubscriptionName.of(projectId, subscriptionId);

        MessageReceiver receiver =
                (PubsubMessage message, AckReplyConsumer consumer) -> {
                    System.out.println("Id: " + message.getMessageId());
                    System.out.println("Data: " + message.getData().toStringUtf8());
                    consumer.ack();
                };
        subscriber = Subscriber.newBuilder(subscriptionName,receiver).build();
        subscriber.startAsync().awaitRunning();
        System.out.printf("Listening for messages on %s:\n", subscriptionName.toString());

    }
    @PostConstruct
    public void startSub(){
        subscribeAsyncExample();
    }
    @PreDestroy
    public void endSub(){
            subscriber.stopAsync().awaitTerminated();
    }
}