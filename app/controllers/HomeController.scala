package controllers

//import com.github.tototoshi.play2._
import com.github.tototoshi.play2.scalate.Scalate
import com.google.inject.{Inject, Singleton}
import play.api.mvc._

@Singleton
class HomeController extends Controller {

  def index = Action { implicit request: Request[AnyContent] =>
    Ok(Scalate.render("hoge.ssp"))
  }

}
