package spittr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import spittr.daoimpl.SpitterRepositoryImpl;
import spittr.daoimpl.SpittleRepositoryImpl;
import spittr.data.SpitterRepository;
import spittr.data.Spittle;
import spittr.data.SpittleRepository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


@Configuration
public class DataConfig {

//    @Bean
//    public DataSource dataSource() {
//        return new EmbeddedDatabaseBuilder()
//                .build();
//    }

//    @Bean
//    public JdbcOperations jdbcTemplate() {
//        return new JdbcTemplate();
//    }

//    @Bean
//    public SpittleRepositoryImpl spittleRepository(JdbcOperations jdbc) {
//        return new SpittleRepositoryImpl(jdbc);
//    }

//    @Bean
//    public List<Spittle> spittles() {
//        return new ArrayList<Spittle>();
//    }

    @Bean
    public SpittleRepository spittleRepository() {
        return new SpittleRepositoryImpl();
    }

    @Bean
    public SpitterRepository spitterRepository() {
        return new SpitterRepositoryImpl();
    }

}
