- create cert.
``` 
    keytool -genkeypair -alias ray_key -keyalg RSA -keypass 123456 -keystore ray_key.jks -storepass 123456
```

- check cert.
``` 
    # check common info
    keytool -list -v -keystore ray_key.jks -storepass 123456

    # check pub info
    keytool -list -rfc -keystore ray_key.jks -storepass 123456
```

- save "ray_key.jks" in "srce/main/resources"
