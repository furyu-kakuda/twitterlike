package controllers

import play.api.libs.json.Json.toJson
import play.api.libs.json.{Format, JsResult, JsSuccess, JsValue}
import java.util.Date
import java.text.SimpleDateFormat

object DateFormatter {

  implicit object JsonDateFormatter extends Format[Option[Date]] {

    val dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'")

    def writes(date: Option[Date]): JsValue = {
      toJson(
        date.map(
          date => dateFormat.format(date)
        ).getOrElse(
          ""
        )
      )
    }

    def reads(j: JsValue): JsResult[Option[Date]] = {
      try {
        JsSuccess(Some(dateFormat.parse(j.as[String])))
      } catch {
        case e: Exception => JsSuccess(None)
      }
    }

  }

}
