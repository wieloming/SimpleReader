import play.api.libs.json.Json

case class Node(id: Int, name: String, nodes: List[Node])

object Node {
  implicit def nodeFormat = Json.format[Node]
}
