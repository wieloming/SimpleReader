
import org.apache.poi.ss.usermodel.{Cell, WorkbookFactory, DataFormatter}
import java.io.File
import scala.collection.JavaConverters._

object Engine extends App {
  def read(filename: String) : List[SCell] = {
    val workbook = WorkbookFactory.create(new File(filename))
    val activeSheet = workbook.getSheetAt(0)
    activeSheet.removeRow(activeSheet.getRow(0))

    val cells = for {
      row <- activeSheet.asScala.toList
      cell <- row.asScala.toList
    } yield SCell.fromCell(cell)
    cells.filter(_.value.nonEmpty)
  }

  val cells = read("test1.xlsx")
  val idsRow = cells.filter(_.row == 3)
}
