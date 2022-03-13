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
    br.com.amsj.grpc.sum.client.SumClient 
#### Server side:
    br.com.amsj.grpc.sum.server.SumServer


### gRPC server streaming
#### Client side:
    br.com.amsj.grpc.numberdecomposition.client.NumberDecompositionClient 
#### Server side:
    br.com.amsj.grpc.numberdecomposition.server.NumberDecompositionServer


### gRPC client streaming
#### Client side:
    br.com.amsj.grpc.calculator.client.AverageServerStreamClient
#### Server side:
    br.com.amsj.grpc.calculator.server.CalculatorServer


### gRPC bidirecional streaming ( client and server )
#### Client side:
    br.com.amsj.grpc.findmaximum.client.FindMaximumClient
#### Server side:
    br.com.amsj.grpc.findmaximum.server.FindMaximumServer


### gRPC unary call with error handling
#### Client side:
    br.com.amsj.grpc.calculator.client.SquareRootUnaryClient
#### Server side:
    br.com.amsj.grpc.calculator.server.CalculatorServer


### gRPC Deadline example
#### Client side:
    br.com.amsj.grpc.sum.client.SumLongProcessClient
#### Server side:
    br.com.amsj.grpc.sum.server.SumServer

### gRPC SSL example ( Using Custom CA )
#### Reference: https://grpc.io/docs/guides/auth/#with-server-authentication-ssltls-5
#### Client side:
    br.com.amsj.grpc.greeting.client.GreetingManyTimesClient
    br.com.amsj.grpc.greeting.client.GreetingClient
#### Server side:
    br.com.amsj.grpc.greeting.server.GreetingServer

### gRPC Reflections 
#### Reference: https://github.com/grpc/grpc-java/blob/master/documentation/server-reflection-tutorial.md

#### configuration
    build.gradle

#### Server side:
    br.com.amsj.grpc.greeting.server.GreetingServer (TLS)
    br.com.amsj.grpc.sum.server.SumServer (no TLS)

# Test With evans (Install evans is needed)

#### 1. Install evans
#### https://github.com/ktr0731/evans#installation
#### 2. Start the server
#### 3.1 Run (No TLS):
    evans --host <LOCALHOST> -p <PORT> -r
    evans --host localhost -p 50061 -r
#### 3.2 Run (TLS):
    not supported by evans at the moment
#### 4. Discovery the services
    show services
#### 5. Call some service
    call <SERVICE-NAME>
    call Sum


