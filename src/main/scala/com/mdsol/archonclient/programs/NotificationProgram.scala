package com.mdsol.archonclient.programs

import cats.implicits._
import cats.Monad
import com.mdsol.archonclient.algebras.{NotificationAlg, TopicAlg}
import com.mdsol.archonclient.entities.TopicName

class NotificationProgram[F[_]: Monad](topicAlg: TopicAlg[F], alg: NotificationAlg[F]) {
  import topicAlg._, alg._

  def publishNotification(topicName: TopicName, message: Map[String, String]): F[Unit] =
    for {
      _ <- getTopic(topicName)
      _ <- publish(topicName, message)
    } yield ()
}
