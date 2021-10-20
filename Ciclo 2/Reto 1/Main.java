import java.util.Scanner;
class Main {

  static void marvel(int bruja){
    // upm
    int capMarvel = (bruja * 2) + 4;
    int thanos = (capMarvel + bruja) / 5;

    String nivel = "cero";

    if (thanos >= 0 && thanos <= 20){nivel = "uno";}
    else if(thanos >= 21 && thanos <= 30){nivel = "dos";}
    else if(thanos >= 31 && thanos <= 50){nivel = "tres";}
    else if(thanos > 50){nivel = "cuatro";}
    System.out.println(bruja + " " + capMarvel + " " + thanos );
    System.out.println(nivel);
    
  }


  public static void main(String[] args) {
    Scanner sc= new Scanner(System.in);  
    int entrada = sc.nextInt();
    marvel(entrada);
  }
}