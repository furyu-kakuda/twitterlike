package controllers

import com.google.inject.{Inject, Singleton}
import model.{Tweet, Tweets}
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.TwitterService

@Singleton
class TweetController @Inject()(twitterService: TwitterService) extends Controller {

  def getTweets(page: Int) = Action {
    val tweets = Tweets(twitterService.findWithPagenation(page))
    Ok(Json.toJson(tweets))
  }

  def postTweet = Action(parse.json[Tweet]) { request =>
    twitterService.tweet(request.body)
    Ok
  }

  def deleteTweet(id: Int) = Action {
    twitterService.delete(id)
    Ok
  }

}
