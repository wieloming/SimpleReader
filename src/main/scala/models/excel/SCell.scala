package models.excel

import org.apache.poi.ss.usermodel.{Cell, DataFormatter}
import scala.util.Try

case class SCell(row: Int, col: Int, value: String) {
  def valueAsInt: Option[Int] = Try(value.toInt).toOption
}

object SCell {
  def parseCell(cell: Cell): SCell = {
    SCell(cell.getRowIndex, cell.getColumnIndex, new DataFormatter().formatCellValue(cell))
  }
}