public class Bishop extends ChessPiece implements ChessFigure{

    public Bishop(String color) {
        super(color);
    }

    @Override
    String getColor() {
        return color;
    }

    @Override
    boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        //Проверяем, что в метод переданы координаты в пределах шахматной доски.
        if (checkPos(line, column, toLine, toColumn)
            //Проверяем, что слон пойдет по диагонали
            && checkPosBishop(Math.abs(column - toColumn), Math.abs(line - toLine))
            //Проверяем, что клетка, куда хотим пойти, пустая
                && (checkChessSquare(chessBoard, toLine, toColumn) ||
            //Проверяем, что клетка может быть не пустой, но тогда на ней должна стоять чужая фигура
                (!checkChessSquare(chessBoard, toLine, toColumn) &&
                        !compareFiguresColor(chessBoard, line, column, toLine, toColumn)))){
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
        } else return false;
    }

    @Override
    String getSymbol() {
        return "B";
    }

    public boolean checkPosBishop(int c, int l){
        return l >= 1 && l <= 7 && c == l;
    }
}