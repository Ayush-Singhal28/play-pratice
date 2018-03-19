package model


import javax.inject.Inject


import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile
import slick.lifted.{ProvenShape}
import slick.lifted.ProvenShape.proveShapeOf

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

case class UserInfoTable(id: Int,
                         email: String,
                         fname: String,
                         lname: String
               )

trait userRepositoryTable extends HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  class UserTable(tag: Tag) extends Table[UserInfoTable](tag, "UserInfoTable") {


    def id: Rep[Int] = column[Int]("id", O.PrimaryKey, O.AutoInc)

    def fname: Rep[String] = column[String]("fname")
    def lname: Rep[String] = column[String]("lname")

    def email: Rep[String] = column[String]("email")
    def * : ProvenShape[UserInfoTable] = (id,email, fname,lname) <> (UserInfoTable.tupled, UserInfoTable.unapply)
  }

  val userQuery: TableQuery[UserTable] = TableQuery[UserTable]

}

trait UserRepositoryTrait {
  def store(user: UserInfoTable): Future[Boolean]

  def findByEmail(email: String): Future[Option[UserInfoTable]]
}

trait UserRepositoryImpl extends UserRepositoryTrait {
  self: userRepositoryTable =>
  import profile.api._

  def store(user: UserInfoTable): Future[Boolean] = {
    db.run(userQuery += user) map (_ > 0)
  }

  def findByEmail(email: String): Future[Option[UserInfoTable]] = {
    val queryResult = userQuery.filter(_.email.toLowerCase === email.toLowerCase).result.headOption
    val result = db.run(queryResult)
    result
  }

}

class UserRepository @Inject() (protected val dbConfigProvider: DatabaseConfigProvider) extends UserRepositoryTrait with userRepositoryTable with UserRepositoryImpl