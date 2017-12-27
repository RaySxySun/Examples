package concurrent.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExtendThreadPool {
	public static class MyTask implements Runnable {
		public String name;
		
		public MyTask(String name) {
			super();
			this.name = name;
		}

		@Override
		public void run() {
			System.out.println("executing: " + "Thread ID: " + Thread.currentThread().getId() + ", Task Name: " + name);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService es = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(10)){
			
			@Override
			protected void beforeExecute(Thread t, Runnable r) {
				System.out.println("before executing" + ((MyTask) r).name);
			}
			
			@Override
			protected void afterExecute(Runnable r, Throwable th) {
				System.out.println("after executing" + ((MyTask) r).name);
			}
			
			@Override
			protected void terminated() {
				System.out.println("exit thread pool");
			}
			
		};
		for(int i = 0; i < 5; i++) {
			MyTask task = new MyTask("Task-" + i);
			es.execute(task);
			Thread.sleep(10);
		}
		es.shutdown();
	}
}
