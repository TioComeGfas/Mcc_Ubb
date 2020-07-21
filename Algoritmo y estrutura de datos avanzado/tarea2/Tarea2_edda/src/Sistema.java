public class Sistema {

    public static void main(String[] args) {
        int[] tamanios = new int[] {10,25,50,75,100,200};

        System.out.println("Alumno: Fredy Moncada");
        System.out.println("Tarea: 2");
        System.out.println("Resolviendo el problema 1");
        System.out.println("");

        for(int tam: tamanios){
            System.out.println("tamanio: "+tam);
            Problema1 problema1 = new Problema1(tam);
            problema1.aplicar();
        }

        System.out.println("Resolviendo el problema 2");
        System.out.println("");

        tamanios = new int[] {5, 10, 15, 20, 25, 30, 35};

        for(int tam: tamanios){
            System.out.println("tamanio: "+tam);
            Problema2 problema2 = new Problema2(tam);
            problema2.aplicar();
        }
    }
}
