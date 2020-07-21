public class Problema1 {

    private int[][] A;
    private int[][] B;
    private int[][] C;
    private int tamanio;
    private double[] epsilon = { (double)1/2, (double)1/4, (double)1/8, (double)1/16, (double)1/32, (double)1/64, (double)1/128};

    public Problema1(int tamanio){
        this.tamanio = tamanio;
        //this.epsilon = epsilon;

        this.A = new int[tamanio][tamanio];
        this.B = new int[tamanio][tamanio];
        this.C = new int[tamanio][tamanio];
    }

    private void loadABequalC(){
        for(int i=0; i < tamanio; i++){
            for(int j=0; j < tamanio; j++){
                int number = (int) (Math.random() * 100);
                A[i][j] = number;
                C[i][j] = number;
                if(i==j) B[i][j] = 1;
                else B[i][j] = 0;
            }
        }
    }

    private void loadABnotEqualC(){
        for(int i=0; i < tamanio; i++){
            for(int j=0; j < tamanio; j++){
                int number = (int) (Math.random() * 100);
                A[i][j] = number;
                C[i][j] = number;
                if(i==j) B[i][j] = 1;
                else B[i][j] = 0;
            }
        }

        //valor fuera del rango del random = 100
        //por lo tanto siempre dara un valor diferente
        C[0][0] = 101;
    }

    public void aplicar(){
        long time;
        System.out.println("**********************");
        System.out.println("Aplicando determinista");
        System.out.println("**********************");
        System.out.println("");
        System.out.println("AB=C");
        loadABequalC();
        time = System.currentTimeMillis();
        deterministico();
        System.out.println("El tiempo transcurrido fue: "+ ((System.currentTimeMillis() - time)) + " milisegundos");
        System.out.println("");
        System.out.println("AB!=C");
        loadABnotEqualC();
        time = System.currentTimeMillis();
        deterministico();
        System.out.println("El tiempo transcurrido fue: "+ ((System.currentTimeMillis() - time))+ " milisegundos");
        System.out.println("");

        System.out.println("**********************");
        System.out.println("Aplicando probabilistico");
        System.out.println("**********************");
        System.out.println("");
        System.out.println("AB=C");
        loadABequalC();
        probabilistico();
        System.out.println("");
        System.out.println("AB!=C");
        loadABnotEqualC();
        probabilistico();
        System.out.println("");
    }

    private void deterministico(){
        int AColLength = A[0].length;

        int mRRowLength = A.length;
        int mRColLength = B[0].length;
        int[][] X = new int[tamanio][tamanio];
        for (int i = 0; i < mRRowLength; i++) {
            for (int j = 0; j < mRColLength; j++) {
                for (int k = 0; k < AColLength; k++) {
                    X[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        boolean error = false;
        for(int i = 0; i < tamanio; i++){
            for(int j = 0; j < tamanio; j++){
                if(X[i][j] != C[i][j]) {
                    error = true;
                    break;
                }
            }
        }

        if(error) System.out.println("El resutado es: A*B!=C");
        else System.out.println("El resutado es: A*B=C");
    }

    private void probabilistico(){
        for(double epsilon: epsilon){
            System.out.println("probabilidad: "+epsilon);

            long time = System.currentTimeMillis();

            if(testProdMatEpsilon(epsilon)) System.out.println("El resutado es: A*B=C");
            else System.out.println("El resutado es: A*B!=C");

            System.out.println("El tiempo transcurrido fue: "+ ((System.currentTimeMillis() - time))+ " milisegundos");
            System.out.println("");
        }
    }


    private boolean testProdMat(){
        int[] X = new int[tamanio];

        for(int j=0; j < tamanio; j++){
            if((int) (Math.random() * 10) >= 6) X[j] = 1;
            else X[j] = 0;
        }

        //copia de A en XA
        int[][] XA = new int[tamanio][tamanio];
        for(int i=0; i < tamanio; i++){
            System.arraycopy(A[i], 0, XA[i], 0, tamanio);
        }

        // multiplicacion X*A
        // almacenandolo en XA
        for(int i=0; i < tamanio; i++){
            XA[0][i] = X[i] * A[i][0];
        }

        //multiplicacion del XA*B
        int[][] XAB = new int[tamanio][tamanio];
        int AColLength = XA[0].length;

        int mRRowLength = XA.length;
        int mRColLength = B[0].length;
        for (int i = 0; i < mRRowLength; i++) {
            for (int j = 0; j < mRColLength; j++) {
                for (int k = 0; k < AColLength; k++) {
                    XAB[i][j] += XA[i][k] * B[k][j];
                }
            }
        }

        //copia de C en XC
        int[][] XC = new int[tamanio][tamanio];
        for(int i=0; i < tamanio; i++){
            System.arraycopy(C[i], 0, XC[i], 0, tamanio);
        }

        // multiplicacion X*C
        // almacenandolo en XC
        for(int i=0; i < tamanio; i++){
            XC[0][i] = X[i] * C[i][0];
        }

        for(int i = 0; i < tamanio; i++){
            for(int j = 0; j < tamanio; j++){
                if(XAB[i][j] != XC[i][j]) return false;
            }
        }

        return true;
    }

    private boolean repeatTestProdMat(double k){
        for(int i=0; i < k; i++) if(!testProdMat()) return false;
        return true;
    }

    private boolean testProdMatEpsilon(double epsilon){
        double k = Math.log(1/epsilon);
        return repeatTestProdMat(k);
    }
}
