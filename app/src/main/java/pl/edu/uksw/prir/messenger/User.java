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

public class User {
    int id;
    private String jid_identifier;  //np pawel@adam.pl dla programu
    private String name;            //dla uzytkownika
    private Boolean approved = false; //zaakceptowany lub nie uzytkownik
    private int ask;
    private Boolean status = false;
    //private String password;
    
    User(int id,String jid_identifier,String name,Boolean approved,int ask,Boolean status){
        this.id = id;
        this.jid_identifier = jid_identifier;
        this.name = name;
        this.approved = approved;
        this.ask = ask;
        this.status = status;
        
    }
    
    User() throws FileNotFoundException{
        setStatus(true);
        if(findUser(name)==false){
            createUser(name);
        }
        
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    

    public Boolean getApproved() {
        return approved;
    }

    public int getAsk() {
        return ask;
    }

    public int getId() {
        return id;
    }

    public String getJid_identifier() {
        return jid_identifier;
    }

    public String getName() {
        return name;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public void setAsk(int ask) {
        this.ask = ask;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setJid_identifier(String jid_identifier) {
        this.jid_identifier = jid_identifier;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Boolean findUser(String name){
        File dir = new File(name+".txt");
        return dir.isFile();
    }
    
    public void createUser(String name) throws FileNotFoundException{
        PrintWriter printer = new PrintWriter(name+".txt");
        printer.println(this.name);
        printer.println(this.id);
        printer.close();
    }

}
