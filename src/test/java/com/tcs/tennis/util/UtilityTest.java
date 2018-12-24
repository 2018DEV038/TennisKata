package com.tcs.tennis.util;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
public class UtilityTest {

    @Test
    public void testUtilityConstructor() throws Exception {
        Utility  utility = new Utility();
        assertNotNull(utility);
    }
}
