package com.company.Utils;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Class
 *
 * @author Artur Dzidzoiev
 * @version 1.0.0 11/18/13
 */
public class HasherTest extends Assert {
    @Test
    public void testHash0() throws Exception {
        assertEquals("cfcd208495d565ef66e7dff9f98764da",Hasher.hash("0","MD5"));
    }

    @Test
    public void testHashPass() throws Exception {
        assertEquals("1a1dc91c907325c69271ddf0c944bc72",Hasher.hash("pass","MD5"));
    }

    @Test
    public void testHashPassword() throws Exception {
        assertEquals("5f4dcc3b5aa765d61d8327deb882cf99",Hasher.hash("password","MD5"));
    }
}
