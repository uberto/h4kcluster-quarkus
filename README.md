# Http4k Cluster on Quarkus



start dev mode with

```
./mvnw -f launcher quarkus:dev
```

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/xxxx-runner`

## debug startup time

to produce a flamegraph of the start up times in dev mode you can start it with:
```
./mvnw compile quarkus:dev -Djvm.args="-agentpath:/path/to/async-profiler/build/libasyncProfiler.so=start,event=cpu,file=/tmp/startup-cpu-profile.svg,interval=1000000,width=1600,simple"
```

See
https://github.com/quarkusio/quarkus/blob/master/TROUBLESHOOTING.md#profiling-application-dev-mode-with-async-profiler
and
https://github.com/jvm-profiling-tools/async-profiler
