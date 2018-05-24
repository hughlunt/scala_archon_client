package com.mdsol.archonclient

import java.time.ZonedDateTime
import java.util.UUID

import cats.data.EitherT

import io.circe.generic.extras._
import io.circe.generic.extras.semiauto._
import io.circe.java8.time._
import io.circe.syntax._
import io.circe.{Decoder, Encoder}

import scala.concurrent.Future

package object entities {

  type Error = String
  type FEither[A] = EitherT[Future, Error, A]

  sealed trait SubscriptionType
  case object HTTPS extends SubscriptionType
  case object SQS extends SubscriptionType
  implicit val subTypeEncoder: Encoder[SubscriptionType] = deriveEnumerationEncoder[SubscriptionType]
  implicit val subTypeDecoder : Decoder[SubscriptionType] = deriveEnumerationDecoder[SubscriptionType]

  implicit val config: Configuration = Configuration.default.withSnakeCaseMemberNames

  case class TopicName(name: String) extends AnyVal
  implicit val topicNameEncoder: Encoder[TopicName] = deriveUnwrappedEncoder
  implicit val topicNameDecoder: Decoder[TopicName] = deriveUnwrappedDecoder

  case class errorResponse(errors: Map[String, List[String]])

  @ConfiguredJsonCodec
  case class SqsEndpointConfig(messageRetentionPeriod: Int, deadLetterQueue: Boolean, maximumReceives: Int)

  @ConfiguredJsonCodec
  case class SqsEndpointConfigResponse(messageRetentionPeriod: Int, deadLetterQueue: String, maximumReceives: Int)

  @ConfiguredJsonCodec
  case class Topic(uuid: UUID,
                   name: TopicName,
                   description: String,
                   resource: Option[String],
                   resourceUri: Option[String],
                   notificationsEndpoint: String,
                   subscriptionsEndpoint: String,
                   embedResource: Option[Boolean],
                   appUuid: UUID,
                   createdAt: ZonedDateTime,
                   updatedAt: ZonedDateTime)

  @ConfiguredJsonCodec
  case class CreateTopicRequest(name: TopicName,
                                description: String,
                                resource: String = "unused field (resource)",
                                resourceUri: String = "unused field (resource_uri)",
                                embedResource: Boolean = false)


  @ConfiguredJsonCodec
  case class NotificationRequest(additionalFields: Map[String, String])

  @ConfiguredJsonCodec
  case class PublishNotificationResponse(uuid: UUID,
                                         topicEndpoint: String,
                                         resource: String,
                                         resourceUri: String,
                                         embedResource: Boolean,
                                         additionalFields: Map[String, String])

  @ConfiguredJsonCodec
  case class HttpsSubscriptionRequest(name: String,
                                      messageEndpoint: String,
                                      subscriptionType: SubscriptionType = HTTPS)

  @ConfiguredJsonCodec
  case class SqsSubscriptionRequest(name: String,
                                    messageEndpointConfiguration: Option[SqsEndpointConfig],
                                    subscriptionType: SubscriptionType = SQS)

  @ConfiguredJsonCodec
  case class Subscription(uuid: UUID,
                          name: String,
                          subscriptionType: SubscriptionType,
                          appUuid: UUID,
                          messageEndpoint: String,
                          topic: String,
                          topicUuid: UUID,
                          createdAt: ZonedDateTime,
                          updatedAt: ZonedDateTime,
                          messageEndpointConfiguration: Option[SqsEndpointConfigResponse])
}
