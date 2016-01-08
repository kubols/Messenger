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
import java.util.List;


public class Friends {
    public List<Integer> friends_id;

    public List<Integer> getFriends_id() {
        return friends_id;
    }

    public void setFriends_id(int id) {
        friends_id.add(id);
    }
    
    
}
