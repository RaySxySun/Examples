Netty provides the following platform specific JNI transports:

- Linux (since 4.0.16)
- MacOS/BSD (since 4.1.11)

These JNI transports add features specific to a particular platform, generate less garbage, and generally improve performance when compared to the NIO based transport.

## Using the native transports

Note that you must specify the proper classifier for the dependency.  `os-maven-plugin` in the `extensions` section in the `pom.xml` file will set `os.detected.name` and `os.detected.arch` properties automatically.  For more information. refer to [the homepage of the `os-maven-plugin`](https://github.com/trustin/os-maven-plugin).

### Using the Linux native transport

Because the native transport is compatible with the NIO transport, you can just do the following search-and-replace:

* `NioEventLoopGroup` → `EpollEventLoopGroup`
* `NioEventLoop` → `EpollEventLoop`
* `NioServerSocketChannel` → `EpollServerSocketChannel`
* `NioSocketChannel` → `EpollSocketChannel`

Because the native transport is not part of the Netty core, you need to pull the `netty-transport-native-epoll` as a dependency in your Maven `pom.xml`:

```xml
  <build>
    <extensions>
      <extension>
        <groupId>kr.motd.maven</groupId>
        <artifactId>os-maven-plugin</artifactId>
        <version>1.5.0.Final</version>
      </extension>
    </extensions>
    ...
  </build>

  <dependencies>
    <dependency>
      <groupId>io.netty</groupId>
      <artifactId>netty-transport-native-epoll</artifactId>
      <version>${project.version}</version>
      <classifier>${os.detected.name}-${os.detected.arch}</classifier>
    </dependency>
    ...
  </dependencies>
```

For using the native transport in a [sbt](http://www.scala-sbt.org/) project, please add line below to your `libraryDependencies`:
```
"io.netty" % "netty-transport-native-epoll" % "${project.version}" classifier "linux-x86_64"
```

### Using the MacOS/BSD native transport

Because the native transport is compatible with the NIO transport, you can just do the following search-and-replace:

* `NioEventLoopGroup` → `KQueueEventLoopGroup`
* `NioEventLoop` → `KQueueEventLoop`
* `NioServerSocketChannel` → `KQueueServerSocketChannel`
* `NioSocketChannel` → `KQueueSocketChannel`

Because the native transport is not part of the Netty core, you need to pull the `netty-transport-native-kqueue` as a dependency in your Maven `pom.xml`:

```xml
  <build>
    <extensions>
      <extension>
        <groupId>kr.motd.maven</groupId>
        <artifactId>os-maven-plugin</artifactId>
        <version>1.5.0.Final</version>
      </extension>
    </extensions>
    ...
  </build>

  <dependencies>
    <dependency>
      <groupId>io.netty</groupId>
      <artifactId>netty-transport-native-kqueue</artifactId>
      <version>${project.version}</version>
      <classifier>${os.detected.name}-${os.detected.arch}</classifier>
    </dependency>
    ...
  </dependencies>
```

For using the native transport in a [sbt](http://www.scala-sbt.org/) project, please add line below to your `libraryDependencies`:
```
"io.netty" % "netty-transport-native-kqueue" % "${project.version}" classifier "osx-x86_64"
```

## Building the native transports

If you already have the JAR file of the native transport, you should not need to build the native transport by yourself because the JAR file already contains the necessary shared library files (e.g. `.so`, `.dll`, `.dynlib`) and they will be loaded automatically.

### Building the Linux native transport
To build the native transport, you need to use Linux with 64-bit kernel 2.6 or higher.  Please also install the required tools and libraries:

```bash
# RHEL/CentOS/Fedora:
sudo yum install autoconf automake libtool make tar \
                 glibc-devel libaio-devel \
                 libgcc.i686 glibc-devel.i686
# Debian/Ubuntu:
sudo apt-get install autoconf automake libtool make tar \
                     gcc-multilib libaio-dev
```

### Building the MacOS/BSD native transport
To build the native transport, you need to use MacOS 10.12 or higher.  Please also install the required tools and libraries:

```bash
brew install autoconf automake libtool
```