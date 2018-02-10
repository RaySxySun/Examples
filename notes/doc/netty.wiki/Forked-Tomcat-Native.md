[netty-tcnative](https://github.com/netty/netty-tcnative) is a fork of [Tomcat Native](http://tomcat.apache.org/native-doc/).  It includes a set of changes contributed by Twitter, Inc, such as:

* Simplified distribution and linkage of native library
* Complete mavenization of the project
* Improved OpenSSL support

To minimize the maintenance burden, we create a dedicated branch for each stable upstream release and apply our own changes on top of it, while keeping the number of maintained branches to minimum.

## Artifacts

`netty-tcnative` is a multi-module project and builds a few artifacts, allowing it to be used in a variety of environments.

 ArtifactId | Description | On Maven Central
 ---------- | ----------- | ----------------
 netty-tcnative-{os_arch} | This is the "default" artifact which is dynamically linked to both [libapr-1](https://apr.apache.org/) and [OpenSSL](https://www.openssl.org/). To use this artifact, your system must have both libapr-1 and OpenSSL installed and configured. This artifact is useful for production environments where the site administrator is free to upgrade OpenSSL without having to rebuild the application. | yes
netty-tcnative-boringssl-static-{os_arch} | This artifact is statically linked against Google's [boringssl](https://boringssl.googlesource.com/), which is a fork of OpenSSL that has a reduced code footprint and additional features (such as ALPN) which at the time of this writing have not made an appearance in a stable release of linux. The static linking makes it easier to get started using tcnative on a system without having to worry about additional install steps. This library does not require APR.| yes
netty-tcnative-boringssl-static | This is an uber jar that contains the statically linked libraries for all supported `netty-tcnative-boringssl-static-{os_arch}`. While this tends to be a fairly large jar, it greatly simplifies the getting started experience since an application need not be concerned with obtaining the correct jar for the platform.  | yes
netty-tcnative-openssl-static-{os_arch} | This artifact is statically linked against both libapr-1 and OpenSSL, making it easier to get started using tcnative on a system without having to worry about additional install steps. | no
netty-tcnative-libressl-static-{os_arch} | Coming soon. | no

## How to download netty-tcnative-boringssl "uber" jar

This is the simplest dependency to use, since it doesn't require a platform-specific classifier:

```xml
<project>
  ...
  <dependencies>
    ...
    <dependency>
      <groupId>io.netty</groupId>
      <artifactId>netty-tcnative-boringssl-static</artifactId>
      <version>2.0.0.Final</version>
    </dependency>
    ...
  </dependencies>
  ...
</project>
```

## How to download netty-tcnative-boringssl-static

In your application's `pom.xml`, add the `os-maven-plugin` extension and the `netty-tcnative-boringssl-static` dependency appropriate for your platform:

```xml
<project>
  ...
  <dependencies>
    ...
    <dependency>
      <groupId>io.netty</groupId>
      <artifactId>netty-tcnative-boringssl-static</artifactId>
      <version>2.0.0.Final</version>
      <classifier>${os.detected.classifier}</classifier>
    </dependency>
    ...
  </dependencies>
  ...
  <build>
    ...
    <extensions>
      <extension>
        <groupId>kr.motd.maven</groupId>
        <artifactId>os-maven-plugin</artifactId>
        <version>1.4.0.Final</version>
      </extension>
    </extensions>
    ...
  </build>
  ...
</project>
```

Netty-tcnative uses classifiers when deploying to [Maven Central](http://repo1.maven.org/maven2/io/netty/netty-tcnative/) and provide distributions for the following platforms:

Classifier | Description
---------------- | -----------
windows-x86_64 | Windows distribution
osx-x86_64 | Mac distribution
linux-x86_64 | Linux distribution

## How to download netty-tcnative

In your application's `pom.xml`, add the `os-maven-plugin` extension and `netty-tcnative` dependency:

```xml
<project>
  <properties>
    <!-- Configure the os-maven-plugin extension to expand the classifier on -->
    <!-- Fedora-"like" systems. -->
     <os.detection.classifierWithLikes>fedora</os.detection.classifierWithLikes>
  </properties>
  ...
  <dependencies>
    ...
    <dependency>
      <groupId>io.netty</groupId>
      <artifactId>netty-tcnative</artifactId>
      <version>2.0.0.Final</version>
      <classifier>${os.detected.classifier}</classifier>
    </dependency>
    ...
  </dependencies>
  ...
  <build>
    ...
    <extensions>
      <extension>
        <groupId>kr.motd.maven</groupId>
        <artifactId>os-maven-plugin</artifactId>
        <version>1.4.0.Final</version>
      </extension>
    </extensions>
    ...
  </build>
  ...
</project>
```

Netty-tcnative uses classifiers when deploying to [Maven Central](http://repo1.maven.org/maven2/io/netty/netty-tcnative/) to provide distributions for the various platforms. On Linux it should be noted that OpenSSL uses a different soname for Fedora derivatives than other Linux releases. Starting with version `1.1.33.Fork7`, we work around this limitation by deploying two separate versions for Linux (see the table below).

Classifier | Description
---------------- | -----------
windows-x86_64 | Windows distribution
osx-x86_64 | Mac distribution
linux-x86_64 | Used for non-Fedora derivatives of Linux
linux-x86_64-fedora | Used for Fedora derivatives

Using `classifierWithLikes` will configure the `os-maven-plugin` to alter the generated `os.detected.classifier` property. If your build contains other dependencies that rely on `os.detected.classifier`, you can manually create the netty-tcnative classifier with the `antrun` plugin:

```xml
<project>
  <dependencies>
    <dependency>
      <groupId>io.netty</groupId>
      <artifactId>netty-tcnative</artifactId>
      <version>2.0.0.Final</version>
      <classifier>${tcnative.classifier}</classifier>
    </dependency>
  </dependencies>

  <build>
    <extensions>
      <!-- Use os-maven-plugin to initialize the "os.detected" properties -->
      <extension>
        <groupId>kr.motd.maven</groupId>
        <artifactId>os-maven-plugin</artifactId>
        <version>1.4.0.Final</version>
      </extension>
    </extensions>
    <plugins>
      <!-- Use Ant to configure the appropriate "tcnative.classifier" property -->
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-antrun-plugin</artifactId>
        <executions>
          <execution>
            <phase>initialize</phase>
            <configuration>
              <exportAntProperties>true</exportAntProperties>
              <target>
                <condition property="tcnative.classifier"
                           value="${os.detected.classifier}-fedora"
                           else="${os.detected.classifier}">
                  <isset property="os.detected.release.fedora"/>
                </condition>
              </target>
            </configuration>
            <goals>
              <goal>run</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
    </plugins>
  </build>
</project>
```

## How to use

To use the OpenSSL-based `SSLEngine` implementation, use `io.netty.handler.ssl.SslContext` class:

```java
public static void main(String[] args) throws Exception {
  File certificate = new File("certificate");
  File privateKey = new File("privateKey");
  SslContext sslContext =
    SslContextBuilder.forServer(certificate, privateKey)
                     .sslProvider(SslProvider.OPENSSL)
                     .build();
  ...
}

public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

  private final SslContext sslCtx;
  
  public MyChannelInitializer(SslContext sslCtx) {
    this.sslCtx = sslCtx;
  }
  
  @Override
  public void initChannel(SocketChannel ch) throws Exception {
    ChannelPipeline p = ch.pipeline();
    if (sslCtx != null) {
      p.addLast(sslCtx.newHandler(ch.alloc()));
    }
    ...
  }
}
```

Netty will unpack the shared library provided by the netty-tcnative-XXX artifact into a temporary directory and load it using `java.lang.System.load(String)`.

### Prerequisites for Dynamically Linked `netty-tcnative`

Note that the shared library in the `netty-tcnative` artifact is dynamically linked against Apache Portable Runtime (APR) and OpenSSL.  They must be in the library load path such as system library directories, `$LD_LIBRARY_PATH`, and `%PATH%`.

* If you are on Linux, you probably don't need to do anything because you can install them using your system's package manager.
* If you are on Mac, you have to install `openssl` package using [Homebrew](http://brew.sh/).
* If you are on Windows, you have to:
  * Build APR by yourself,
  * Install [OpenSSL for Windows](http://slproweb.com/products/Win32OpenSSL.html), and
  * Add the directories that contain the .DLL files to `%PATH%`.

**Important:** If you use Linux and want to make use of ALPN (you will need this for http2) you need to ensure you have openssl >= 1.0.2 installed. If this is not provided by the package system of your distro you will need to compile it by your own and set the LD_LIBRARY_PATH as explained in [How to built](http://netty.io/wiki/forked-tomcat-native.html#wiki-h2-2).

## How to build

You don't usually need to build `netty-tcnative` by yourself because we officially ship the JARs with the native libraries for `Linux x86_64`, `Mac OS X x86_64`, and `Windows x86_64`.  If you are looking for a SNAPSHOT build, browse [Sonatype Snapshots](https://oss.sonatype.org/content/repositories/snapshots/io/netty/).

If you are on a platform we don't ship the JARs with the native libraries, such as Windows x86_32, follow the instructions in this section.


### Updating and Verifying Dependencies

It is important to verify the integrity of third party dependencies when building on your own. Also, dependency updates in the build will trigger checksum failures, and the package maintainers should follow this process to ensure the integrity of the package before updating the checksum to match.

### autoconf
- download tgz and tgz.sig from http://ftp.gnu.org/gnu/autoconf/autoconf-X.Y.Z.tar.gz
- `gpg --keyserver keys.gnupg.net --recv-keys 2527436A`
- `gpg --verify autoconf-X.Y.Z.tar.gz.sig`

### apr
- download at https://apr.apache.org/download.cgi
- Securely fetch the MD5 sum for apr: the apache site links to http, but modify that to https
- Paste the sum into a file
- Verify the sum `md5 -r apr-X.Y.Z.tar.gz | cut -d " " -f 1 | diff -u apr-X.Y.Z.tar.gz.md5 -`

### libressl
- signify public key: RWQg/nutTVqCUVUw8OhyHt9n51IC8mdQRd1b93dOyVrwtIXmMI+dtGFe
- (you can download hte public key at https://www.openbsd.org/libressl/signing.html)
- download tarball, signature, and SHA256.sig at http://ftp.openbsd.org/pub/OpenBSD/LibreSSL/
- verify using libressl.pub from https://www.openbsd.org/libressl/signing.html:
`signify -C -x SHA256.sig  -p /path/to/libressl.pub libressl-X.Y.Z.tar.gz`


### openssl
- Download tarball and SHA256 signature from https://www.openssl.org/source/
- Verify the sum `sha1sum openssl-X.Y.Z.tar.gz | cut -d " " -f 1 | diff -u openssl-X.Y.Z.tar.gz.sha256 -`

### boringssl
boringssl is not currently versioned hermetically for tcnative builds: instead it just pulls
from the `chromium-stable` branch at build time from the [upstream google git repo](https://boringssl.googlesource.com/boringssl/)

### Building on Linux

Prerequisite | Description
------------ | -----------
Basic tools | autoconf, automake, libtool, glibc-devel, make, tar, [xutils-dev or imake]
APR | apr-devel or libapr1-dev
OpenSSL | openssl-devel or libssl-dev
GCC | Require version >= 4.8
[CMake](https://cmake.org/download/) | Require version >= 2.8.8
Perl | Require version >= 5.6.1
[Ninja](https://ninja-build.org/) | Require version >= 1.3.4
[Go](https://golang.org/dl/) | Require version >= 1.5.1

Build the package and install it into your local Maven repository:

```bash
git clone https://github.com/netty/netty-tcnative.git
cd netty-tcnative
# To build a specific version: (e.g. netty-tcnative-2.0.0.Final)
git checkout netty-tcnative-[version]
# To build a snapshot: (e.g. 2.0.0.Final)
git checkout [branch]
mvn clean install
```

### Building on Mac OS X

First of you need to install [Xcode](https://itunes.apple.com/de/app/xcode/id497799835?mt=12). After this make sure you install the command line tools as well:
```bash
xcode-select --install
```

Use [Homebrew](http://brew.sh/) to install the required packages:

```bash
brew install autoconf automake libtool openssl perl ninja golang cmake apr
```

Build the package and install it into your local Maven repository:

```bash
git clone https://github.com/netty/netty-tcnative.git
cd netty-tcnative
# To build a specific version: (e.g. netty-tcnative-2.0.0.Final)
git checkout netty-tcnative-[version]
# To build a snapshot: (e.g. 2.0.0.Final)
git checkout [branch]
mvn clean install
```

### Building on Windows

This section describes how to build on 64-bit Windows. Some of these steps may have to be tailored when building on 32-bit systems. 

Install the following packages:

* [Visual C++ 2013 Express](https://www.visualstudio.com/en-us/news/vs2013-community-vs.aspx)
* [Windows 10 SDK](https://dev.windows.com/en-us/downloads/windows-10-sdk)
* [CMake](https://cmake.org/files/v3.4/cmake-3.4.3-win32-x86.exe)
  * During installation, select `Add CMake to the system PATH for current user`
* [Perl](http://www.activestate.com/activeperl/downloads/thank-you?dl=http://downloads.activestate.com/ActivePerl/releases/5.22.1.2201/ActivePerl-5.22.1.2201-MSWin32-x64-299574.msi)
* [Ninja](https://github.com/ninja-build/ninja/releases/download/v1.6.0/ninja-win.zip)
  * Extract the executable to `C:\Workspaces\` and add `C:\Workspaces` to `PATH`
* [Go](https://storage.googleapis.com/golang/go1.5.3.windows-amd64.msi)
* [Yasm](http://www.tortall.net/projects/yasm/releases/yasm-1.3.0-win64.exe)
  * Download to `C:\Workspaces`
  * Set the environment variable `ASM_NASM=C:\Workspaces\yasm-1.3.0-win64.exe`.
* [OpenSSL](http://slproweb.com/download/Win64OpenSSL-1_0_2f.exe).
  * During installation, select `Copy OpenSSL DLLs to: The OpenSSL binaries (/bin) directory`
  * Set the following environment variables:
    * `OPENSSL_INCLUDE_DIR=C:\OpenSSL-Win64\include`
    * `OPENSSL_LIB_DIR=C:\OpenSSL-Win64\lib`
* [Apache Portable Runtime (APR) 1.5.2](http://www.us.apache.org/dist/apr/apr-1.5.2-win32-src.zip)
  * Extract into: `C:\Workspaces\apr-1.5.2`
  * Run the following commands in command prompt (`cmd.exe`) from `C:\Workspaces\apr-1.5.2`:
    * `"C:\Program Files (x86)\Microsoft Visual Studio 12.0\VC\vcvarsall.bat" x86_amd64`
    * `nmake /f Makefile.win ARCH="x64 Release" PREFIX=..\apr-1.5.2-dist buildall install`
    * `xcopy include\arch\win32\*.h ..\apr-1.5.2-dist\include /d`
    * `xcopy include\arch\*.h ..\apr-1.5.2-dist /d`
  * Set the following environment variables:
    * `APR_INCLUDE_DIR=C:\Workspaces\apr-1.5.2-dist\include`
    * `APR_LIB_DIR=C:\Workspaces\apr-1.5.2-dist\lib`


Now, start the command prompt (`cmd.exe`) and load the environment variables required for Visual C++:

```bat
"C:\Program Files (x86)\Microsoft Visual Studio 12.0\VC\vcvarsall.bat" x86_amd64
```

Clone `netty-tcnative` and build it:

```bat
git clone https://github.com/netty/netty-tcnative.git
cd netty-tcnative
REM To build a specific version: (e.g. netty-tcnative-2.0.0.Final)
git checkout netty-tcnative-[version]
REM To build a snapshot: (e.g. 2.0.0.Final)
git checkout [branch]
mvn clean install
```

## Creating a new fork

Checking out the branch `bootstrap` and running the `new-fork` script with the upstream tcnative version will bring you to a new fully mavenized branch whose name is identical to the upstream tcnative version.  For example:

```
$ git checkout bootstrap
$ ./new-fork 1.1.29 1
```

will create a new branch called `1.1.29`, which contains a mavenized fork of `tcnative-1.1.29`.  Please note that the fork does not contain any patches that will change the main source code of `tcnative`.  You will probably want to cherry-pick some commits from other branches that were patched already.