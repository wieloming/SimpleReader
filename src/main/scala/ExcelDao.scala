import java.io.{File, FileNotFoundException}

import models.excel.{SCell, SSheet}
import org.apache.poi.ss.usermodel.{Sheet, WorkbookFactory}

import scala.collection.JavaConverters._
import scala.language.postfixOps

object ExcelDao {
  private val ActiveSheetIndex = 0
  private val HeadRowIndex = 0

  def read(filename: String): Either[String, SSheet] = {
    try {
      val activeSheet = getActiveSheet(filename)
      activeSheet.removeRow(activeSheet.getRow(HeadRowIndex))
      val cells = parseCells(activeSheet)
      Right(SSheet(cells))
    } catch {
      case e: FileNotFoundException => Left("file not present: src\\test\\test2.xlsx")
      case e: Throwable => Left(e.getMessage)
    }
  }

  private def parseCells(activeSheet: Sheet): List[SCell] = {
    for {
      row <- activeSheet.asScala.toList
      cell <- row.asScala.toList
      parsed = SCell.parseCell(cell)
      if parsed.value nonEmpty
    } yield parsed
  }

  private def getActiveSheet(fileName: String): Sheet = {
    val file = new File(fileName)
    val workbook = WorkbookFactory.create(file)
    val activeSheet = workbook.getSheetAt(ActiveSheetIndex)
    activeSheet
  }
}