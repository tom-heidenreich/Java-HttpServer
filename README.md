# Java-HttpServer

## Create Server
```JAVA
HttpServer server = new HttpServer(80);
```
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
This is the default executor.
```JAVA
server.setExecutor(Executors.newCachedThreadPool());
```

***

## Set Handler
* ### Handle GET requests
```JAVA
server.get("/home", (req, res) -> {

});
```
* ### Handle POST requests
```JAVA
server.post("/home", (req, res) -> {

});
```
* ### Handle any method
```JAVA
server.handle("*", "/home", (req, res) -> {

});
```
* ### Alternative
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

### Cookies
```JAVA
Cookie[] cookies = req.getCookies();
```
```JAVA
Cookie cookie = req.getCookie("name");
```

### Params
```JAVA
String param = req.getParam("name");
```

### Body
```JAVA
String body = req.getRequestBody();
```
```JAVA
byte[] body = req.getRequestBodyBytes();
```

***

## Response
### Head
```JAVA
res.status(200)
```
```JAVA
res.writeHead(200, (head) -> {
    head.setHeader("Content-Type", "text/plain");
});
```
### Body
```JAVA
res.send("Hello World!");
```
Or with status code
```JAVA
res.send(200, "Hello World!");
```
You can also use write. This will not end the response and you can send bytes
```JAVA
res.write("Hello World!");
```
### End
Using send will already end the response.
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
