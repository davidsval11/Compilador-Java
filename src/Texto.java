
public class Texto {

    public static String[][] palabras = new String[5000][5000];
    public static String[] lineas = new String[5000];
    public static String[][] tabla = new String[5000][3];
    public static int filaT = 1;
    public static int fila = 1, columna = 1;
    public static String salida = "1 ";

    public static void limpiarMatriz() {
        for (int x = 0; x < 50; x++) {
            for (int y = 0; y < 10; y++) {
                palabras[x][y] = null;
            }
        }
    }

    public static void almacenarPalabras() {
        int linea = 1;
        String cod = "";
        String palabra = "";
        String lineacod = "";
        cod = Editor.texto();

        for (int x = 0; x < cod.length(); x++) {
            
            if (cod.charAt(x) != '\n') {
                lineacod = lineacod + cod.charAt(x);
            }
            if (cod.charAt(x) != ' ' && cod.charAt(x) != '\n') {
                palabra = palabra + cod.charAt(x);
            }

            if ((cod.charAt(x) == ' ' || cod.charAt(x) == '\n' || x == (cod.length() - 1)) && palabra != "") {
                palabra = palabra.replace("-", "");
                palabra = palabra.replace("(", "");
                palabra = palabra.replace(")", "");
                palabras[fila][columna] = palabra;
                tabla[filaT][0] = palabra;
                tabla[filaT][1] = Validacion.validarPalabra(palabra);                

                filaT++;
                palabra = "";
                columna++;
            }

            if (cod.charAt(x) == '\n') {
                lineas[fila]=lineacod;
                lineacod="";
                linea++;
                fila++;
                columna = 1;
            }
        }

    }

    public static void ImprimirMtabla() {

        for (int a = 1; a < filaT; a++) {

            Editor.columnas = new String[]{tabla[a][0], tabla[a][1]};
            Editor.modelo.addRow(Editor.columnas);
            Editor.tabla.setModel(Editor.modelo);

            
        }
    }
}
