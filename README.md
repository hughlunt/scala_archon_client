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

def registerTopic(topic_name: String, description: String): ArchonResponse {
  ArchonClient.register(topic_name, description)
}

def publish(someMessage: Json, topicName: TopicName): ArchonResponse {
  ArchonClient.publish(topicName, someMessage)
}

def subscribe(topic_name: TopicName): ArchonResponse {
  ArchonClient.subscribe(topicName, notificationEndpoint: URL)
}

```
### Detailed Usage
To follow

### Caveats
No code has yet been written, this is a loose sketch

### Contributing
To follow
