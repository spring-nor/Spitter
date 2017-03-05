package spittr.daoimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import spittr.data.Spittle;
import spittr.data.SpittleRepository;
import spittr.exception.DuplicateSpittleException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by norman on 26/02/17.
 */

public class SpittleRepositoryImpl implements SpittleRepository {


    private List<Spittle> spittles = new ArrayList<Spittle>();

//    @Autowired
//    public SpittleRepositoryImpl(List<Spittle> spittles) {
//
//        this.spittles = spittles;
//    }

//    @Autowired
    public SpittleRepositoryImpl() {
        List<Spittle> spittles = new ArrayList<Spittle>();
        for (int i = 0; i < 500; i++) {
            spittles.add(new Spittle((long) i, "Message" + i, new Date(), (double) i, (double) i));
        }
        this.spittles = spittles;
    }

    public List<Spittle> findRecentSpittles() {
        return null;
    }

    public List<Spittle> findSpittles(long max, int count) {

        List<Spittle> spittles = new ArrayList<Spittle>();
        for (int i = 0; i < count; i++) {
//            spittles.add(new Spittle("Spittle" + i, new Date()));
            spittles.add(this.spittles.get(i));

        }
        return spittles;

    }

    public Spittle findOne(long id) {
        try {
            return this.spittles.get((int) id);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public void save(Spittle spittle) {
        List<Spittle> messageSpittle =
                spittles.stream()
                        .filter(p -> p.getMessage().equals(spittle.getMessage()))
                        .collect(Collectors.toList());
        if (messageSpittle.isEmpty())
            this.spittles.add(spittle);
        else
            throw new DuplicateSpittleException();
    }
}
