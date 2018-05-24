## Archon scala client

*All my REST calls Archon*

### Overview
The Archon Scala client is a library to interact with the Archon pubsub service.  

### Quick start
Add the following to your projects build.sbt

```scala
libraryDependencies += "com.mdsol" %% "scala_archon_client" % ArchonClientVesion
```

In your code:
```scala
import com.mdsol.archon_client.{ArchonClient, ArchonResponse}

def registerTopic(topicName: String, description: String): ArchonResponse {
  ArchonClient.register(topic_name, description)
}

def publish(someMessage: Json, topicName: TopicName): ArchonResponse {
  ArchonClient.publish(topicName, someMessage)
}

def subscribe(topicName: TopicName): ArchonResponse {
  ArchonClient.subscribe(topicName, notificationEndpoint: URL)
}

```
### Detailed Usage
To follow

### Caveats
Need to replace String for resource uris with a Uri type (i.e. MdsolUri, but a scala equivalent)

### Contributing
To follow
