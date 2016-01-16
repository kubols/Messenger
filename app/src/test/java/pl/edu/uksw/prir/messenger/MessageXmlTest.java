package pl.edu.uksw.prir.messenger;

import org.junit.Test;

import java.io.FileNotFoundException;

/**
 * Created by Jakub on 16/01/16.
 */
public class MessageXmlTest {
    @Test
    public void testMessage() throws FileNotFoundException{
        Message msg = new Message("some body", "Romeo", "asdf", "Juliet");
        msg.messageString();
        String body = msg.openMessageFromFile();
        System.out.println(body);
    }
}
