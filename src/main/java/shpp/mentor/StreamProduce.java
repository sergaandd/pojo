package shpp.mentor;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class StreamProduce {

    StreamProduce() {
    }
    public static AtomicInteger loadStream(Session session, MessageProducer producer
                  , String duration, String qtyElements,String stopMessage) throws JMSException {
        //Calculate the tame to stop generating.
        long endTime = LocalDateTime.now().getSecond() + Long.parseLong(duration);
        //Use the counter inside of stream to calculate the qty of elements generate
        AtomicInteger counter = new AtomicInteger();//Atomic type because I can get it from streaming process
        //Math.random function works quicker than Random.nextInt()
        Stream.generate(() -> new Pojo().setCount((int) ((Math.random() * (999 - 1)) + 1))
                .setName(PojoToJson.getName()).setCreated_at(LocalDateTime.now().toString()))
                .limit(Integer.parseInt(qtyElements))//Qty of elements
                .takeWhile(x -> LocalDateTime.now().getSecond() <= endTime)//Time to stop generating
                .map(PojoToJson::getJson)//Convert Pojo object to json type string
                .forEach((p) -> {//This block send every generated element to queue
                    try {
                        producer.send(session.createTextMessage(p));
                        counter.getAndIncrement();//Calculating generated elements
                    } catch (JMSException e) {
                        throw new RuntimeException(e);
                    }
                });
        if (!stopMessage.equals("Test")) {
            producer.send(session.createTextMessage(stopMessage));//Send Poison pill as last message
            counter.getAndIncrement();
        }
        return counter;//Return as result the qty of sent elements to queue
    }

}
