public class Pawn extends ChessPiece implements ChessFigure{

    public Pawn(String color) {
        super(color);
    }

    @Override
    String getColor() {
        return color;
    }

    @Override
    boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        //Проверяем, что методу переданы координаты ячейки в пределах шахматной доски.
        if (checkPos(line, column, toLine, toColumn)
                //проверяем, что пешка двигается
                && line != toLine
                //Проверяем максимальный шаг пешки. Значение "2", т.к. ход с начальной линии может быть на 2 клетки.
                && Math.abs(line-toLine) <= 2
                //Проверяем, что по диагонали пешка может сдвинуться только на 1 клетку.
                && Math.abs(column - toColumn) <= 1
                // проверяем, что пешка либо двигается прямо, либо пытается съесть по диагонали чужую фигуру
                && (column == toColumn || (Math.abs(toColumn-column) == 1 &&
                        !compareFiguresColor(chessBoard, line, column, toLine, toColumn) ))){
            //Проверяем, что белые с начальной позиции могут пойти на одну или две клетки вперед.
            if (color.equals("White") && line == 1 && ((toLine - line) == 2 || (toLine - line) == 1)
                    //Проверяем, что на пути нет фигур/
                    && checkChessSquare(chessBoard, line+1, column)
                        && checkChessSquare(chessBoard, line+2, column) ){
                return true;
            //Проверяем, что белые либо идут на одну клетку вперед, либо едят по диагонали на одну клетку вперед.
            //Не знаю можно ли есть назад. Поэтому сделал так, что есть можно только вперед.
            //Съесть "на проходе" не делал (времени нет).
            } if (color.equals("White") && (toLine - line) == 1) {
                //Проверяем, что на пути нет фигур, если пешка ходит.
                if (toColumn == column && checkChessSquare(chessBoard, line+1, column)){
                    return true;
                }
                //Выше проверили цвет фигур, и если дошли до этого условия, значит они разные.
                //Также можно сделать вывод, что в ячейке находится фигура, иначе бы не выполнилось условие выше про цвет.
                //Далее проверяется, что ход по диагонали, иначе можно получить кейс, когда пешка есть фигуру перед собой.
                if ((toColumn - column) == 1 && chessBoard.board[toLine][toColumn] != null){
                    return true;
                }
            //Проверяем, что черные с начальной позиции могут пойти на одну или две клетки вперед.
            } if (color.equals("Black") && line == 6 && ((line - toLine) == 2 || (line - toLine) == 1)
                    //Проверяем, что на пути нет фигур.
                    && checkChessSquare(chessBoard, line-1, column)
                        && checkChessSquare(chessBoard, line-2, column)){
                return true;
            //Проверяем, что черный либо идут на одну клетку вперед, либо едят по диагонали на одну клетку вперед.
            //Не знаю можно ли есть назад. Поэтому сделал так, что есть можно только вперед.
            //Съесть "на проходе" не делал (времени нет).
            } if (color.equals("Black") && (line - toLine) == 1){
                //Проверяем, что на пути нет фигур, если пешка ходит.
                if (toColumn == column && checkChessSquare(chessBoard, line-1, column)){
                    return true;
                }
                //Выше проверили цвет фигур, и если дошли до этого условия, значит они разные.
                //Также можно сделать вывод, что в ячейке находится фигура, иначе бы не выполнилось условие выше про цвет.
                //Далее проверяется, что ход по диагонали, иначе можно получить кейс, когда пешка есть фигуру перед собой.
                if ((column - toColumn) == 1 && chessBoard.board[toLine][toColumn] != null){
                    return true;
                }
            } else return false;
        }
        return false;
    }

    @Override
    String getSymbol() {
        return "P";
    }
}