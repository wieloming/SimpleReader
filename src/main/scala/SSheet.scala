import scala.util.Try

case class SSheet(cells: List[SCell]) {
  val IDsCol = 3

  def find(row: Int, col: Int): Option[SCell] = {
    cells.find(c => c.row == row && c.col == col)
  }

  def getCellId(cell: SCell): Option[Int] = {
    val idCell = if(cell.col < IDsCol)
      cells.find(c => c.col == IDsCol && c.row == cell.row)
    else None
    idCell.flatMap(c => Try(c.value.toInt).toOption)
  }

  def getCellChildren(cell: SCell): List[SCell] = {
    def rec(col: Int, row: Int): List[SCell] = find(row, col) match {
      case Some(c) => c :: rec(col, row + 1)
      case None => Nil
    }
    rec(cell.col + 1, cell.row + 1)
  }

  def toNode(cell: SCell): Option[Node] = {
    getCellId(cell).map(id => Node(id, cell.value, getCellChildren(cell).flatMap(toNode)))
  }

  def parents = cells.filter(_.col == 0)
}
