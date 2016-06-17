package com.jv.utils.thrman;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  ThreadManager
 *
 *  Use this class in your application to easily execute task in parallel at the same time.
 *
 *  This class has primitive lifecycle:
 *      - not created
 *      - created
 *      - initialized
 *      - destroyed
 *
 *  If there would be situation that threadPool is already shut down expect IllegalStateException.
 *
 *  Tip: use onDestroy if application context is destroyed.
 *
 *  @see Runnable
 *  @see ExecutorService
 *
 * @author jv on 6/17/2016
 * @version 1.0.0
 */
public class ThreadManager {

    private static ThreadManager instance;

    public static final int HOW_MANY_THREADS = 15;

    private ExecutorService threadPoolExecutor;

    private ThreadManager() {
        threadPoolExecutor = Executors.newFixedThreadPool(HOW_MANY_THREADS);
    }

    public static ThreadManager getInstance() {
        if (instance == null)
            instance = new ThreadManager();
        return instance;
    }

    public void onDestroy() {
        threadPoolExecutor.shutdown();
    }

    public void execute(Runnable runnable) {
        if (!threadPoolExecutor.isShutdown())
            threadPoolExecutor.execute(runnable);
        else
            throw new IllegalStateException("Thread pool executor already destroyed");
    }

}
