package com.mdsol.archonclient.algebras

import com.mdsol.archonclient.entities.TopicName

trait NotificationAlg[F[_]] {

  def publish(topic: TopicName, message: Map[String, String]): F[Unit]
}
