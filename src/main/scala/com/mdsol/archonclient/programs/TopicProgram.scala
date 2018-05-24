package com.mdsol.archonclient.programs

import cats.implicits._
import cats.Monad
import com.mdsol.archonclient.algebras.TopicAlg
import com.mdsol.archonclient.entities.TopicName

class TopicProgram[F[_]: Monad](alg: TopicAlg[F]) {
  import alg._

  def delete(topic: TopicName): F[Unit] = deleteTopic(topic)

  def createTopic(topicName: TopicName, description: String): F[Unit] =
    createTopic(topicName, description)
}
