//package models
//
//import akka.Done
//import org.specs2.mutable.Specification
//import play.api.Application
//import play.api.inject.guice.GuiceApplicationBuilder
//
//import scala.concurrent.Await
//import scala.concurrent.duration.Duration
//import scala.reflect.ClassTag
//
//class ModelsTest[T: ClassTag] {
//  def fakeApp: Application = {
//    new GuiceApplicationBuilder()
//      .build
//  }
//
//  lazy val app2daa = Application.instanceCache[T]
//  lazy val repository: T = app2daa(fakeApp)
//}
//
//class UserInfoRepoTest extends Specification {
//
//  val repo = new ModelsTest[UserInfoRepo]
//
//  "user info repository" should {
//
//    "store associate detail of a user" in {
//      val user = repo.repository.UserInfo("ayush", "singhal", "ayush@knoldus")
//      val storeResult = Await.result(repo.repository.store(user), Duration.Inf)
//      storeResult must equalTo(Done)
//    }
//
//
//  }
//}
