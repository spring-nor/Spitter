package spittr.daoimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import spittr.data.Spittle;
import spittr.data.SpittleRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by norman on 26/02/17.
 */

public class SpittleRepositoryImpl implements SpittleRepository {


    private List<Spittle> spittles;

//    @Autowired
//    public SpittleRepositoryImpl(List<Spittle> spittles) {
//
//        this.spittles = spittles;
//    }

//    @Autowired
    public SpittleRepositoryImpl() {
        List<Spittle> spittles = new ArrayList<Spittle>();
        for (int i = 0; i < 500; i++) {
            spittles.add(new Spittle("Spittle" + i, new Date()));
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
        return this.spittles.get((int) id);
    }

    public void save(Spittle spittle) {
        this.spittles.add(spittle);
    }
}
