### GC Fact

- Throughput and latency are the two main drivers towards refinement of GC algorithm.

---

- Maximize Throughput:

```
    Throughput maximizing
    Generational            -> Young, Old
    Parallel work           -> stop-the-world threads, Concurrent Threads
```

- Latency Sensitive:

```
    Latency Sensitive
    Pause only when needed      -> No/Partial Compaction (CMS)
    Concurrent Marking/Sweeping -> reduce stop-the-world GC pause time (keep it to a bare minimum)

```

---

- HotSpot GCs

```
    -----------------------------------------------------------
        Throughput              Both                Latency
    -----------------------------------------------------------
        Parallel GC            G1 GC                CMS GC
    ------------------------------------------------------------
```

---

### Tune for throughput:

- GC overhead:
  - GC overhead is an indication of the frequency of stop-the-world GC events.
    - less frequent GC events == more application time == higher application throughput.

- Biggest Contributors: Allocation rates and promotion rates are biggest contributors to GC overhead.

- Calculating (1) Allocation Rate & (2) Promotion Rate

```
38.692 [GC(Allocation Failure) [PSYoungGen:917486K->131054K(858112K)] 1004844K->224424K(2955264K), 0.0827570 secs] [Times: user=0.61 sys=0.00, real=0.08 secs]
51.013 [GC(Allocation Failure) [PSYoungGen:858094K->50272K(777728K)] 951464K->239014K(2874880K), 0.1414536 secs][Times: user=0.70 sys=0.07, real=0.14sec]

Allocation Rate = (858094K - 131054K) / (51.013 - 38.692) = 57.6MB/s
Promotion Rate = ((239014k - 50272K) - (951464 - 858094)) / (51.013 - 38.692) = 7.56MB/s
```
- Tuning to Reduce GC Overhead:
  - size generation keeping your app's object longevity and size in mind.
  - the faster the generation gets "filled", the sooner a GC is triggered.

- Premature promotions are a big problem!!!

---

### Tune for latency: (Latency facts)

- Minimal GC time == Maximum app responsiveness
- GC elapsed time is an indication of the amount of time it takes to execute stop-the-world GC events.
- Faster execution of GC events == Lower GC induced latencies == Higher app responsiveness

- Biggest Contributors: Copying/Moving costs are the biggest contributors to GC elapsed time

```
        Copying Costs Using G1 GC
        0.283:[GC pause(G1 Evacuation Pause)(young),0.0270523 secs]
            [Parallel Time: 24.3ms, GC Workers:8]
            ...
            <snip>
                [Object Copy(ms): Min: 22.6,Avg:23.2,Max:23.6,Diff:1.1,Sum:185.7]
                ...
            <snip>
```

- Full GCs are expensive, so you want to use more young GCs.(more minor GC, young collections)

- Tuning to Reduce GC Elapsed Times
  - size generation and age objects appropriately
  - The higher the amount of live data to be copied, the longer the GC pause.

- Premature promotions are a big problem!!! (single thread)

---

### G1 GC

- Facts
  - G1 GC is the newest collector in the HotSpot family
  - G1 GC is based on a new regionalized framework
  - For G1 GC, the regions that form a particular generation are not contiguous.
  - G1 GC has concurrent marking phase to identify candidate old regions for efficient.
  - G1 GC has stop-the-world pauses that employ parallel threads
  - G1 GC performs incremental compaction also known as partial complaction or mixed collection.

```
    Traditional Java Heap
|-------------------------------------|
|         Eden          |  S0  |  S1  |
|                       |      |      |
|-------------------------------------|
|           Old Generation            |
|-------------------------------------|

    G1 Java Heap
    - free region
    - non-free region
      - non-free region could be Young Region, Old Region or Humongous Regions
```

- G1 GC Heap Regions:
  - Target is 2048 regions
  - Region size can be anywhere between 1 MB to 32 MB
  - Region size has to be a power of 2
  - Region has to be aligned
  - -XX:G1RegionSize can override the default region size selected by the JVM
  - The number of regions in a particular generation can change between collections based on G1 GC's adaptive sizing heuristics.
  
---

- G1 GC Pause Metrics (G1 GC works with these optionsOB) 

```
[General Option] -XX:MaxGCPauseMillis: it determines the GC pause duration that users may be able to tolerate.
[General Option] -XX:GCPauseIntervalMillis: it determins the GC frequency
[G1 Option] -XX:G1NewSizePercent: it sets the minimum young generation size
[G1 Option] -XX:G1MaxNewSizePercent: it sets the maximum young generation size

> XX:MaxGCPauseMillis: it always needs to be less than -XX:GCPauseIntervalMillisOA
> By default -XX:MaxGCPauseMillis is set to 200ms and -XX:GCPauseIntervalMillis is set to -XX:MaxGCPauseMillis + 1
```

---

- G1 GC's pause time goal is a hint (not hard real-time)
  - Adaptive sizing logic: 
    - it adjusts the size of the young generation after every collection so as to ensure that the prediction logic and the pasuse time goal are close enough
    - young generation can range anywhere from 5% to 60% of your heap size
    - experimental option -XX:G1NewSizePercent: it sets the minimum young genenration size

---

- GC1 RSet & CSet 

- G1 Remembered Sets aka RSet:
  - One per collection
  - Maintains and tracks incoming old-to-young references and old-to-old references
  ![Image of Yaktocat](./img/Rset.PNG)
  
- G1 Collection Set aka CSet
  - One per collection
  - A set of regions that will be reclaimed during a collection pause
  - Live data in a CSet is moved/copied to region(s) outside the CSet
  - After the collection cycle, the regions in the cycle's CSet are freed and returned to the list of free regions
  - A young collection can only have young regions (Eden & Survivor) in its CSet
  - A mixed collection will have young and old regions in its CSet

