package controllers

import javax.inject._

import play.api.data._
import play.api.data.Forms._
import play.api.i18n.I18nSupport
import play.api.mvc._



case class User(name: String, email: String)

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc)  with I18nSupport {

  implicit val message = cc.messagesApi
  val formUser = Form(mapping(
      "name" -> text,
      "email" -> text
    )(User.apply)(User.unapply))



  /**
    * Create an Action to render an HTML page.
    *
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
//    */
//  def index() = Action { implicit request: Request[AnyContent] =>
//    Ok(views.html.index())
//  }


 /* def printDetails() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.user(formUser))
  }

  def validate() = Action { implicit request: Request[AnyContent] =>

    println("here>>>>>>")
    formUser.bindFromRequest.fold(
      formWithErrors => {
        BadRequest(views.html.user(formWithErrors))
      },
       userData => {
        println("here>>>>11111>>")

        Ok(views.html.output(userData)).withSession(
          "email" -> userData.email)
          .flashing("Success" -> "session created successfully")

      })
  }*/
}
