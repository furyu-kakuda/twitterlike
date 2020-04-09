package services

import com.google.inject.Singleton
import daos.Daos._
import model.Tweet

@Singleton
class TwitterService {

  def findWithPagenation(page: Int) = tweetDao.findPagenation(page)

  def tweet(tweet: Tweet) = tweetDao.insert(tweet)

  def delete(id: Int) = tweetDao.delete(id)
}
