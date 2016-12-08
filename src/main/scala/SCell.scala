import org.apache.poi.ss.usermodel.{Cell, DataFormatter}

case class SCell(row: Int, col: Int, value: String)

object SCell {
  def fromCell(cell: Cell): SCell = {
    SCell(cell.getRowIndex, cell.getColumnIndex, new DataFormatter().formatCellValue(cell))
  }
}