package spittr.data;

import java.util.List;

public interface SpitterRepository {

    Spitter save(Spitter spitter);

    Spitter findByUsername(String username);

    List<Spitter> showAllSpitter();

}
