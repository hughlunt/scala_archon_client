package com.mdsol.archonclient.interpreters

import cats.data.EitherT
// import cats.implicits._
import com.google.inject.{Inject, Singleton}
import com.mdsol.archonclient.algebras.NotificationAlg
import com.mdsol.archonclient.entities
import com.mdsol.archonclient.entities.{Error, FEither, NotificationRequest}
import com.mdsol.archonclient.helpers.CirceHelpers
import io.circe.syntax._
import io.circe.generic.auto._
import play.api.libs.ws.ahc.StandaloneAhcWSClient

import scala.concurrent.ExecutionContext

@Singleton
class NotificationInterpreter @Inject()(wsClient: StandaloneAhcWSClient, implicit val ec: ExecutionContext)(archonBaseUrl: String) extends NotificationAlg[FEither] with CirceHelpers {
  override def publish(topic: entities.TopicName, message: Map[String, String]): FEither[Unit] = EitherT(
    wsClient.url(archonBaseUrl + s"/v1/topics/$topic/notifications").withHttpHeaders("Content-Type" -> "application/json")
      .post(NotificationRequest(message).asJson.toString)
      .map(_.body[Either[Error, Unit]])
  )
}
