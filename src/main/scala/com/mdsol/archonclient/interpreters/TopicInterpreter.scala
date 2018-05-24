package com.mdsol.archonclient.interpreters

import cats.data.EitherT
import com.google.inject.{Inject, Singleton}
import com.mdsol.archonclient.algebras.TopicAlg
import com.mdsol.archonclient.entities.{CreateTopicRequest, Error, FEither, Topic, TopicName}
import io.circe.syntax._
import io.circe.generic.auto._
import play.api.libs.ws.ahc.StandaloneAhcWSClient
import cats.implicits._
import com.mdsol.archonclient.helpers.CirceHelpers

import scala.concurrent.ExecutionContext

@Singleton
class TopicInterpreter @Inject()(wsClient: StandaloneAhcWSClient, implicit val ec: ExecutionContext)(archonBaseUrl: String) extends TopicAlg[FEither] with CirceHelpers {

  override def deleteTopic(topicName: TopicName): FEither[Unit] = EitherT.fromEither(Left("Delete Topic not implemented"))

  override def getTopic(name: TopicName): FEither[Topic] = EitherT(
    wsClient.url(archonBaseUrl + s"/v1/topics/$name").withHttpHeaders("Content-Type" -> "application/json").get()
      .map(_.body[Either[Error, Topic]])
  )

  override def createTopic(name: TopicName, description: String): FEither[Unit] = EitherT(
    wsClient.url(archonBaseUrl + s"/v1/topics").withHttpHeaders("Content-Type" -> "application/json")
      .post(CreateTopicRequest(name, description).asJson).map(_.body[Either[Error, Unit]])
  )
}
