import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Problema2 {

    private int[][] A;
    private int tamanio;

    public Problema2(int tamanio){
        this.tamanio = tamanio;

        this.A = new int[tamanio][tamanio];

        Random random = new Random();
        for(int i=0; i < tamanio; i++){
            for(int j=0; j < tamanio; j++){

            }
        }
    }

    public void aplicar(){
        long time;
        System.out.println("**********************");
        System.out.println("Aplicando determinista");
        System.out.println("**********************");
        System.out.println("");
        time = System.currentTimeMillis();
        deterministico();
        System.out.println("El tiempo transcurrido fue: "+ ((System.currentTimeMillis() - time)) + " milisegundos");
        System.out.println();
        System.out.println("**********************");
        System.out.println("Aplicando probabilistico");
        System.out.println("**********************");
        System.out.println("");
        time = System.currentTimeMillis();
        probabilistico();
        System.out.println("El tiempo transcurrido fue: "+ ((System.currentTimeMillis() - time)) + " milisegundos");
        System.out.println("");
    }

    private void deterministico(){
        A = new int[tamanio][tamanio];
        if (!solveNQUtil(0)) {
            System.out.print("La solucion no existe");
            return;
        }

        printSolution();
    }

    private void printSolution(){
        for (int i = 0; i < tamanio; i++) {
            for (int j = 0; j < tamanio; j++) System.out.print(" " + A[i][j] + " ");
            System.out.println();
        }
    }

    private boolean isSafe(int row, int col){
        int i, j;
        for (i = 0; i < col; i++) if (A[row][i] == 1) return false;

        for (i = row, j = col; i >= 0 && j >= 0; i--, j--) if (A[i][j] == 1) return false;

        for (i = row, j = col; j >= 0 && i < tamanio; i++, j--) if (A[i][j] == 1) return false;

        return true;
    }

    private boolean solveNQUtil(int col){
        if (col >= tamanio) return true;

        for (int i = 0; i < tamanio; i++) {
            if (isSafe(i, col)) {
                A[i][col] = 1;
                if (solveNQUtil(col + 1)) return true;
                A[i][col] = 0;
            }
        }

        return false;
    }


    private void probabilistico(){
        List<Integer> col = new LinkedList<>();
        List<Integer> diag45 = new LinkedList<>();
        List<Integer> diag135 = new LinkedList<>();

        int fila = 1;

        Random rand = new Random();
        List<Integer> libres;
        do {
            int columna;
            libres = new LinkedList<>();

            for (columna = 1; columna < tamanio ; columna++)
                if (!col.contains(columna) &&
                    !diag45.contains(fila-columna) &&
                    !diag135.contains(fila+columna))
                    libres.add(columna);

            if (!libres.isEmpty()) {
                columna = libres.get(rand.nextInt(libres.size()));
                A[fila][columna] = 1;
                col.add(columna);
                diag45.add(fila-columna);
                diag135.add(fila+columna);
                fila++;
            }

        }while (fila > tamanio && !libres.isEmpty());

        if (libres.isEmpty()) {
            System.out.print("La solución no existe");
            System.out.println("");
            return;
        }

        printSolution();
    }

}
