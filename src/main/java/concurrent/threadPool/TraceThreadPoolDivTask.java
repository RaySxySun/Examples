package concurrent.threadPool;

public class TraceThreadPoolDivTask implements Runnable {
	int a, b;
	
	public TraceThreadPoolDivTask(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}

	@Override
	public void run() {
		double re = a/b;
		System.out.println(re);
	}
	
}