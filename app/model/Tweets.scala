package model

import play.api.libs.json.Json

case class Tweets(tweets: List[Tweet])

object Tweets {
  implicit val jsonWrites = Json.writes[Tweets]
  implicit val jsonReads = Json.reads[Tweets]
}
