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

## Run the server in each package, to after run the client

### Examples in Project:

### gRPC unary call
#### Client side:
    br.com.amsj.grpc.greeting.client.SumClient 
#### Server side:
    br.com.amsj.grpc.greeting.server.SumServer

### gRPC streaming server
#### Client side:
    br.com.amsj.grpc.greeting.client.NumberDecompositionClient 
#### Server side:
    br.com.amsj.grpc.greeting.server.NumberDecompositionServer

### gRPC streaming client
#### Client side:
    br.com.amsj.grpc.greeting.client.CalculatorClient
#### Server side:
    br.com.amsj.grpc.greeting.server.CalculatorServer


### gRPC bidirecional streaming ( client and server )
#### Client side:
    br.com.amsj.grpc.greeting.client.FindMaximumClient
#### Server side:
    br.com.amsj.grpc.greeting.server.FindMaximumServer

