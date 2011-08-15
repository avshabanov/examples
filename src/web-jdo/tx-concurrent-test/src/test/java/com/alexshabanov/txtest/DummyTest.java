package com.alexshabanov.txtest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Sample spring-driven test.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/service-test-context.xml" })
public class DummyTest {

    /**
     * Sample test method.
     */
    @Test
    public void testDummy() {
        assertTrue(true);
    }
}
