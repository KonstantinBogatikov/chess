class Horse extends ChessPiece implements ChessFigure{

    public Horse(String color) {
        super(color);
    }

    @Override
    String getColor() {
        return color;
    }

    @Override
    boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        //Проверяем, что в метод переданы координаты в пределах шахматной доски.
        //Проверяем, что конь ходит буквой г.
        if (checkPos(line, column, toLine, toColumn) && Math.abs((line - toLine) * (column - toColumn)) == 2) {
            //Проверяем, что клетка, куда хотим пойти, пустая.
            if (checkChessSquare(chessBoard, toLine, toColumn)){
                return true;
            //Если в клетке есть фигура, то проверяем, что она чужого цвета
            } else {
                return !compareFiguresColor(chessBoard, line, column, toLine, toColumn);
            }
        } else return false;
    }

    @Override
    String getSymbol() {
        return "H";
    }
}