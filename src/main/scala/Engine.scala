
import org.apache.poi.ss.usermodel.{WorkbookFactory, DataFormatter}
import java.io.File
import scala.collection.JavaConverters._

object Engine extends App {
  def read(filename: String)= {

    val workbook = WorkbookFactory.create(new File(filename))
    val formatter = new DataFormatter()
    for {
      sheet <- workbook.asScala.toList
      row <- sheet.asScala.toList
      cell <- row.asScala.toList
    } yield println(formatter.formatCellValue(cell))
  }

  read("test1.xlsx")
}
