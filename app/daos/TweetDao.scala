package daos

import Entities.TweetEntity
import com.googlecode.mapperdao._
import com.googlecode.mapperdao.utils.{SurrogateIntIdAll, TransactionalSurrogateIntIdCRUD}
import model.Tweet

abstract class TweetDao extends TransactionalSurrogateIntIdCRUD[Tweet] with SurrogateIntIdAll[Tweet] {

  import Query._

  val queryDao: QueryDao
  private val tweetEntity = TweetEntity

  def findPagenation(page: Int): List[Tweet] = queryDao.query(QueryConfig.pagination(page, 10), select from tweetEntity order by(tweetEntity.tweetedAt, desc))

  def insert(tweet: Tweet): Unit = mapperDao.insert(tweetEntity, tweet)

}
