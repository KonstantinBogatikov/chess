/*Интерфейс для фигур. Содержит часто применяемые методы:
1) Проверка checkPos, что координаты находятся на доске (от 0 до 7);
2) Сравнение цвета фигур compareFiguresColor. Необходимо для выполнения "съедания" фигур разного цвета.
Если клетка пустая, то возвращается true для дальнейшей обработке в методе canMoveToPosition;
3) Проверка checkChessSquare, что клетка шахматной доски не пустая.
* */

public interface ChessFigure {

    default boolean checkPos(int posLine, int posColumn, int posToLine, int posToColumn) {
        return (posLine >= 0 && posLine <= 7) && (posColumn >= 0 && posColumn <= 7)
                && (posToLine >= 0 && posToLine <= 7) && (posToColumn >= 0 && posToColumn <= 7);
    }

    default boolean compareFiguresColor(ChessBoard chessBoard, int line, int column, int toLine, int toColumn){
        if (chessBoard.board[toLine][toColumn] != null){
            return chessBoard.board[line][column].getColor().equals(chessBoard.board[toLine][toColumn].getColor());
        } else return true;
    }

    default boolean checkChessSquare(ChessBoard chessBoard, int line, int column){
        return chessBoard.board[line][column] == null;
    }
}