> http://ifeve.com/selectors/

### Three Main Components

- Channels
- Buffers
- Selectors

```
            Thread
              |
              |
           Selector
          |   |   |
      -----   |   -----
      |       |       |
   Channel Channel  Channel

```

---

### Four Nio Channels:
    
- FileChannel (FILE)
- DatagramChannel (UDP)
- SocketChannel (TCP)
- ServerSocketChannel (SERVER)

> please note that only "FileChannel" Can't be set as non-blocking mode
> channel.configureBlocking(false); 

---

### Buffers

- ByteBuffer 
- CharBuffer
- DoubleBuffer
- FloatBuffer
- IntBuffer
- LongBuffer
- ShortBuffer

---

### Selector

```
Selector.open();
channel.configureBlocking(false);
SelectionKey key = channel.register(selector,
	Selectionkey.OP_READ);
```
