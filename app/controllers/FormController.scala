package controllers

import javax.inject.Inject

import model.{UserInfoTable, UserRepository}
import models.UserInfoRepo
import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents, Request}
//import play.mvc.Action
import users.UserForm
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class FormController @Inject()(userRepository: UserRepository,userForm: UserForm, userInfoRepo: UserInfoRepo, cc: ControllerComponents) extends AbstractController(cc) with I18nSupport {
  implicit val message = cc.messagesApi

  def index() = Action.async { implicit request: Request[AnyContent] =>
    Future.successful(Ok(views.html.index(userForm.userInfoForm)))
  }

  /*def storeData: Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    userForm.userInfoForm.bindFromRequest().fold(
      formWithError => {
        Future.successful(BadRequest(views.html.index(formWithError)))
      },
      data => {
        userInfoRepo.getUser(data.email).flatMap {
          optionalRecord =>
            optionalRecord.fold {
              val record = userInfoRepo.UserInfo(data.fname, data.lname, data.email)
              userInfoRepo.store(record).map { _ =>
                Ok("stored")
              }

            } { record => Future.successful(InternalServerError("user already exists")) }
        }
      }
    )
  }*/

  def getData(email: String): Action[AnyContent] = Action.async {
    userInfoRepo.getUser(email).map { optionalRecord =>
      optionalRecord.fold {
        NotFound("Oops! user not found")
      } {
        record => Ok(s"Users fullname is ${record.fname} ${record.lname}")
      }
    }
  }

  /*val userdetails=UserInfoTable(1,"ayush","singhal","ayush.singhal0728@gmail.com")
  userRepository.store(userdetails).map{
    case true => Redirect(routes.FormController.form).withSession("email" -> userdetails.email)
      .flashing("success" -> "user created")
    case false => Ok(views.html.index).flashing("failure" ->"user not created")
  }
  userRepository.findByEmail("ayush.singhal0728@gmail.com")

  def form = Action.async { implicit request: Request[AnyContent] =>
    Ok(views.html.form())
  }*/

  def storeInDb = Action.async { implicit request =>
    userForm.userInfoForm.bindFromRequest().fold(
      formWithError => {
        Future.successful(BadRequest(views.html.index(formWithError)))
      },
      data => {
        userRepository.store(UserInfoTable(0, data.email, data.fname, data.lname)).map {
          case true => Ok("stored")
          case false => Ok("not stored")
        }
      })
  }
}
