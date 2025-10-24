
class Matrix {

    private double[][] data;
    private int rows;
    private int columns;

    public Matrix(double[][] data) {
        if (data == null || data.length == 0) {
            this.data = new double[][] { { 0 } };
            this.columns = 1;
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

        this.rows = data.length;
        this.columns = data[0].length;
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
        if (this.columns != otherMatrix.rows) {
            return null;
        }

        // https://en.wikipedia.org/wiki/Matrix_multiplication
        // c[i][j] = suma od 0 do n taka że ( a[i][n] * b[n][j])
        double[][] resultData = new double[this.rows][otherMatrix.columns];
        for (int i = 0; i < resultData.length; i++) {
            for (int j = 0; j < resultData[0].length; j++) {
                for (int k = 0; k < this.columns; k++) {
                    resultData[i][j] += this.data[i][k] * otherMatrix.data[k][j];
                }
            }
        }

        return new Matrix(resultData);
    }

    public Matrix transpose() {
        double[][] resultData = new double[this.columns][this.rows];
        for (int i = 0; i < this.columns; i++) {
            for (int j = 0; j < this.rows; j++) {
                resultData[i][j] = this.data[j][i];
            }
        }

        return new Matrix(resultData);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (double[] row : data) {
            sb.append("[");
            for (double value : row) {
                sb.append(value).append(" ");
            }
            sb.append("]\n");
        }
        return sb.toString();
    }
}
