package matrix;

public class MatrixEntry<E> {
    private int row;
    private int col;
    private E value;

    public MatrixEntry(int row, int col, E value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }

    public int getRow() { return row; }
    public int getCol() { return col; }
    public E getValue() { return value; }

    @Override
    public String toString() {
        return "Pos:(" + row + "," + col + ") Val:(" + value + ")";
    }
}