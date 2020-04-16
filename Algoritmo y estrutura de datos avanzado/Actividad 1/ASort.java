import java.lang.*;
import java.util.*;
import java.io.*;
class ASort{
  public static void main(String[] args) {
  if(args.length !=1) {
    System.out.println("Error! ejecute <java ASort n>" ); System.exit(-1);
  }
  int  n = new Integer(args[0]).intValue();
  Random r = new Random(); 
  int []a = new int[n];
  int []b = new int[n];
  int []c = new int[n];


 //--Se generan los arreglos a ordenar 
 for(int i=0; i < n; i++) {
     a[i]= r.nextInt();
     b[i] = a[i]; 
     c[i] = a[i];
  }


  System.out.println("-------------------------------------------- ");
  System.out.println("Tamaño del arreglo: " + n);
//----HeapSort   
  long x = System.currentTimeMillis();
  HeapSort(c);
  long y = System.currentTimeMillis();
  System.out.println("Tiempo HeapSort: " + (y-x) + " milisegundos");
  //Print(c); 

//----InsertSort   
  x = System.currentTimeMillis();
  InsertSort(b);
  y = System.currentTimeMillis();
  System.out.println("Tiempo InsertSort: " + (y-x) + " milisegundos");
 // Print(b);

//----Sort Básico
  x = System.currentTimeMillis();
  SortBasico(a);
  y = System.currentTimeMillis();
  System.out.println("Tiempo SortBasico:  " + (y-x) + " milisegundos");
//  Print(a);
  System.out.println("-------------------------------------------- ");

}
// Fin main
//==========================================================

//----------- Algoritmos de ordenamiento ------------------
//======SortBasico=====================
public static void SortBasico(int []a) { 
  int n=a.length;
 int i;
  for ( i=0; i < n-1; i++) 
    for (int j = i+1; j < n; j++)
       if(a[i] > a[j]) Intercambia(a, i,j);
 }

//======BubbleSort=====================
public static void SortBasicoRR(int []a, int l, int r) { 
 if (l != r) { 
 for (int i=l+1; i <= r; i++) 
    if(a[l] > a[i]) Intercambia(a, l,i);
  SortBasicoRR(a, l+1,r);
 } 
}

//================== InsertSort===== 
static void InsertSort(int []a) 
 { 
   for(int j= 1 ; j < a.length; j++) 
   {
    int key = a[j];
    int i = j-1; 
    while(i >= 0 && a[i] > key) {  
     a[i+1] = a[i]; 
     i--; 
    } 
   a[i+1] = key;
   }
 }

//=================== HeapSort======//
 static void HeapSort(int a[]) {
 //  System.out.print("==========Build =========");
   BuildHeap(a);
 //  System.out.print("==========Build =========");
   for(int i = a.length-1; i>=0; i--) {
   Intercambia(a,0,i);
   Heapify(a,0,i-1);
  }
 }

 static void BuildHeap(int a[]) {
 for(int i = a.length/2; i>=0; i--)
  Heapify(a,i,a.length-1);
 }
 
 private static void Heapify(int a[], int i, int j){
//  System.out.println();
//  for (int l=0; l < a.length; i++) System.out.println(a[l] + " ");
  if((2 * i +1)  <= j){
    int k;
    if((2*i + 2) <=j) k = (a[2*i +2] >= a[2*i+1]) ? 2*i +2 : 2*i+1;
    else k = 2*i+1;
    if(a[i] < a[k]) {
      Intercambia(a,i,k);
      Heapify(a,k,j);
      }
    }
   }

 private static void Intercambia(int a[], int i, int j) {
    int t = a[i];
      a[i] = a[j];
      a[j] = t;
  }
//=================== fin HeapSort======//

 public static void Print(int []array) { 
  System.out.println("--Inicio--");
  for(int i=0; i< array.length;i++) System.out.print(array[i]+ " ");
  System.out.println();
  System.out.println("--Fin--");
 }
}// Fin Clase
