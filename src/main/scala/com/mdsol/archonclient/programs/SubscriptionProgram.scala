package com.mdsol.archonclient.programs

import cats.implicits._
import cats.Monad
import com.mdsol.archonclient.algebras.{SubscriptionAlg, TopicAlg}
import com.mdsol.archonclient.entities.{Subscription, SubscriptionType, TopicName}

class SubscriptionProgram[F[_]: Monad](alg: SubscriptionAlg[F], topicAlg: TopicAlg[F]) {
  import alg._, topicAlg._

  def subscribeToTopic(topic: TopicName, subscriptionType: SubscriptionType): F[Subscription] =
    for {
      _ <- getTopic(topic)
      subscription <- subscribe(topic, subscriptionType)
    } yield subscription
}
