package com.mdsol.archonclient

import com.google.inject.Singleton

import scala.concurrent.Future

@Singleton
case class ArchonClient(archonBaseUrl: String) {

  def register(topicName: String, description: String): Future[Either[Error, Unit]] = ???

  def publish(data: Map[String, String], topicName: String): Future[Either[Error, Unit]] = ???

  def subscribe(topicName: String): Future[Either[Error, Unit]] = ???
}
