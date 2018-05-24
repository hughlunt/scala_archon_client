package com.mdsol.archonclient.algebras

import com.mdsol.archonclient.entities.{Subscription, SubscriptionType, TopicName}

trait SubscriptionAlg[F[_]] {
  def subscribe(topic: TopicName, subscriptionType: SubscriptionType): F[Subscription]
  def unsubscribe(topic: TopicName): F[Unit]
}
