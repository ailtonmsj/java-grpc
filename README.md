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