import java.util.Scanner;

public class reto1 {
    public static String promedionrca (float prom) {
        String a = "";
        if (prom <= 100 && prom >= 81.1){
            a = "INVIABLE SANITARIAMENTE";
        } else if (prom <= 80 && prom >= 35.1){
            a =  "ALTO";
        } else if (prom <= 35 && prom >= 14.1){
            a = "MEDIO";
        } else if (prom <= 14 && prom >= 5.1){
            a =  "BAJO";
        } else if (prom <= 5 && prom >= 0){
            a =  "SIN RIEGO";
        }
        return a;
    }
    public static float conteo (Float[] datos) {
        float a = 0;
        for (Float dato : datos) {
            if (dato > 5 && dato <= 35) {
                a++;
            }
        }
        return a;
    }
    public static String sino (Float[] datos) {
        int a = 0;
        for (Float dato : datos) {
            if (dato > 80 && dato <= 100) {
                a++;
            }
        }
        if (a >= 2){
            return "SI";
        } else {
            return "NO";
        }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // numero de datos que va a ingresar
        int entrada;
        entrada = input.nextInt();
        input.nextLine();
        String datos = input.nextLine();

        // arrays
        String[] datos1 = datos.split(" ");
        Float[] datosflo = new Float[entrada];

        // cambio de String a float
        for (int i = 0; i < entrada; i++) {
            String b = datos1[i];
            datosflo[i] = Float.valueOf(b);
        }

        // suma y promedio de los valores
        float suma = 0;
        for (int i = 0; i < entrada; i++) {
            suma = suma + datosflo[i];
        }
        float prom = suma / datosflo.length;

        // nivel de riesgo mediante funsion y condicinal
        String nr = promedionrca(prom);

        float calagu = (conteo(datosflo)/entrada)*100;
        String masq = sino(datosflo);

        // output esperados
        System.out.println(nr);
        System.out.printf("%.2f%n",calagu);
        System.out.println(masq);
    }
}