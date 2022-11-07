package shpp.mentor;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.jms.*;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StreamProduceTest {

    @Test
    void loadStream() throws JMSException {
        try {
            Properties myProp = PropertyFileOpen.openPropertyFile();//Open property filу
            //Create ActiveMQ: connection,session,producer using data from property file
            ActiveMQConnectionFactory myFactory = new ActiveMQConnectionFactory();
            Connection myConnection = myFactory.createConnection();
            myConnection.start();

            Session session = myConnection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);//Its mean auto response from ActiveMQ server

            Destination queue = session.createQueue(myProp.getProperty("queue"));//Use the name of queue from property

            MessageProducer producer = session.createProducer(queue);//Create a message sender

            //Return qty of messages generated and sent in queue by stream
            AtomicInteger actual = StreamProduce.loadStream(session, producer, "1", "1", "Test");
            //Closing all elements of connection
            producer.close();
            session.close();
            myConnection.close();
            int expected = 1;
            Assertions.assertEquals(actual.intValue(), expected);
        }catch(Exception e){
            System.out.println("ALARM! Something get wrong....");
        }
    }
}