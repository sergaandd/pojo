package shpp.mentor;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

public class App {
    public static final Logger logger = LoggerFactory.getLogger(App.class);
    public static void main(String[] args) throws JMSException {
        Properties myProp = PropertyFileOpen.openPropertyFile();//Open property file
            //Create ActiveMQ: connection,session,producer
            ActiveMQConnectionFactory myFactory = new ActiveMQConnectionFactory(myProp.getProperty("userName")
                    ,myProp.getProperty("password"),myProp.getProperty("brokerURL"));
            Connection myConnection = myFactory.createConnection();
            myConnection.start();

            Session session = myConnection.createSession(false,
            Session.AUTO_ACKNOWLEDGE);//Its mean auto response from ActiveMQ server

            Destination queue = session.createQueue(myProp.getProperty("queue"));//Use the name of queue from property

            MessageProducer producer = session.createProducer(queue);//Create a message sender

            //Return qty of messages generated and sent in queue by stream
            AtomicInteger resultSession = StreamProduce.loadStream(session, producer, myProp.getProperty("streamTime")
            , myProp.getProperty("qtyElements"), myProp.getProperty("stopMessage"));
            logger.info("We send in {} {} {}",myProp.getProperty("queue"),resultSession," messages.");
            //Closing all elements of connection
            producer.close();
            session.close();
            myConnection.close();
    }
}
