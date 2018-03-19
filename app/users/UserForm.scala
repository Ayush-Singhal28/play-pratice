package users

import play.api.data.Form
import play.api.data.Forms.{email, mapping, text}

case class UserInformation(fname: String, lname: String, email: String)
class UserForm {
  val userInfoForm = Form(mapping(
    "fname" -> text.verifying("",_.nonEmpty),
    "lname" -> text.verifying("",_.nonEmpty),
    "email" -> email
  )(UserInformation.apply)(UserInformation.unapply))

}
