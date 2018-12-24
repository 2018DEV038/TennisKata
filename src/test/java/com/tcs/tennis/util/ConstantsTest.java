package com.tcs.tennis.util;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ConstantsTest {

    @Test
    public void testUtilityConstructor() throws Exception {
        Constants  constants = new Constants();
        assertNotNull(constants);
    }
}