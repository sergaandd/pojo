package shpp.mentor;

import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class App {
    Random rg= new Random();
    public  static void main(String[] args) throws JMSException {
        Properties myProp = PropertyFileOpen.openPropertyFile();
        ActiveMQConnectionFactory myFactory = new ActiveMQConnectionFactory(myProp.getProperty("userName")
                ,myProp.getProperty("password"),myProp.getProperty("brokerURL"));
        Connection myConnection = myFactory.createConnection();
        myConnection.start();
        Session session = myConnection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);
        Destination queue = session.createQueue(myProp.getProperty("queue"));
        MessageProducer producer = session.createProducer(queue);
        AtomicInteger resultSession = StreamProduce.loadStream(session,producer,myProp.getProperty("streamTime")
                ,myProp.getProperty("qtyElements"));
        producer.close();
        session.close();
        myConnection.close();
    }
}
