package controllers

import javax.inject._
import play.api._
import play.api.libs.json._
import play.api.mvc._
import library.{RPN, Base64, ShuntingYard}

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject() extends Controller {

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action {
    Ok(views.html.index("App is Ready !!"))
  }

  def calcResult(query: String) = Action {

    try {
        val InFix = Base64.decodeString(query)
        val PostFix = ShuntingYard.shunt(InFix)
        val RpnValue = RPN.calculate(PostFix)

        Ok(JsObject(Seq(
          "error" -> JsBoolean(false),
          "result" -> JsNumber(RpnValue)
        )))
    } catch {
        case e @ (_ : RuntimeException | _ : java.io.IOException) => BadRequest(JsObject(Seq(
          "error" -> JsBoolean(true),
          "message" -> JsString("Error: " + e)
        )))
    }

  }

}
