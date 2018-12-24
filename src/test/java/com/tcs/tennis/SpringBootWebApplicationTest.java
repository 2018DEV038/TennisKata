
package com.tcs.tennis;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;


@SpringBootTest
public class SpringBootWebApplicationTest {

    @Test
    public void testMain() throws Exception {
        SpringBootWebApplication.main(new String []{});
    }
}