package com.mdsol.archonclient.helpers

import com.mdsol.archonclient.entities.Error
import io.circe.Decoder
import io.circe.parser.decode
import play.api.libs.ws.BodyReadable

trait CirceHelpers {

  implicit def circeBodyReadable[A: Decoder] = BodyReadable[Either[Error, A]] { response =>
    decode[A](response.body).fold(e => Left(e.getMessage), Right(_))
  }

}
