package daos

import Entities.TweetEntity
import com.googlecode.mapperdao.utils.Setup
import play.api.db.DB
import play.api.Play.current

object Daos {

  val datasource = DB.getDataSource("default")
  val (jdbc, mapperDao, queryDao, txManager) = Setup.mysql(datasource, List(TweetEntity))

  val tweetDao = new TweetDao {
    val (entity, queryDao, txManager, mapperDao) = (TweetEntity, Daos.queryDao, Daos.txManager, Daos.mapperDao)
  }

}
