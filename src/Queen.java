public class Queen extends ChessPiece implements ChessFigure{

    public Queen(String color) {
        super(color);
    }

    @Override
    String getColor() {
        return color;
    }

    @Override
    boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (checkPos(line, column, toLine, toColumn)
                //Проверяем, что клетка, куда хотим пойти, пустая
                && (checkChessSquare(chessBoard, toLine, toColumn) ||
                //Проверяем, что клетка может быть не пустой, но тогда на ней должна стоять чужая фигура
                (!checkChessSquare(chessBoard, toLine, toColumn) &&
                        !compareFiguresColor(chessBoard, line, column, toLine, toColumn)))){
            //Проверяем идет ли королева по диагонали
            if (checkPosQueen(Math.abs(column - toColumn), Math.abs(line - toLine))){
                int i = 1;
                boolean move = true;
                //Проверка ячеек при движении по диагонали вправо наверх.
                if (toLine - line > 0 && toColumn - column > 0) {
                    while (move && i < toLine - line){
                        move = checkChessSquare(chessBoard, line+i, column+i);
                        i++;
                    }
                    //Проверка ячеек при движении по диагонали влево наверх.
                } else if (toLine - line > 0 && toColumn - column < 0) {
                    while (move && i < toLine - line) {
                        move = checkChessSquare(chessBoard, line + i, column - i);
                        i++;
                    }
                    //Проверка ячеек при движении по диагонали влево вниз.
                } else if (toLine - line < 0 && toColumn - column < 0) {
                    while (move && i < line - toLine) {
                        move = checkChessSquare(chessBoard, line - i, column - i);
                        i++;
                    }
                } else {
                    while (move && i < line - toLine) {
                        move = checkChessSquare(chessBoard, line - i, column + i);
                        i++;
                    }
                }
                return move;
            }
            //Проверяем идет ли королева по вертикальной или горизонтальной линии
            else if (column == toColumn || line == toLine) {
                int i = 1;
                boolean move = true;
                //Если движение по вертикали, то проверяем, что вертикальный путь (линия) пустой.
                //Также проверяется, что фигура двигается, а не стоит на месте.
                if (column == toColumn && Math.abs(line-toLine) >= 1){
                    int minL = Math.min(line, toLine);
                    while (move && i < Math.abs(toLine - line)){
                        move = checkChessSquare(chessBoard, minL+i, column);
                        i++;
                    }
                    return move;
                }
                //Если движение по горизонтали, то проверяем, что горизонтальный путь (линия) пустой.
                //Также проверяется, что фигура двигается, а не стоит на месте.
                else if (line == toLine && Math.abs(column - toColumn) >= 1) {
                    int minC = Math.min(column, toColumn);
                    while (move && i < Math.abs(toColumn - column)){
                        move = checkChessSquare(chessBoard, line, minC+i);
                        i++;
                    }
                    return move;
                }
            }
        }
        return false;
    }

    @Override
    String getSymbol() {
        return "Q";
    }

    public boolean checkPosQueen(int c, int l){
        return l >= 1 && l <= 7 && c==l;
    }
}
