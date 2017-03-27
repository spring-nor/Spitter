package spittr.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import spittr.daoimpl.SpitterRepositoryImpl;
import spittr.daoimpl.SpittleRepositoryImpl;
import spittr.data.SpitterRepository;
import spittr.data.SpittleRepository;

import javax.sql.DataSource;
import java.util.Properties;

//import org.jboss.logging.Logger;
//import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;


@Configuration
public class DataConfig {

//    @Autowired
//    private Environment env;

    @Value("${spitter.entity.package}")
    String spitter_entity_package;

    @Value("${spitter.db.driver}")
    String spitter_db_driver;

    @Value("${spitter.db.url}")
    String spitter_db_url;

    @Value("${spitter.db.username}")
    String spitter_db_username;

    @Value("${spitter.db.password}")
    String spitter_db_password;


    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
//        Resource config = new ClassPathResource("hibernate.cfg.xml");

        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

//        sessionFactory.setConfigLocation(config);
        sessionFactory.setPackagesToScan(new String[] {"spittr.model.entity"});

        Properties props = new Properties();
        props.setProperty("dialect", "org.hibernate.dialect.PostgreSQLDialect");
        sessionFactory.setHibernateProperties(props);

        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();

        // Driver class name
        ds.setDriverClassName("org.postgresql.Driver");
        // Set URL
        ds.setUrl("jdbc:postgresql://localhost:5432/spitter");
        // Set username & password
        ds.setUsername("spitter");
        ds.setPassword("spitter");
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
