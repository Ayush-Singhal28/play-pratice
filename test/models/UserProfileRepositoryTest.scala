package models

import model.{ModelsTest, UserInfoTable, UserRepository}
import org.specs2.mutable.Specification

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class UserProfileRepositoryTest extends Specification {

  val repo = new ModelsTest[UserRepository]

  private val user = UserInfoTable(1, "ayush@knoldus", "Ayush", "Singhal")

  "User Profile Repository" should {
    "store user profile" in {
      val storeResult = Await.result(repo.repository.store(user), Duration.Inf)
      storeResult must equalTo(true)
    }
  }
}
