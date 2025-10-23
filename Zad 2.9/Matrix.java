
class Matrix {

    private double[][] data;
    private int rows;
    private int columns;

    public Matrix(double[][] data) {
        if (data == null || data.length == 0) {
            this.data = new double[][] { { 0 } };
            this.rows = 1;
            return;
        }

        for (int i = 0; i < data.length; i++) {
            if (data[i].length != data[0].length) {
                this.data = new double[][] { { 0 } };
                this.columns = 1;
                this.rows = 1;
                return;
            }
        }

        this.columns = data.length;
        this.rows = data[0].length;
        this.data = new double[this.rows][this.columns];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                this.data[i][j] = data[i][j];    
            }
        }
    }

    public Matrix add(Matrix otherMatrix) {
        if (this.columns != otherMatrix.columns || this.rows != otherMatrix.rows) {
            return null;
        }

        double[][] resultData = new double[this.rows][this.columns];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                resultData[i][j] = this.data[i][j] + otherMatrix.data[i][j];
            }
        }
        return new Matrix(resultData);
    }

    public Matrix subtract(Matrix otherMatrix) {
        if (this.columns != otherMatrix.columns || this.rows != otherMatrix.rows) {
            return null;
        }

        double[][] resultData = new double[this.rows][this.columns];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                resultData[i][j] = this.data[i][j] - otherMatrix.data[i][j];
            }
        }
        return new Matrix(resultData);
    }

    public Matrix multiply(Matrix otherMatrix) {
        if (this.rows != otherMatrix.columns) {
            return null;
        }

        return new Matrix(resultData);
    }
}
