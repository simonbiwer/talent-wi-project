import com.example.application.entities.User;
import com.example.application.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Round Triping Test mit einer einfachen Strecke (C-R-Ass-D).
 * @Author Mhalah
 * 03.05.2023
 */

@SpringBootTest(classes = com.example.application.Application.class)
public class RoundTripTest {

    @Autowired
    private UserRepository userRepository;


    @Test
    void createReadAndDeleteAUser() {

        // Schritt 1: C = Create (hier: Erzeugung und Abspeicherung mit der Method save()
        // Anlegen von 2 Users. Eine ID wird automatisch erzeugt durch JPA
        User user1 = new User();
        User user2 = new User();

        // setEmails von Users
        user1.setEmail("test1@myserver.de");
        user2.setEmail("test2@myserver.de");
        user1.setPasswort("aStrongPassword");
        user2.setPasswort("aStrongPassword");
        user1.setVorname("Max");
        user2.setVorname("Mustermann");
        user1.setNachname("max");
        user2.setNachname("mustermann");

        // und ab auf die DB damit (save!)
        userRepository.save(user1);
        userRepository.save(user2);

        // Da die ID auto-generiert wurde, müssen wir uns die erzeugte ID nach dem Abspeichern merken:
        int idUser1Tmp = user1.getUserid();
        int idUser2Tmp = user2.getUserid();


        // Schritt 2: R = Read (hier: Auslesen über die Methode find()
        Optional<User> wrapperUser1 = userRepository.findById(idUser1Tmp);
        Optional<User> wrapperUser2 = userRepository.findById(idUser2Tmp);


        User user1AfterCreate = null;
        User user2AfterCreate = null;


        if (wrapperUser1.isPresent()) {
            user1AfterCreate = wrapperUser1.get();
        }

        if (wrapperUser2.isPresent()) {
            user2AfterCreate = wrapperUser2.get();
        }


        // Schritt 3: Ass = Assertion: Vergleich der vorhandenen Objekte auch Gleichheit...
        assertEquals(user1AfterCreate.getEmail(), "test1@myserver.de");
        assertEquals(user1AfterCreate.getNachname(), "max");
        assertEquals(user1AfterCreate.getVorname(), "Max");
        assertEquals(user1AfterCreate.getPasswort(), "aStrongPassword");
        assertEquals(user2AfterCreate.getEmail(), "test2@myserver.de");
        assertEquals(user2AfterCreate.getNachname(), "mustermann");
        assertEquals(user2AfterCreate.getVorname(), "Mustermann");
        assertEquals(user2AfterCreate.getPasswort(), "aStrongPassword");
        assertNotSame(user1, user1AfterCreate);
        assertNotSame(user2, user2AfterCreate);



        // Schritt 4: D = Deletion, also Löschen des Users, um Datenmüll zu vermeiden
        userRepository.deleteById(idUser1Tmp);
        userRepository.deleteById(idUser2Tmp);


        // Schritt 4.1: Wir sind vorsichtig und gucken, ob der User wirklich gelöscht wurde ;-)
        Optional<User> wrapperUser1AfterDelete = userRepository.findById(idUser1Tmp);
        Optional<User> wrapperUser2AfterDelete = userRepository.findById(idUser2Tmp);
        System.out.println("wrapperUser1AfterDelete: " + wrapperUser1AfterDelete + "\n" +
                "wrapperUser2AfterDelete: " + wrapperUser2AfterDelete);
        assertFalse(wrapperUser1AfterDelete.isPresent());
        assertFalse(wrapperUser2AfterDelete.isPresent());

    }

}
