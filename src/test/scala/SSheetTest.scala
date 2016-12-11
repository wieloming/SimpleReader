import models.dto.Node
import org.scalatest._


class SSheetTest extends FlatSpec with Matchers {

  "A SSheet" should "parse data to Nodes" in {
    val dao = ExcelDao
    val data = dao.read("src\\test\\test.xlsx")
    val sheet = data.right.get
    val nodes = sheet.asNodes
    nodes should be (List(Node(1,"A",List(Node(2,"AA",List(Node(3,"AA1",List()), Node(4,"AA2",List()))), Node(5,"AB",List()))), Node(6,"B",List()), Node(7,"C",List(Node(8,"CA",List(Node(9,"CA1",List()), Node(10,"CA2",List()))))), Node(11,"D",List(Node(12,"DA",List())))))
  }

}