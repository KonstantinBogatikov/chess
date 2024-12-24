public class King extends ChessPiece implements ChessFigure{

    public King(String color) {
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
            //Проверяем, что мы хотим пойти в клетку, которая не находится под ударом противника
            && !isUnderAttack(chessBoard, toLine, toColumn)
                //Проверяем, что клетка, куда хотим пойти, пустая
                && (checkChessSquare(chessBoard, toLine, toColumn) ||
                //Проверяем, что клетка может быть не пустой, но тогда на ней должна стоять чужая фигура
                (!checkChessSquare(chessBoard, toLine, toColumn) &&
                        !compareFiguresColor(chessBoard, line, column, toLine, toColumn)))
        ) {
            int difColumn = Math.abs(column - toColumn);
            int difLine = Math.abs(toLine - line);
            //В методе проверяется, что король двигается хотя бы на 1 клетку в любом направлении
            //и не может переместиться на 2 в любом направлении.
            return checkPosKing(difLine, difColumn);
        } else return false;
    }

    //Метод для проверки хода короля. Применяется в методе isUnderAttack, чтобы можно было проверить, что король ходит
    //в клетку, которая не находится под ударом чужого короля. В методе нет проверки isUnderAttack, чтобы не было рекурсии.
    private boolean canMoveToPositionAlt(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        //Проверяем, что в метод переданы координаты в пределах шахматной доски.
        if (checkPos(line, column, toLine, toColumn)
                //Проверяем, что клетка, куда хотим пойти, пустая
                && (checkChessSquare(chessBoard, toLine, toColumn) ||
                //Проверяем, что клетка может быть не пустой, но тогда на ней должна стоять чужая фигура
                (!checkChessSquare(chessBoard, toLine, toColumn) &&
                        !compareFiguresColor(chessBoard, line, column, toLine, toColumn)))
        ) {
            int difColumn = Math.abs(column - toColumn);
            int difLine = Math.abs(toLine - line);
            //В методе проверяется, что король двигается хотя бы на 1 клетку в любом направлении
            //и не может переместиться на 2 в любом направлении.
            return checkPosKing(difLine, difColumn);
        } else return false;
    }

    @Override
    String getSymbol() {
        return "K";
    }

    public boolean checkPosKing(int pos1, int pos2){
        return (pos1 == 0 || pos1 == 1) && (pos2 == 0 || pos2 == 1) && ( pos1 + pos2 == 2 || pos1 + pos2 == 1);
    }

    public boolean isUnderAttack(ChessBoard chessBoard, int lineFinal, int columnFinal) {
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                //Проверяем, что клетка не пустая. Также исключаем обработку белого короля, чтобы не было рекурсии

                if (chessBoard.board[i][j] != null) {
                    //Проверяем цвет фигуры. Нет смысла проверять свои фигуры.
                    //Проверяем, что фигура не чужой король. Для чужого короля надо проверять без isUnderAttack
                    if (!chessBoard.board[i][j].getColor().equals(color) &&
                            !chessBoard.board[i][j].getSymbol().equals("K")){
                        //Проверяем, что чужая фигура может пойти в ячейку.
                        if (chessBoard.board[i][j].canMoveToPosition(chessBoard, i, j, lineFinal, columnFinal)) {
                            return true;
                        }
                    }
                    //Проверяем, ход чужого короля.
                    else if (!chessBoard.board[i][j].getColor().equals(color) &&
                                chessBoard.board[i][j].getSymbol().equals("K")){
                        if (canMoveToPositionAlt(chessBoard, i, j, lineFinal, columnFinal)) {
                            return true;
                        }
                    }
                }
            }
        } return false;
    }
}