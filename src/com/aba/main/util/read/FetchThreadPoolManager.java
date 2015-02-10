package com.aba.main.util.read;

import java.util.concurrent.*;

/**
 * thread pool manager
 * @author diyong
 *
 */
public class FetchThreadPoolManager{
	
//	private static final int threadPoolSize = 100 ;		//thread number
	private static FetchThreadPoolManager singletonObject = null; 
    private ExecutorService es = null;
    
    private FetchThreadPoolManager() {
//       es = Executors.newFixedThreadPool(Const.FetchThreadPoolSize);
       es = new ThreadPoolExecutor(10,20,600L, TimeUnit.SECONDS,
               new SynchronousQueue<Runnable>());
       
//    	es = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
//                60L, TimeUnit.SECONDS,
//                new SynchronousQueue<Runnable>());
//    	
//    	es = new ThreadPoolExecutor(Const.FetchThreadPoolSize, Const.FetchThreadPoolSize,
//                0L, TimeUnit.MILLISECONDS,
//                new LinkedBlockingQueue<Runnable>());
    }
    
    public static synchronized FetchThreadPoolManager getInstance(){
        if (singletonObject == null) {
            singletonObject = new FetchThreadPoolManager();
        }
        return singletonObject;
    }
    
    public ExecutorService getExecutorService() {
        return es;
    }
}

