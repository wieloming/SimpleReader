import play.api.libs.json.Json

object Engine extends App {
  val sheet = ExcelDao.read("test1.xlsx")
  sheet match {
    case Right(s) =>
      val nodes = s.asNodes
      val json = Json.toJson(nodes)
      println(json)
    case Left(e) =>
      println(e)
  }
}
