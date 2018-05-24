package com.mdsol.archonclient.algebras

import com.mdsol.archonclient.entities.{Topic, TopicName}

trait TopicAlg[F[_]] {

  def getTopic(name: TopicName): F[Topic]

  def createTopic(name: TopicName, description: String): F[Unit]

  def deleteTopic(topic: TopicName): F[Unit]

}
