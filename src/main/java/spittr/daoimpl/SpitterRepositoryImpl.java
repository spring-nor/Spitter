package spittr.daoimpl;

import spittr.data.Spitter;
import spittr.data.SpitterRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by norman on 28/02/17.
 */
public class SpitterRepositoryImpl implements SpitterRepository {

    private List<Spitter> spitter = new ArrayList<Spitter>();


    public SpitterRepositoryImpl() {
        String username = "utente";
        String password = "000";
        String firstName = "FirstName";
        String lastName = "LastName";
        String email = "@mail.net";

        List<Spitter> spitter = new ArrayList<Spitter>();
        for (int i = 0; i < 10; i++) {
            spitter.add(new Spitter((long) i, username + i, password + i, firstName + i, lastName + i, i + email, null));
        }

        this.spitter = spitter;
    }

    public Spitter save(Spitter spitter) {
        this.spitter.add(spitter);
        return spitter;
    }

    public Spitter findByUsername(String username) {
        List<Spitter> usernameSpitter =
                spitter.stream()
                        .filter(p -> p.getUsername().equals((username)))
                        .collect(Collectors.toList());
        return usernameSpitter.get(0);
    }

    @Override
    public List<Spitter> showAllSpitter() {
        return this.spitter;
    }
}
