The `SslContextBuilder` and so Netty´s `SslContext` implementations only support PKCS8 keys. 

If you have a key with another format you need to convert it to PKCS8 first to be able to use it. This can be done easily by using openssl. 

For example to convert a non-encrypted PKCS1 key to PKCS8 you would use:

`openssl pkcs8 -topk8 -nocrypt -in pkcs1_key_file -out pkcs8_key.pem`

For more details please see the [OpenSSL docs](https://www.openssl.org/docs/manmaster/apps/pkcs8.html).




