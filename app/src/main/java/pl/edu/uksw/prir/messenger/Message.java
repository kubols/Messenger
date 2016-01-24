package pl.edu.uksw.prir.messenger;

/**
 *
 * @author Wojciech Pokora
 * @author Jakub Pawlak
 * @author Patryk Szewczyk
 * @author Katarzyna Wiater
 * @author Agnieszka Musiał
 * @author Michał Darkowski
 *
 */
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 
import org.w3c.dom.Document;
import org.xml.sax.InputSource;


public class Message {
    private String body;
    private String from;
    private String id;
    private String to;
    // private String type;
    // private Date date;
    
    Message(){
        this.from = null;
        this.id = null;
        this.to = null;
        this.body = null;
    }
    
    Message(String  msg_body, String msg_from, String msg_id, String msg_to){
        this.body = msg_body;
        this.from = msg_from;
        this.id = msg_id;
        this.to = msg_to;
    }
    
    
    public void messageString() throws FileNotFoundException{
        final String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"+
                                "<message \n"+
                                "from= \""+ this.from+"\"\n"+
                                "id= \""+ this.id+"\"\n"+
                                "to= \""+ this.to+"\"\n"+
                                ">\n"+
                                "<body> \n"+this.body+"\n"+"</body> \n"+
                                "</message>";
        
        Document doc = convertStringToDocument(xmlStr);
         
        String str = convertDocumentToString(doc);
        writeMessageToFile(str);
        //System.out.println(str);
    }
    
    private static String convertDocumentToString(Document doc) {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = tf.newTransformer();
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            String output = writer.getBuffer().toString();
            return output;
        } catch (TransformerException e) {
            e.printStackTrace();
        }
         
        return null;
    }
 
    private static Document convertStringToDocument(String xmlStr) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
        DocumentBuilder builder;  
        try 
        {  
            builder = factory.newDocumentBuilder();  
            Document doc = builder.parse( new InputSource( new StringReader( xmlStr ) ) ); 
            return doc;
        } catch (Exception e) {  
            e.printStackTrace();  
        } 
        return null;
    }
    
    public void writeMessageToFile(String data) throws FileNotFoundException{
        PrintWriter out = new PrintWriter("message.xml");
        out.println(data);
        out.close();
    } 
    
}
