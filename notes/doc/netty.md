
## The diagram for ChannelPipeline 

- The following diagram describes how I/O events are processed by {@link ChannelHandler}s in a {@link ChannelPipeline} typically. An I/O event is handled by either a {@link ChannelInboundHandler} or a {@link ChannelOutboundHandler}
 
```
                                                  I/O Request
                                             via {@link Channel} or
                                         {@link ChannelHandlerContext}
                                                       |
   +---------------------------------------------------+---------------+
   |                           ChannelPipeline         |               |
   |                                                  \|/              |
   |    +---------------------+            +-----------+----------+    |
   |    | Inbound Handler  N  |            | Outbound Handler  1  |    |
   |    +----------+----------+            +-----------+----------+    |
   |              /|\                                  |               |
   |               |                                  \|/              |
   |    +----------+----------+            +-----------+----------+    |
   |    | Inbound Handler N-1 |            | Outbound Handler  2  |    |
   |    +----------+----------+            +-----------+----------+    |
   |              /|\                                  .               |
   |               .                                   .               |
   | ChannelHandlerContext.fireIN_EVT() ChannelHandlerContext.OUT_EVT()|
   |        [ method call]                       [method call]         |
   |               .                                   .               |
   |               .                                  \|/              |
   |    +----------+----------+            +-----------+----------+    |
   |    | Inbound Handler  2  |            | Outbound Handler M-1 |    |
   |    +----------+----------+            +-----------+----------+    |
   |              /|\                                  |               |
   |               |                                  \|/              |
   |    +----------+----------+            +-----------+----------+    |
   |    | Inbound Handler  1  |            | Outbound Handler  M  |    |
   |    +----------+----------+            +-----------+----------+    |
   |              /|\                                  |               |
   +---------------+-----------------------------------+---------------+
                   |                                  \|/
   +---------------+-----------------------------------+---------------+
   |               |                                   |               |
   |       [ Socket.read() ]                    [ Socket.write() ]     |
   |                                                                   |
   |  Netty Internal I/O Threads (Transport Implementation)            |
   +-------------------------------------------------------------------+
```

---

### ByteBuf:

```
  BEFORE clear()

       +-------------------+------------------+------------------+
       | discardable bytes |  readable bytes  |  writable bytes  |
       +-------------------+------------------+------------------+
       |                   |                  |                  |
       0      <=      readerIndex   <=   writerIndex    <=    capacity
 
 
   AFTER clear()
 
       +---------------------------------------------------------+
       |             writable bytes (got more space)             |
       +---------------------------------------------------------+
       |                                                         |
       0 = readerIndex = writerIndex            <=            capacity
 ```