package shpp.mentor;


import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class StreamProduce {


    StreamProduce(){}
public static AtomicInteger loadStream(Session session, MessageProducer producer,String duration,String qtyElements) throws JMSException {
    long endTime = LocalDateTime.now().getSecond()+Long.parseLong(duration);
    AtomicInteger counter= new AtomicInteger();
    Stream.generate(() -> new Pojo().setCount((int) ((Math.random() * (999 - 1)) + 1))
            .setName(PojoToJson.getName()).setCreated_at(LocalDateTime.now().toString()))
            .limit(Integer.parseInt(qtyElements))
            .takeWhile(x->LocalDateTime.now().getSecond()<=endTime)
            .map(PojoToJson::getJson)
            .forEach((p) -> {
                try {
                    producer.send(session.createTextMessage(p));
                    counter.getAndIncrement();
                } catch (JMSException e) {
                    throw new RuntimeException(e);
                }
            });
    producer.send(session.createTextMessage("Stop reading"));
    counter.getAndIncrement();
    return counter;
}

}
