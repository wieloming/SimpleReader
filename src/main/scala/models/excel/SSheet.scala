package models.excel

import models.dto.Node


case class SSheet(private val cells: List[SCell]) {
  private val IDsColIndex = 3
  private val ParentsColIndex = 0

  def asNodes: List[Node] = {
    parents.flatMap(toNode)
  }

  private def toNode(cell: SCell): Option[Node] = {
    val id = getCellId(cell)
    val children = getCellChildren(cell).flatMap(toNode)
    id.map(Node(_, cell.value, children))
  }

  private def parents = cells.filter(_.col == ParentsColIndex)

  private def find(row: Int, col: Int): Option[SCell] = {
    cells.find(c => c.row == row && c.col == col)
  }

  private def getCellId(cell: SCell): Option[Int] = {
    if (cell.col >= IDsColIndex) None
    else find(cell.row, IDsColIndex).flatMap(_.valueAsInt)
  }

  private def getCellChildren(cell: SCell): List[SCell] = {
    if (cell.col >= IDsColIndex - 1) List.empty
    else {
      val childrenCol = cell.col + 1
      val nextParent = cells.find(c => c.col == cell.col && c.row > cell.row)
      nextParent match {
        case None =>
          val isAfterParent: (SCell) => Boolean = c => c.col == childrenCol && c.row > cell.row
          cells.filter(isAfterParent)
        case Some(parent) =>
          val isBetweenParents: (SCell) => Boolean = c => c.col == childrenCol && c.row < parent.row && c.row > cell.row
          cells.filter(isBetweenParents)
      }
    }
  }

}
