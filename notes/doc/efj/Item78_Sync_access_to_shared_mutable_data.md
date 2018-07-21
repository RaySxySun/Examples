- mutual exclusion: 互斥
  - Many programmer think of synchronization solely as a means of mutual exclusion, to prevent an object from being seen in an inconsistent state by one thread while it's being modified by another.
  - this view is correct, but it's only half the story

- state transition:
  - transforming the object from one consistent state to another.

- Java language specification
  - it gurantees that reading or writing a variable is atomic unless the variable is the type long or double. - dispense with: 免除, 省掉 
  - you may hear it said that improve performance, you should dispense with synchronization when reading or writing atomic data.
  - (visibility)Java guarantees that a thread will not see an arbitrary value when reading a field, but it does NOT  guarantee that a value written by on thread will be visible to another. 

- communication
  - Synchronization is required for reliable communication between threads as well as for mutual exclusion.
  
- typical case for visibility:
  - DO NOT use Thread.stop (deprecate)
  - INSTEAD, polling a boolean field(init: false), set to true by 2nd thread to indicate that the 1st thread is to stop itself.
  - In the absence of (省略) synchronization, it's quite acceptable for the virtual machine to transform this code.
  
- A typical application scenario for "volatile" 
- hoisting: 变量声明提升 
- liveness failure: 活性失败 the program fails to make progress(继续)
- SOLUTION: use "volatile"
  ```
    while(!stopRequested)
      i++;
      
    // =====VM optimization======>
    
    if(!stopRequested)
        while(true)
            i++;
  ```

- is not guaranteed
- deceiving: 迷惑人的, 欺骗人的
  - Synchronization is not guaranteed to work unless both read and write operations are synchronized.

- be omitted: 被省略
  - the locking can be omitted if "stopRequested" is declared volatile.

- interleaved: 交错的
  - "++" operation in multi threads will be performed interleaved
  - synchronization ensures that multiple invocations won't be interleaved and that each invocation of the method will see the effects of all previous invations.

- java.util.concurrent.atomic
  - it provides primitives(原始操作) for lock-free, thread-safe programming on single variables.

- confine: 限制
  - confine mutable data to a single thread.
  - Either share immutable data or don't share at all.

- R/W splitting: 读写分离
- effectively immutable:
- safe publication: 5 ways(static field, volatile field, final field, locked field, concurrent collection)
  - it is acceptable for one thread to modify a data object for a while and then to share it with other threads, synchronizing only the act of sharing the object reference.
    . Other threads can then read the object without further synchronization. (effectively immutable)
  - such objects are said to be effectively immutable.
  - transferring such an object reference from one thread to others is called safe publication.

- Important things need to be repeated for three times. 
  - safe publication: 5 ways(static field, volatile field, final field, locked field, concurrent collection)
  - safe publication: 5 ways(static field, volatile field, final field, locked field, concurrent collection)
  - safe publication: 5 ways(static field, volatile field, final field, locked field, concurrent collection)
