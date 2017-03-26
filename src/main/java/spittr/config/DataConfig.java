package spittr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import spittr.daoimpl.SpitterRepositoryImpl;
import spittr.daoimpl.SpittleRepositoryImpl;
import spittr.data.SpitterRepository;
import spittr.data.SpittleRepository;

import javax.sql.DataSource;


@Configuration
public class DataConfig {

    @Autowired
    private Environment env;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        Resource config = new ClassPathResource("hibernate.cfg.xml");
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setConfigLocation(config);
        sessionFactory.setPackagesToScan(env.getProperty("spitter.entity.package"));
        sessionFactory.setDataSource(dataSource());
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();

        // Driver class name
        ds.setDriverClassName(env.getProperty("spitter.db.driver"));

        // Set URL
        ds.setUrl(env.getProperty("spitter.db.url"));

        // Set username & password
        ds.setUsername(env.getProperty("spitter.db.username"));
        ds.setPassword(env.getProperty("spitter.db.password"));

        return ds;
    }

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
