# Java-HttpServer

Create new Server

```JAVA
HttpServer server = new HttpServer(new InetSocketAddress(80), 0);
```


Handlers

Set DefaultHandler

```JAVA
server.setDefaultHandler((req, res) -> {
  
});
```

Add Handler

```JAVA
server.addHandler("/home", (req, res) -> {
  
});
```

Start

```JAVA
server.start();
```

```JAVA
server.start(()->{
  System.out.println("Server started!");
});