- Rset & CSet working together:
  - During a collection pause, G1 GC only needs to scan the RSets of the regions in the CSet of that collection.

---

- Phases of a G1 GC Young Collection
  - (1) Parallel phase (after it completes, the sequencial phase starts) 
    - Employs multiple GC worker threads
    - Each worker thread has its own task queue
    - A GC worker thread will enter a termination protocol after completing its task.
    - steall work from others. Once a thread is in the termination protocol, it can attempt to steal work from
      other threads that may still be carrying out their tasks.
    - Parallel activities terminate once all the worker threads are done with their tasks.

  - (2) Sequential Phase 
    - After the parallel phase completes, the sequential tasks can be carried out.
    - each task needs to finish before the nect can start
    - Apart from listing the big grocers, a lot of independent activities that are carried out sequentially are 
      clubbed under the "other" tag.













触发JVM进行Full GC的情况
1) System.gc()方法的调用
2) 老年代代空间不足
3) 永生区空间不足
4) CMS GC时出现promotion failed和concurrent mode failure
5) 统计得到的Minor GC晋升到旧生代的平均大小大于老年代的剩余空间
6) 堆中分配很大的对象
---------------------------------------------------------------------------
1、System.gc()方法的调用

 

此方法的调用是建议JVM进行Full GC,虽然只是建议而非一定,但很多情况下它会触发 Full GC,从而增加Full GC的频率,也即增加了间歇性停顿的次数。强烈影响系建议能不使用此方法就别使用，让虚拟机自己去管理它的内存，可通过通过-XX:+ DisableExplicitGC来禁止RMI调用System.gc。


 2、老年代代空间不足


老年代空间只有在新生代对象转入及创建为大对象、大数组时才会出现不足的现象，当执行Full GC后空间仍然不足，则抛出如下错误：
java.lang.OutOfMemoryError: Java heap space 
为避免以上两种状况引起的Full GC，调优时应尽量做到让对象在Minor GC阶段被回收、让对象在新生代多存活一段时间及不要创建过大的对象及数组。


3、永生区空间不足


JVM规范中运行时数据区域中的方法区，在HotSpot虚拟机中又被习惯称为永生代或者永生区，Permanet Generation中存放的为一些class的信息、常量、静态变量等数据，当系统中要加载的类、反射的类和调用的方法较多时，Permanet Generation可能会被占满，在未配置为采用CMS GC的情况下也会执行Full GC。如果经过Full GC仍然回收不了，那么JVM会抛出如下错误信息：
java.lang.OutOfMemoryError: PermGen space 
为避免Perm Gen占满造成Full GC现象，可采用的方法为增大Perm Gen空间或转为使用CMS GC。


 4、CMS GC时出现promotion failed和concurrent mode failure


对于采用CMS进行老年代GC的程序而言，尤其要注意GC日志中是否有promotion failed和concurrent mode failure两种状况，当这两种状况出现时可能

会触发Full GC。
promotion failed是在进行Minor GC时，survivor space放不下、对象只能放入老年代，而此时老年代也放不下造成的；concurrent mode failure是在

执行CMS GC的过程中同时有对象要放入老年代，而此时老年代空间不足造成的（有时候“空间不足”是CMS GC时当前的浮动垃圾过多导致暂时性的空间不足触发Full GC）。
 对措施为：增大survivor space、老年代空间或调低触发并发GC的比率，但在JDK 5.0+、6.0+的版本中有可能会由于JDK的bug29导致CMS在remark完毕

后很久才触发sweeping动作。对于这种状况，可通过设置-XX: CMSMaxAbortablePrecleanTime=5（单位为ms）来避免。


 5、统计得到的Minor GC晋升到旧生代的平均大小大于老年代的剩余空间


 这是一个较为复杂的触发情况，Hotspot为了避免由于新生代对象晋升到旧生代导致旧生代空间不足的现象，在进行Minor GC时，做了一个判断，如果之

前统计所得到的Minor GC晋升到旧生代的平均大小大于旧生代的剩余空间，那么就直接触发Full GC。
 例如程序第一次触发Minor GC后，有6MB的对象晋升到旧生代，那么当下一次Minor GC发生时，首先检查旧生代的剩余空间是否大于6MB，如果小于6MB，

则执行Full GC。
 当新生代采用PS GC时，方式稍有不同，PS GC是在Minor GC后也会检查，例如上面的例子中第一次Minor GC后，PS GC会检查此时旧生代的剩余空间是否

大于6MB，如小于，则触发对旧生代的回收。
 除了以上4种状况外，对于使用RMI来进行RPC或管理的Sun JDK应用而言，默认情况下会一小时执行一次Full GC。可通过在启动时通过- java -

Dsun.rmi.dgc.client.gcInterval=3600000来设置Full GC执行的间隔时间或通过-XX:+ DisableExplicitGC来禁止RMI调用System.gc。

 

6、堆中分配很大的对象

 

所谓大对象，是指需要大量连续内存空间的java对象，例如很长的数组，此种对象会直接进入老年代，而老年代虽然有很大的剩余空间，但是无法找到足够大的连续空间来分配给当前对象，此种情况就会触发JVM进行Full GC。

为了解决这个问题，CMS垃圾收集器提供了一个可配置的参数，即-XX:+UseCMSCompactAtFullCollection开关参数，用于在“享受”完Full GC服务之后额外免费赠送一个碎片整理的过程，内存整理的过程无法并发的，空间碎片问题没有了，但提顿时间不得不变长了，JVM设计者们还提供了另外一个参数 -XX:CMSFullGCsBeforeCompaction,这个参数用于设置在执行多少次不压缩的Full GC后,跟着来一次带压缩的。
