import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by huyan on 15/6/25.
 */
public class JdbcTest {

    private static ApplicationContext context;

    @Test
    public void testJdbcInsert(){
        JdbcTemplate jdbcTemplate = (JdbcTemplate)context.getBean("jdbcTemplate");
        jdbcTemplate.execute("insert into user(name) value(1111)");
    }
    @BeforeClass
    public static void initContext(){
        context = new ClassPathXmlApplicationContext("classpath:spring/Application-JDBC.xml");
    }
}
