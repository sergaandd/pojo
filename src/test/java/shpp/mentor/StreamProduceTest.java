package shpp.mentor;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.jupiter.api.Test;

import javax.jms.*;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class StreamProduceTest {

    @Test
    void loadStream() throws JMSException {
//        Properties myProp = PropertyFileOpen.openPropertyFile();
//        ActiveMQConnectionFactory myFactory = new ActiveMQConnectionFactory(myProp.getProperty("userName")
//                ,myProp.getProperty("password"),myProp.getProperty("brokerURL"));
//        Connection myConnection = myFactory.createConnection();
//        myConnection.start();
//        Session session = myConnection.createSession(false,
//                Session.AUTO_ACKNOWLEDGE);
//        Destination queue = session.createQueue(myProp.getProperty("queue"));
//        MessageProducer producer = session.createProducer(queue);
//        AtomicInteger resultSession = StreamProduce.loadStream(session,producer,"1","1");

    }
}