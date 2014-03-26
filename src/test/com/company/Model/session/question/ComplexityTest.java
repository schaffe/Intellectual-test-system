package test.com.company.Model.session.question;

import com.company.Model.session.question.Complexity;

import static org.junit.Assert.assertEquals;

/**
 * Class
 *
 * @author Artur Dzidzoiev
 * @version 3/24/14
 */
public class ComplexityTest {
    @org.junit.Test
    public void testGet20() throws Exception {
        assertEquals(Complexity.easy, Complexity.get(20));
        assertEquals(Complexity.easy, Complexity.get(29));
    }
}
