package shpp.mentor;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

public class App {

    public static void main(String[] args) throws JMSException {
        Properties myProp = PropertyFileOpen.openPropertyFile();//Open property file
        try {
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
            //Closing all elements of connection
            producer.close();
            session.close();
            myConnection.close();
        }catch(Exception e){
            System.out.println("Something get wrong....");

        }
    }
}
