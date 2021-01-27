# Tehnic1

Working for the first time with Spring and JABX so i would be glad  to get additional recomandations from you .
<br>
JAVA 1.8 
<br>
SPRING FRAMEWORK
<br>
RABITMQ SERVER
<br>
JABX
<br>
No additional library were used, I would need a gson parser, but I managed to do with the split function .
<br>


Description :
<br>
The middleware is part of 3 components: API WEBServer, RabitMQ server and a simple console application.
<br>
API WebServer:
<br>
Spring server expose an api  over a controller "com.middleware.webapi.fileUpload.controller.FilesController" accepting post requests on http://localhost:8080/upload ,
type : accepting multipart/form-data. Inside controller are injected 2 services :FilesStorageService and RabbitMQSender .The first one reesponsible for storing
the input files and the second is responsible for sending a message(with name of inputfile) to the rabitmq queue. And some exception may be raised if input file is invalid. Th
<br>
RabbitMQ: I chose it instead of createing my own queue of blocking task. It is configured in the simplest form, acting as a performant queue, no emphasize on any
scheduler, response or all it's FEATURES. 
<br>
Console Application: Namec com.middleware.Listener.SimpleListener.java i thought if keeping simple will do it job. So the connection to RabbitMQ is made with different libs (instead the ones offered by spring framewor) . Here things are simple, waiting for a message on queue and then process the input file according to the requirements.
<br>
Drawbacks : I did not implement the post to the vendors servers because still have to do some works and rather there should be made some refactorings and some recomandations from you.

Tested with postman. 
