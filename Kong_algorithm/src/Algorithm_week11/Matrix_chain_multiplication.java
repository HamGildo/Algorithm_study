package Algorithm_week11;

public class Matrix_chain_multiplication {
    public int[][] Matrix_chain_order(Matrix[] matrices) {
        int n = matrices.length;
        int[][] m = new int[n][n];

        for(int i = 1; i < n; i++){
            m[i][i] = 0;
        }
        for(int l = 2; l < n; l++) { // l = chain_length
            for(int i = 1; i < n-l+1; i++) {
                int j = i + l - 1;
                m[i][j] = Integer.MAX_VALUE;
                for(int k = i; k < j; k++) {
                    int q = m[i][k] + m[k+1][j]
                            + matrices[i].getRow()*matrices[k].getColumn()*matrices[j].getColumn();
                    if(q < m[i][j]) {
                        m[i][j] = q;
                    }
                }
            }
        }

        return m;
    }


}
