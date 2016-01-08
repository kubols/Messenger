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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;
 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 
import org.w3c.dom.Document;
import org.xml.sax.InputSource;


public class Message {
    private String message;
    private int jid_id_from;
    private int jid_id_to;
   // private Date date;
    
    Message(){
        this.jid_id_from = 0;
        this.jid_id_to = 0;
        this.message = null;
    }
    
    Message(String  m, int jid_from, int jid_to){
        this.message = m;
        this.jid_id_from = jid_from;
        this.jid_id_to = jid_to;
    }
    
    
    public void messageString() throws FileNotFoundException{
        final String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"+
                                "<Message> \n"+
                               "<To> \n"+ Integer.toString(this.jid_id_to)+"\n"+"</To> \n"+
                               "<From> \n"+ Integer.toString(this.jid_id_from)+"\n"+"</From> \n"+
                                "<Text> \n"+this.message+"\n"+"</Text> \n"+
                                "</Message>";
        
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
