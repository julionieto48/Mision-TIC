import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
 //:::::::::::::::::::::::::::::::_____________________:::::::::::::::::::::::::::::::::::::


class Hielocos {
  
  //________________________________________________________________________________________
  static void ColorDeHieloco(){
    String[] lista = { "amarillo","verde","azul","azul","azul","amarillo","verde","gris","azul","gris"};
   
    ArrayList<String> listaColores  = new ArrayList<String>();
    ArrayList<String> noRepetidos = new ArrayList<String>(); 
    listaColores.addAll(Arrays.asList(lista));
    System.out.println(listaColores);
 
    for (int i = 0;i < listaColores.size(); i++){
      if ( !noRepetidos.contains( listaColores.get(i)) ){
        noRepetidos.add(listaColores.get(i)); // listaColores[i] si es array
      }
    }
    System.out.println(noRepetidos);
   
  }

  //________________________________________________________________________________________
  static void noTengo(){
    int[] a = {3,5,7,10,15,16};
    int[] b = {4,10,5,8};
    ArrayList<Integer> listaOtraPersona  = new ArrayList<Integer>();
    ArrayList<Integer> listaTengo = new ArrayList<Integer>();

    // convertir de array a arraylist
    for (int i = 0; i < a.length; i++) listaOtraPersona.add(new Integer(a[i])); //listaOtraPersona.addAll(Arrays.asList(a)); 

    for (int i = 0; i < b.length; i++) listaTengo.add(new Integer(b[i])); //listaTengo.addAll(Arrays.asList(b));

    ArrayList<Integer> repetidos = new ArrayList<Integer>();
        
    for (int i = 0; i < listaTengo.size(); i++){
      for (int j = 0; j < listaOtraPersona.size(); j++){
        //System.out.println(listaTengo.get(i) + " - " + listaOtraPersona.get(j));
        //System.out.println(listaTengo.get(i).equals(listaOtraPersona.get(j)));
        Boolean elBuleano = listaTengo.get(i).equals(listaOtraPersona.get(j)) ;
        if(elBuleano == true ){
          repetidos.add(listaOtraPersona.get(j));  // estos son los que ambos tenemos
        }
        if( listaTengo.get(i) == listaOtraPersona.get(j) ){
          // repetidos.add(listaOtraPersona.get(j));  // estos son los que ambos tenemos
        }
      }      
    }
    // System.out.println(repetidos);

    ArrayList<Integer> noTengoYo = new ArrayList<Integer>();

    for (int i = 0; i < listaOtraPersona.size(); i++){
      if(!repetidos.contains(listaOtraPersona.get(i)) ){ // si la lista repetidos no contiene el recorrido de la lista de la otra persona
        noTengoYo.add(listaOtraPersona.get(i)); // lo agrego  ami lista d eno los tengo
      }
    }
    System.out.println(noTengoYo);

  }
  //________________________________________________________________________________________
  static void puedoCambiar(){
    int[] a = {3,5,7,10,15,16};
    int[] b = {4,10,5,8};
    ArrayList<Integer> listaOtraPersona  = new ArrayList<Integer>();
    ArrayList<Integer> listaTengo = new ArrayList<Integer>();

    // convertir de array a arraylist
    for (int i = 0; i < a.length; i++) listaOtraPersona.add(new Integer(a[i])); //listaOtraPersona.addAll(Arrays.asList(a)); 

    for (int i = 0; i < b.length; i++) listaTengo.add(new Integer(b[i])); //listaTengo.addAll(Arrays.asList(b));

    ArrayList<Integer> repetidos = new ArrayList<Integer>();
        
    for (int i = 0; i < listaTengo.size(); i++){
      for (int j = 0; j < listaOtraPersona.size(); j++){
        Boolean elBuleano = listaTengo.get(i).equals(listaOtraPersona.get(j)) ;
        if(elBuleano == true ){
          repetidos.add(listaOtraPersona.get(j));  // estos son los que ambos tenemos
        }
      }      
    }
    System.out.println(repetidos.size());

  }


//:::::::::::::::::::::::::::::::_____________________:::::::::::::::::::::::::::::::::::::
  public static void main(String[] args) {
    ColorDeHieloco();
    System.out.println("____________________");
    noTengo();
    System.out.println("____________________");
    puedoCambiar();
    
  }
}