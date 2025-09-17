import java.io.File;
import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        String rutaRealDirectorio = "/Users/nombre_usuario";
        String rutaRealFichero = "/home/dam/primerScript.py";
        String rutaFalsaFichero = "/home/dam/programa.py";
        String rutaCrearDirectorio = "/home/dam/Escritorio/practica";
        String rutaDirectorioFalso = "/home/dam/Escritorio/";
        String nombreArchivo = "practica.txt";
        String nombreArchivo1 = "practica2.txt";
        String rutaCarpetaImagenes = "/home/dam/Imágenes";

        String resultado1 = esDirectorio(rutaRealDirectorio);
        System.out.println(resultado1);

        String resultado2 = esFichero(rutaRealFichero);
        System.out.println(resultado2);
        System.out.println(esFichero(rutaFalsaFichero));

        //crearDirectorio(rutaRealDirectorio);
        crearDirectorio(rutaCrearDirectorio);


        try {
            crearFichero(rutaDirectorioFalso,nombreArchivo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        modoAcceso(rutaDirectorioFalso,nombreArchivo1);
        modoAcceso(rutaDirectorioFalso, nombreArchivo);

        calcularLongitud(rutaDirectorioFalso,nombreArchivo);

        modificarSoloLectura(rutaDirectorioFalso,nombreArchivo);

        modificarEscritura(rutaDirectorioFalso,nombreArchivo);

        borrarFichero(rutaDirectorioFalso,nombreArchivo);

        borrarDirectorio(rutaCrearDirectorio);

        mostrarContenidoRuta(rutaCarpetaImagenes, 0);
    }

    /**
     * metodo que regresa un String dependiendo si la ruta que se le pasa es real o no
     *
     * @param ruta
     * @return String
     */
    public static String esDirectorio(String ruta) {
        File archivo = new File(ruta);

        boolean respuesta = archivo.isDirectory();

        if (respuesta) {
            return "Es un directorio.";
        } else {
            return "No es un directorio";
        }

    }

    public static String esFichero(String ruta) {
        File archivo = new File(ruta);

        boolean respuesta = archivo.isFile();

        if (respuesta) {
            return "Es un archivo.";
        } else {
            return "No es un archivo";
        }
    }

    public static boolean crearDirectorio(String ruta) {
        File directorio = new File(ruta);

        if (directorio.exists() || directorio.isDirectory()) {
            System.out.println("No se ha creado el repositorio porque existe o ingresaron un formato que no es directorio.");
        } else {
            System.out.println("Se ha creado el directorio con exito.");
            return directorio.mkdirs();
        }

        return false;
    }

    public static boolean crearFichero(String nombreDirectorio, String nombreArchivo) throws IOException {
        File directorio = new File(nombreDirectorio);
        File archivo = new File(directorio, nombreArchivo);


        if (directorio.isDirectory()) {
                if (archivo.createNewFile()) {
                    System.out.println("Se ha creado el archivo con exito.");
                    return true;
                } else {
                    System.out.println("El archivo ya existe.");
                    return false;
                }
        } else {
            System.out.println("El directorio no existe");
            return false;
        }
    }

    public static void modoAcceso(String nombreDir, String nombreArchivo) {
        File dir = new File(nombreDir);
        File archivo = new File(dir, nombreArchivo);

        if (dir.isDirectory() && dir.exists()) {
            if(archivo.exists() && archivo.isFile()) {
                String mensajeLectura = (archivo.canRead()) ? "Se puede leer" : "No se puede leer";
                System.out.println(mensajeLectura);
                String mensajeEscritura = (archivo.canWrite()) ? "Se puede escribir" : "No se puede escribir";
                System.out.println(mensajeEscritura);
            }else{
                System.out.println("El archivo " + nombreArchivo + " no existe en el directorio");
            }
        } else {
            System.out.println("La ruta: " + nombreDir + " no es un directorio");
        }

    }

    public static void calcularLongitud(String nombreDirectorio, String nombreArchivo) {
        File dir = new File(nombreDirectorio);
        File archivo = new File(dir, nombreArchivo);

        if (dir.isDirectory() && dir.exists()) {
            if(archivo.exists() && archivo.isFile()) {
                System.out.println("El tamaño del archivo " + nombreArchivo + " en Bites es: " + archivo.length());
            }else{
                System.out.println("El archivo " + nombreArchivo + " no existe en el directorio");
            }
        } else {
            System.out.println("La ruta: " + nombreDirectorio + " no es un directorio");
        }
    }

    public static void modificarSoloLectura(String nombreDirectorio, String nombreArchivo) {
        File dir = new File(nombreDirectorio);
        File archivo = new File(dir, nombreArchivo);

        if (dir.isDirectory() && dir.exists()) {
            if(archivo.exists() && archivo.isFile()) {
                archivo.setReadOnly();
                System.out.println("El archivo " + nombreArchivo + " se modifico a SoloLectura con éxito");
            }else{
                System.out.println("El archivo " + nombreArchivo + " no existe en el directorio");
            }
        } else {
            System.out.println("La ruta: " + nombreDirectorio + " no es un directorio");
        }
    }

    public static void modificarEscritura(String nombreDirectorio, String nombreArchivo) {
        File dir = new File(nombreDirectorio);
        File archivo = new File(dir, nombreArchivo);

        if (dir.isDirectory() && dir.exists()) {
            if(archivo.exists() && archivo.isFile()) {
                archivo.setWritable(true);
                System.out.println("Los permisos de escritura del archivo: " + nombreArchivo + " se modificaron con éxito.");
            }else{
                System.out.println("El archivo " + nombreArchivo + " no existe en el directorio");
            }
        } else {
            System.out.println("La ruta: " + nombreDirectorio + " no es un directorio");
        }
    }

    public static void borrarFichero(String nombreDirectorio, String nombreArchivo) {

        File archivo = new File(nombreDirectorio+nombreArchivo);

        if(archivo.exists() && archivo.isFile()) {
                archivo.delete();
                System.out.println("El archivo: " + nombreArchivo + " se ha borrado con éxito.");
        }else{
                System.out.println("El archivo " + nombreArchivo + " no existe en el directorio");
        }

    }

    public static void borrarDirectorio(String nombreDirectorio) {

        File archivo = new File(nombreDirectorio);

        if(archivo.exists() && archivo.isDirectory()) {
            archivo.delete();
            System.out.println("El Directorio: " + nombreDirectorio + " se ha borrado con éxito.");
        }else{
            System.out.println("El Directorio " + nombreDirectorio + " no existe o tiene descendencia.");
        }

    }

    public static void mostrarContenidoRuta(String ruta,int nivel) {

        File directorio = new File(ruta);

        File[] lista = directorio.listFiles();

        if (directorio.exists() && directorio.isDirectory()) {

            for (File elemento : lista) {
                if (elemento.isDirectory()) {
                    System.out.println(generarSangria(nivel) + "directorio: " + elemento.getName());
                    System.out.println("-----------");
                    System.out.println(nivel);
                    mostrarContenidoRuta(elemento.toString(), nivel++);
                } else {
                    System.out.println(generarSangria(nivel) + "fichero: " + elemento.getName());
                    System.out.println("-----------");
                }
            }
        } else {
            System.out.println("El Directorio " + ruta + " no existe o no es directorio.");
        }
    }
    public static String generarSangria(int nivel) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nivel; i++) {
                sb.append("\t");
        }
        return sb.toString();
    }

}

