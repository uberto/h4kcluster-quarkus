# Http4k Cluster on Quarkus


first check that everything is ok with
```
./gradlew clean build
```

then you can start dev mode with

```
./gradlew launcher:quarkusDev
```

You can check that the launcher is running at

```
http://localhost:8080/
```

Then you can see the ui microservice at

```
http://ui.localhost:8080/
```

## Creating a native executable

If you don't have GraalVM installed and GRAALVM_HOME configured, you can create a native executable using: `./gradlew build -Dquarkus.package.type=native`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./build/xxxx-runner`

Note that you need to add Quarkus as dependency of any project that you want to be

## debug startup time

to produce a flamegraph of the start up times in dev mode you can start it with:
```
./gradlew build launcher:quarkusDev -Djvm.args="-agentpath:/path/to/async-profiler/build/libasyncProfiler.so=start,event=cpu,file=/tmp/startup-cpu-profile.svg,interval=1000000,width=1600,simple"
```

See
https://github.com/quarkusio/quarkus/blob/master/TROUBLESHOOTING.md#profiling-application-dev-mode-with-async-profiler
and
https://github.com/jvm-profiling-tools/async-profiler
