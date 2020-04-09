package model


import java.util.Date

import play.api.libs.json.{Json, Reads, Writes}

case class Tweet(id :Int, contents: String, tweetedAt: Date)

object Tweet {

  implicit val jsonWrites: Writes[Tweet] = Json.writes[Tweet]
  implicit val jsonReads: Reads[Tweet] = Json.reads[Tweet]

}
