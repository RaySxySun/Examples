If you're developing low-latency network applications with Netty, you're probably aware of the thing called thread affinity. Thread affinity can be used to force your application threads to run on a particular CPU core or set of CPUs. 
By doing so, you can eliminate threads migration during the operating system scheduling process. Fortunately, there's a java library called [Java-Thread-Affinity](https://github.com/OpenHFT/Java-Thread-Affinity) which can be easily integrated with your Netty application.

First, add the following dependency to your Maven `pom.xml` file:

```xml
<dependency>
    <groupId>net.openhft</groupId>
    <artifactId>affinity</artifactId>
    <version>3.0.6</version>
</dependency>
```

Second, create an AffinityThreadFactory with a particular strategy and pass it to the EventLoopGroup which would contain latency-sensitive threads.
Here's an example:

```java
final int acceptorThreads = 1;
final int workerThreads = 10;
EventLoopGroup acceptorGroup = new NioEventLoopGroup(acceptorThreads);
ThreadFactory threadFactory = new AffinityThreadFactory("atf_wrk", AffinityStrategies.DIFFERENT_CORE);
EventLoopGroup workerGroup = new NioEventLoopGroup(workerThreads, threadFactory);

ServerBootstrap serverBootstrap = new ServerBootstrap().group(acceptorGroup, workerGroup);
```

Notice, to achieve the lowest latency possible you should consider isolating your target CPUs from OS scheduler. Isolating target CPUs will prevent OS scheduler to schedule any other user-space processes on these CPUs. This can be done with `isolcpus` kernel boot parameter (i.e. add `isolcpus=<cpu-list>` to `grub.conf`).

For more information, please visit the project's [Github](https://github.com/OpenHFT/Java-Thread-Affinity).