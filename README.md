# gRPC Java boilerplate application ( build in gradle )

## To Generate Proto
```bash
./gradlew generateProto
```

#Structure:

## /src/main/proto
Proto definitions

## /src/main/java
Java implementation for gRPC based on gRPC generated code

### Examples:

###Client side for gRPC unary request:
    br.com.amsj.grpc.greeting.client.GreetingClient 

### Server side for gRPC unary response:
    br.com.amsj.grpc.greeting.server.GreetingServer

### Service Implementation:
    br.com.amsj.grpc.greeting.server.GreetServiceImpl