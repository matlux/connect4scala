package connect

import java.awt.Color

/**
 * Created by mathieu on 23/09/2015.
 */
object Connect4 {

  type BoardState = Vector[Colour]

  val COLUMN_COUNT = 4
  val ROW_COUNT = 3

  sealed trait Colour

  object Red extends Colour{
    override def toString = "R "
  }
  object Yellow extends Colour{
    override def toString = "Y "
  }
  object Free extends Colour{
    override def toString = "_ "
  }

  def isFull(board: BoardState, columnStart: Int) : Boolean = {
    val lastPossible = columnStart + ROW_COUNT
    board(lastPossible) != Free

  }

  def findIndex(position: Int, board: BoardState) : Option[Int] = {
    def loop (position: Int): Int = {
      if(board(position) == Free) position
      else loop(position + 1)
    }
    if(isFull(board, position)) None
    else
      Some(loop(position))
  }

  def column2Idx(column: Int): Int = column * ROW_COUNT

  def oppositeColour(colour :Colour) = colour match {
    case Red => Yellow
    case Yellow => Red
    case Free => Free
  }

  def newBoard(board:BoardState,idx:Int,colour:Colour) = board.slice(0,idx) ++ Vector(colour) ++ board.slice(idx + 1, (ROW_COUNT*COLUMN_COUNT))


  def step(column: Int, colour: Colour, board: BoardState) : (Colour,BoardState) = {
    val bottomIdx = column2Idx(column)
    findIndex(bottomIdx,board) match {
      case None => (oppositeColour(colour),board)
      case Some(idx) => (Free, newBoard(board,idx,colour))
    }

  }


  def renderBoard(board:BoardState): String = {
    if (board.size == 0) ""
    else "\n" + board.take(ROW_COUNT).mkString("") +  renderBoard(board.drop(ROW_COUNT))
  }

  def main (args: Array[String]) {
    println("hellow")

    val column = 1
    val board = (0 until (ROW_COUNT*COLUMN_COUNT)).toVector.map(n => Free)
    val colour = Red

    val board2 = step(column,colour,board)._2
    val board3 = step(column,Yellow,board2)._2
    val board4 = step(2,Red,board3)._2
    println(renderBoard(board2))
    println("\n")
    println(renderBoard(board3))
    println("\n")
    println(renderBoard(board4))
  }



}
