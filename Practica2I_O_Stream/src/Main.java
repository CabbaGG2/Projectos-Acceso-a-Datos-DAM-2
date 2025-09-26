import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String ruta1 = "/home/dam/AccesoDatos/texto1.txt";
        String ruta2 = "/home/dam/AccesoDatos/texto2.txt";
        String foto1 = "/home/dam/AccesoDatos/foto.jpg";
        String foto2 = "/home/dam/AccesoDatos/foto2.jpg";
        String foto3 = "/home/dam/AccesoDatos/foto3.jpg";
        String ruta3 = "/home/dam/AccesoDatos/texto3.txt";

        pasarTexto(ruta1,ruta2, true);

        //Copia sin BufferedStream
        Long inicio = System.currentTimeMillis();
        pasarTexto(foto1,foto2,true);
        Long fin = System.currentTimeMillis();
        System.out.println("Tiempo sin buffered: " + (fin - inicio) + " ms");

        //Copia con BufferedStream
        inicio = System.currentTimeMillis();
        copiarArchivoConBuffer(foto1,foto3,true);
        fin = System.currentTimeMillis();
        System.out.println("Tiempo con buffered: " + (fin - inicio) + " ms");

        //Tercer ejercicio
        //grabarTexto(ruta3,"o tempo non para ninguén", true);
        grabarCadenaTresVeces(ruta3,"o tempo non para ninguén");
    }

    public static void pasarTexto (String rutaOrigen, String rutaDestino, boolean append) {
        try (FileInputStream archivoOrigen = new FileInputStream(rutaOrigen);
             FileOutputStream archivoDestino = new FileOutputStream(rutaDestino, append))
        {
            int bit ;
            while ((bit = archivoOrigen.read()) != -1){
                archivoDestino.write(bit);
            }
            System.out.println("El archivo ha sido copiado con exito.");
        } catch (IOException e) {
            System.out.println("Ocurrio un error: " + e.getMessage());
        }

    }

    public static void copiarArchivoConBuffer (String rutaOrigen, String rutaDestino, boolean append) {
        try(BufferedInputStream in = new BufferedInputStream(new FileInputStream(rutaOrigen));
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(rutaDestino))) {
                int c;
                while ((c = in.read()) != -1) {
                    out.write(c);
                }
                System.out.println("El archivo se ha copiado con exito");
        } catch (FileNotFoundException e) {
            System.out.println("No se encontro el archivo.");;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*public static void grabarTexto (String ruta, String texto, boolean append) throws IOException {
        try (DataInputStream in = new DataInputStream(new FileInputStream(ruta));
             DataOutputStream out = new DataOutputStream(new FileOutputStream(ruta, append))) {
            while ((in.available()) > 0) {
                System.out.println(in.readUTF());
                out.writeUTF(texto);
            }
            System.out.println("El texto se ha grabado con exito.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    */

    public static void grabarCadenaTresVeces(String ruta, String cadena) throws IOException {
        File archivo = new File(ruta);
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(archivo,true))) {
            for (int i = 0; i < 3; i++) {
                System.out.println("escribiendo la cadena: " + cadena);
                out.writeUTF(cadena);

                System.out.println("tamaño del fichero: " + archivo.length() + " bytes");
            }
            System.out.println("tamaño final del fichero: " + archivo.length() + " bytes");
            leerCadenas(ruta);
        }
    }

    public static void leerCadenas(String ruta) throws IOException {
        File archivo = new File(ruta);
        try (DataInputStream in = new DataInputStream(new FileInputStream(archivo))) {
            for (int i = 0; i < 3; i++) {
                System.out.println("quedan: " + in.available() + " bytes por leer");
                String cadena = in.readUTF();
                System.out.println("cadena: " + cadena);
            }
            System.out.println("Ya no queda nada por leer");
        }
    }
}
