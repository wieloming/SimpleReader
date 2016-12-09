
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.File

import play.api.libs.json.Json

import scala.collection.JavaConverters._


object Engine extends App {
  def read(filename: String) : SSheet = {
    val workbook = WorkbookFactory.create(new File(filename))
    val activeSheet = workbook.getSheetAt(0)
    activeSheet.removeRow(activeSheet.getRow(0))

    val cells = for {
      row <- activeSheet.asScala.toList
      cell <- row.asScala.toList
    } yield SCell.fromCell(cell)
    SSheet(cells.filter(_.value.nonEmpty))
  }

  val ssheet = read("test1.xlsx")
  val parents = ssheet.parents
  val nodes = parents.flatMap(ssheet.toNode)
  val json = Json.toJson(nodes)
  println(json)
}
