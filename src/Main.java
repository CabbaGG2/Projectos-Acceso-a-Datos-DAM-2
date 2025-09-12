import java.io.File;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        String rutaRealDirectorio = "/Users/nombre_usuario";
        String rutaRealFichero = "/Users/home/dam/primerScript.py";

        String resultado1 = esDirectorio(rutaRealDirectorio);
        System.out.println(resultado1);

        String resultado2 = esFichero(rutaRealFichero);
        System.out.println(resultado2);
    }

    /**
     * metodo que regresa un String dependiendo si la ruta que se le pasa es real o no
     * @param ruta
     * @return String
     */
    public static String esDirectorio(String ruta) {
        File archivo = new File(ruta);

        boolean respuesta = archivo.isDirectory();

        if (respuesta){
            return "Es un directorio.";
        } else {
            return  "No es un directorio";
        }

    }

    public static String esFichero(String ruta) {
        File archivo = new File(ruta);

        boolean respuesta = archivo.isFile();

        if (respuesta){
            return "Es un archivo.";
        } else {
            return  "No es un archivo";
        }
    }

    public static void crearDirectorio(String ruta) {
        File directorio = new File (ruta);

        if (directorio.exists() || !directorio.isDirectory()) {
            System.out.println("No se ha creado el repositorio porque existe o ingresaron un formato que no es directorio.");
        } else {
            return directorio.mkdirs();
            System.out.printf("Se ha creado el directorio con exito.");
        }
    }

}
