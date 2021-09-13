import java.util.Collections;
import java.util.Scanner;

public class reto2 {
    public static void main(String[] args) {
        CuerpoDeAgua cda = new CuerpoDeAgua();
        Scanner input = new Scanner(System.in);

        // Cantidad de cuerpos a analizar
        int entra = input.nextInt();
        input.nextLine();

        // datos entrada de cada cuerpo de agua y almacenarlos
        for (int i = 0; i < entra; i++){
            String datos = input.nextLine();
            String[] tablastr = datos.split(" ");
            cda.Nombre.add(tablastr[0]);
            cda.id.add(Integer.parseInt(tablastr[1]));
            cda.Municipio.add(tablastr[2]);
            cda.IRCA.add(Double.parseDouble(tablastr[3]));
        }
        // cantidad de cuerpos de agua igual o superior a 50 IRCA
        for (double z : cda.IRCA){
            if (z >= 50){
                cda.numere = cda.numere+1;
            }
        }
        cda.motto.add("NA");
        for (int i = 0; i < cda.IRCA.size(); i++) {
            if (cda.IRCA.get(i) > 35 && cda.IRCA.get(i) <= 80) {
                cda.motto.add(cda.nivel(i, cda.Nombre));
            }
        }
        if (cda.motto.size() > 1) {
            cda.motto.remove(0);
        }
        // salidas
        for (int i = 0; i < cda.Nombre.size(); i++) {
            System.out.printf(cda.Nombre.get(i) + " " + "%.2f%n", cda.IRCA.get(i));
        } // salida Nombre y clasificacion cuerpo de agua
        System.out.printf("%.2f%n",cda.numere); // salida IRCA > 50
        for (String x: cda.motto){
            System.out.print(x+" ");
        } // salida nombres riesgo alto
        System.out.println();
        System.out.printf("%.2f%n", Collections.max(cda.IRCA)); // clasificacion IRCA mas alta

        input.close();
    }
}
