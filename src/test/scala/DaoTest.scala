import models.excel.{SCell, SSheet}
import org.scalatest._
import scala.util.Right

class DaoTest extends FlatSpec with Matchers {

  "A Dao" should "import XLSX and return Json" in {
    val dao = ExcelDao
    val data = dao.read("src\\test\\test.xlsx")

    data should be (Right(SSheet(
      List(SCell(1, 0, "A"), SCell(1, 3, "1"), SCell(2, 1, "AA"), SCell(2, 3, "2"), SCell(3, 2, "AA1"),
        SCell(3, 3, "3"), SCell(4, 2, "AA2"), SCell(4, 3, "4"), SCell(5, 1, "AB"), SCell(5, 3, "5"), SCell(6, 0, "B"),
        SCell(6, 3, "6"), SCell(7, 0, "C"), SCell(7, 3, "7"), SCell(8, 1, "CA"), SCell(8, 3, "8"), SCell(9, 2, "CA1"),
        SCell(9, 3, "9"), SCell(10, 2, "CA2"), SCell(10, 3, "10"), SCell(11, 0, "D"), SCell(11, 3, "11"),
        SCell(12, 1, "DA"), SCell(12, 3, "12")))))
  }

  "A Dao" should "import return message when file not available" in {
    val dao = ExcelDao
    val data = dao.read("src\\test\\test2.xlsx")
    data should be (Left("file not present: src\\test\\test2.xlsx"))
  }

}