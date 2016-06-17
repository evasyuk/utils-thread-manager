package com.jv.utils.thrman;

import static org.junit.Assert.*;

/**
 * Created by jv on 6/17/2016.
 */
public class ThreadManagerTest {

    @org.junit.Test
    public void testGetInstance() throws Exception {
        assertTrue(ThreadManager.getInstance() != null);
    }

    @org.junit.Test
    public void testOnDestroy() throws Exception {
        ThreadManager instance = ThreadManager.getInstance();
        assertTrue(instance != null);

        instance.onDestroy();
        try {
            instance.execute(new Runnable() {
                @Override
                public void run() {
                    //assertTrue(true);
                }
            });
        } catch (IllegalStateException exc) {
            assertTrue(true);
        }
    }

    @org.junit.Test
    public void testExecute() throws Exception {
        ThreadManager instance = ThreadManager.getInstance();
        assertTrue(instance != null);

        instance.execute(new Runnable() {
            @Override
            public void run() {
                assertTrue(true);
            }
        });
    }
}