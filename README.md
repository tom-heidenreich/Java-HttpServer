# Java-HttpServer

# Java-HttpServer Wiki!

## Create Server

```JAVA
HttpServer server = new HttpServer(new InetSocketAddress(80), 0);
```

***

## Set Executor
* ### Single Thread
```JAVA
server.setExecutor(Executors.newSingleThreadExecutor());
```

* ### Fixed Threads
```JAVA
server.setExecutor(Executors.newFixedThreadPool(10));
```
* ### Multi Threads
```JAVA
server.setExecutor(Executors.newCachedThreadPool());
```

***

## Set Handler
* ### DefaultHandler
```JAVA
server.setDefaultHandler((req, res) -> {

});
```
* ### Handler
```JAVA
server.addHandler("/home", (req, res) -> {

});
```

***

## Request
### Headers
```JAVA
req.getRequestHeader("Connection");
```
### URI
```JAVA
req.getRequestURI();
```
### Method
```JAVA
req.getRequestMethod();
```
### Remote Address
```JAVA
req.getRemoteAddress();
```

***

## Response
### Head
```JAVA
res.writeHead(200, (head) -> {
    head.setHeader("Content-Type", "text/plain");
});
```
### Body
```JAVA
res.write("Hello World!");
```
### End
```JAVA
res.end();
```

***

## Start
```JAVA 
server.start(); 
```
Or
```JAVA
server.start(()->{
    System.out.println("Server started!");
});
```
