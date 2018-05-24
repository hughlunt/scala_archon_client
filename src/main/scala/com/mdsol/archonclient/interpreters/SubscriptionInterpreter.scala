package com.mdsol.archonclient.interpreters

import cats.data.EitherT
import cats.implicits._
import com.google.inject.{Inject, Singleton}
import com.mdsol.archonclient.algebras.SubscriptionAlg
import com.mdsol.archonclient.entities.{Error, FEither, HTTPS, HttpsSubscriptionRequest, SQS, SqsSubscriptionRequest, Subscription, SubscriptionType, TopicName}
import com.mdsol.archonclient.helpers.CirceHelpers
import io.circe.syntax._
import io.circe.generic.auto._
import play.api.libs.ws.ahc.StandaloneAhcWSClient

import scala.concurrent.ExecutionContext

@Singleton
class SubscriptionInterpreter @Inject()(wsClient: StandaloneAhcWSClient, implicit val ec: ExecutionContext)(archonBaseUrl: String) extends SubscriptionAlg[FEither] with CirceHelpers {

  override def subscribe(topic: TopicName, subscriptionType: SubscriptionType): FEither[Subscription] = EitherT(
    wsClient.url(archonBaseUrl + s"/v1/topics/$topic/subscriptions").withHttpHeaders("Content-Type" -> "application/json")
      .post(subscriptionType match {
        case SQS   => SqsSubscriptionRequest(topic.name, None).asJson
        case HTTPS => HttpsSubscriptionRequest(topic.name, "Some message endpoint (Not sure what this is used for)").asJson
      })
      .map(_.body[Either[Error, Subscription]])
  )

  override def unsubscribe(topic: TopicName): FEither[Unit] = EitherT.fromEither(Left("Unsubscribe not implemented"))
}
