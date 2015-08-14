import com.myorg.springaction.springdo2.MindReader;
import com.myorg.springaction.springdo2.Thinker;
import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by huyan on 15/6/24.
 */
public class SpringTest {

    private static ApplicationContext context;

    @Test
    public void test(){
        Thinker volunteer = (Thinker)context.getBean("volunteer");
        MindReader magician = (MindReader)context.getBean("magician");
        volunteer.thinkOfSomething("what am i thinking");
        Assert.assertEquals("what am i thinking", magician.getThought());
    }

    @BeforeClass
    public static void initContext(){
        context = new ClassPathXmlApplicationContext("classpath:spring/Application-springdo2.xml");
    }
}
