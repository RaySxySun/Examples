- separate service start operation

```
    Channel ch = b.bind(PORT).sync().channel();
    /* comment out the below code because it will be pending,
     * there will be no chance the execute the following code.
     */
    //b.bind(PORT).sync().channel().closeFuture().sync();
    System.err.println("Please open your web browser and navigate to " + (SSL ? "https" : "http") + "://127.0.0.1:" + PORT + "/");
    ch.closeFuture().sync();

```

---

- CORS Config

```
    CorsConfig corsConfig = CorsConfigBuilder.forAnyOrigin().allowNullOrigin().allowCredentials().build();
    pipeline.addLast(new CorsHandler(corsConfig));

```