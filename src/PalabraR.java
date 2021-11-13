
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PalabraR {
    public ArrayList<variables> Tvariables = new ArrayList<>();
    public ArrayList<Condicionales> sent_condicionales = new ArrayList<>();
    Texto txt = new Texto();
    Validacion valid = new Validacion();
    int cant_ejec_mien = 0;
    int consec_tabla = 0;
    static String[] arregloPR = {
        "INICIO", //0

        "mas", //1
        "menos", //2
        "por", //3
        "divide", //4

        "escriba", //5
        "lea", //6

        "menor", //7
        "mayor", //8
        "igual", //9
        "distinto", //10
        "igual.igual", //11

        "mientras", //12
        "hacer", //13
        "masE", //14
        "si", //15

        "ent", //16
        "rflo", //17
        "cadena", //18
        "vof", //19

        "concatenar", //20
        "(", //21
        ")", //22
        
        "FIN", //23
        "y", //24
        "o", //25
        "var", //26
        "menor.igual", //27
        "mayor.igual", //28
    };

    public static int ValidarPalabraR(String palabra) {
        int rpta = -1;
        for (int x = 0; x <= 28; x++) {
            if (palabra.equals(arregloPR[x])) {
                rpta = x;
                x = 28;
            }
        }
        return rpta;
    }
    
    public static void Inicio() {

    }
    
    public void DefinirVariable(int linea) {
        
        String lincod = txt.lineas[linea];
        String[] datos_lin = lincod.split(" ");
        String[] palabras_linea = new String[datos_lin.length];
        int j=0;
        for (int i = 0; i < datos_lin.length; i++) {
            String palabra = datos_lin[i];
            if (!palabra.equals(" ")) {
                palabras_linea[j] = palabra;
                j++;
            }
        }
        String valor = "";
        if (datos_lin[1].equals("cadena")) {
            int fin = 0;
            String valor_str ="";
            for (int x = 0; x < lincod.length(); x++) {
                int ini_fin = 0;
                if (lincod.charAt(x) == '-') {
                    ini_fin++;
                }
                if (ini_fin == 1 ) {
                    valor_str = valor_str + lincod.charAt(x);
                }
                if (ini_fin == 2 ) {
                    break;
                }
            }
            valor = valor_str;
        }else{
            valor = palabras_linea[4];
        }
        variables v = new variables(palabras_linea[2], valor, palabras_linea[1]);
        
        Tvariables.add(v);
    }
            
    public String Ope_matematicas(int linea, String operador) {
        //PalabraR pr = new PalabraR();
        
        String lincod = txt.lineas[linea];
        String[] datos_lin = lincod.split("igual");
        String var = datos_lin[0];
        String exp = datos_lin[1];
        
        variables v_ini = BuscarVariable(var);
        
        String[] datos_exp = exp.split(operador);
        
        String v1 = datos_exp[0].replace("(", "");
        v1 = v1.replace(")", "");
        v1 = v1.replace(" ", "");
        
        String v2 = datos_exp[1].replace("(", "");
        v2 = v2.replace(")", "");
        v2 = v2.replace(" ", "");
        
        variables v_exp1 = BuscarVariable(v1);
        variables v_exp2 = BuscarVariable(v2);
        String tipo = "ent";
        
        
        if (v_ini== null) {
            JOptionPane.showMessageDialog(null, "La variable no fue encontrada");
            return "";
        }else{
            
            if(v_exp1 != null && v_exp2 != null){
                if (v_ini.getTipo().equals(v_exp1.getTipo()) && v_ini.getTipo().equals(v_exp2.getTipo())) {
                    tipo = v_ini.getTipo();
                }else{
                    JOptionPane.showMessageDialog(null, "Los tipos de datos no coinciden");
                    return "";
                }
            }
            
            
        }
        
        if (v_exp1 != null) {
            v1 = v_exp1.getValor();
        }
        
        if (v_exp2 != null) {
            v2 = v_exp2.getValor();
        }
        String res = "";
        switch (operador) {
            case "mas":
                if (tipo.equals("ent")) {
                    int r = Integer.parseInt(v1) + Integer.parseInt(v2);
                    res = Integer.toString(r);
                }
                if (tipo.equals("rflo")) {
                    double r = Double.parseDouble(v1) + Double.parseDouble(v2);
                    res = Double.toString(r);
                }
                break;
            case "menos":
                if (tipo.equals("ent")) {
                    int r = Integer.parseInt(v1) - Integer.parseInt(v2);
                    res = Integer.toString(r);
                }
                if (tipo.equals("rflo")) {
                    double r = Double.parseDouble(v1) - Double.parseDouble(v2);
                    res = Double.toString(r);
                }
                break;
            case "por":
                if (tipo.equals("ent")) {
                    int r = Integer.parseInt(v1) * Integer.parseInt(v2);
                    res = Integer.toString(r);
                }
                if (tipo.equals("rflo")) {
                    double r = Double.parseDouble(v1) * Double.parseDouble(v2);
                    res = Double.toString(r);
                }
                break;
            case "divide":
                if (tipo.equals("ent")) {
                    int r = Integer.parseInt(v1) / Integer.parseInt(v2);
                    res = Integer.toString(r);
                }
                if (tipo.equals("rflo")) {
                    if (Integer.parseInt(v2) == 0) {
                        JOptionPane.showMessageDialog(null, "Division entre 0");
                        return "";
                    }
                    double r = Double.parseDouble(v1) / Double.parseDouble(v2);
                    res = Double.toString(r);
                }
                break;
            default:
                break;
        }
        v_ini.setValor(res);
        return res;
    }
    
    public variables BuscarVariable(String var){
        //PalabraR pr = new PalabraR();
        var = var.trim();
                
        for(variables a: Tvariables) {
            if (a.getName().equals(var)) {
                return a;
            }
        }
        return null;
    }

    public void Escriba(int linea) {
        String lincod = txt.lineas[linea];
        String valor_str ="";
        int ini_fin = 0;

        int resultado = lincod.indexOf("concatenar");
        
        if(resultado == -1) {
            int resultado2 = lincod.indexOf("-");
            if(resultado2 != -1) {
                for (int x = 0; x < lincod.length(); x++) {
                    if (lincod.charAt(x) == '-') {
                        ini_fin++;
                    }
                    if (ini_fin == 1) {
                        valor_str = valor_str + lincod.charAt(x);
                    }

                    if (ini_fin == 2) {
                        break;
                    }
                }
            }else{
                String[] palabras_lin = lincod.split(" ");
                variables v_exp = BuscarVariable(palabras_lin[1]);
                valor_str = v_exp.getValor();
            }
        }else{
            valor_str = Concatenar(linea);
        }
        
        
        JOptionPane.showMessageDialog(null, valor_str.replaceAll("-", ""));
    }
    
    public String Concatenar(int linea) {
        String lincod = txt.lineas[linea];
        String[] partes_lin = lincod.split("concatenar");
        String lnueva = "";
        for (int i = 0; i < partes_lin.length; i++) {
            String[] pal = partes_lin[i].split(" ");
            int res_cad = partes_lin[i].indexOf("-");
            int para_conc_cad = 0;
            for (int j = 0; j < pal.length; j++) {
                String res = valid.validarPalabra(pal[j]);
                
                if (res.equals("VARIABLE")) {
                    if (res_cad == -1) {
                        variables a = BuscarVariable(pal[j]);
                        if (a != null) {
                            lnueva = lnueva + a.getValor();
                        }
                    }else{
                        if (para_conc_cad == 0) {
                            int ini_fin = 0;
                            for (int x = 0; x < partes_lin[i].length(); x++) {
                                if (partes_lin[i].charAt(x) == '-') {
                                    ini_fin++;
                                }
                                if (ini_fin == 1) {
                                    lnueva = lnueva + partes_lin[i].charAt(x);
                                }

                                if (ini_fin == 2) {
                                    para_conc_cad = 1;
                                    break;

                                }
                            }
                        }
                    }
                }
            }
        }
        return lnueva;
    }

    public void Lea(int linea) {
        String lincod = txt.lineas[linea];
        String[] pal = lincod.split(" ");
        int ini_fin = 0;
        String lnueva ="";
        for (int i = 0; i < pal.length; i++) {
            lnueva = lnueva +" ";
            for (int x = 0; x < pal[i].length(); x++) {
                if (pal[i].charAt(x) == '-') {
                    ini_fin++;
                }
                if (ini_fin == 1) {
                    lnueva = lnueva + pal[i].charAt(x);
                }

                if (ini_fin == 2) {
                    break;
                }
            }
        }
        String[] pal_var = lincod.split("&");
        
        variables var_lee = BuscarVariable(pal_var[1]);
        
        String valor = JOptionPane.showInputDialog(lnueva.replaceAll("-", ""));
	
        
        var_lee.setValor(valor);
 
    }

    public boolean Ope_relacional(int linea, String operador) {
        String lincod = txt.lineas[linea];
        
        String sentencia ="";
        int ini_fin = 0;
        
        for (int i = 0; i < lincod.length(); i++) {
            //palabra1 = palabra1 + " ";
            if (lincod.charAt(i) == '(' || lincod.charAt(i) == ')') {
                ini_fin++;
            }
            if (ini_fin == 1) {
                sentencia = sentencia + lincod.charAt(i);
            }

            if (ini_fin == 2) {
                break;
            }
        }
        
        String[] datos_sent = sentencia.split(operador);
        String tipo = "";
        String[] valor = new String[2];
        for (int i = 0; i < datos_sent.length; i++) {
            
            String dato = datos_sent[i];
            dato = dato.replaceAll("\\(", "").replaceAll("\\)",""); 
            int resultado2 = dato.indexOf("-");
            if(resultado2 != -1) {
                for (int x = 0; x < dato.length(); x++) {
                    if (dato.charAt(x) == '-') {
                        ini_fin++;
                    }
                    if (ini_fin == 1) {
                        if (dato.charAt(x) != '(' && dato.charAt(x) != ')') {
                            valor[i] = valor[i] + dato.charAt(x);
                        }
                        
                    }

                    if (ini_fin == 2) {
                        break;
                    }
                }
                tipo = "cadena";
            }else{
               
               variables var_enc = BuscarVariable(dato);
                if (var_enc == null) {
                    valor[i]= dato.trim();
                }else{
                    valor[i] = var_enc.getValor().trim();
                    tipo = var_enc.getTipo();
                }
            }
        }
        
        boolean res = false;
        switch (operador) {
            case "menor":
                if (tipo.equals("cadena")) {
                    res = false;
                }else{
                    if (tipo.equals("ent")) {
                        if (Integer.parseInt(valor[0]) < Integer.parseInt(valor[1])) {
                            res = true;
                        }else{
                            res = false;
                        }
                    }
                    if (tipo.equals("rflo") || tipo.equals("")) {
                        if (Double.parseDouble(valor[0]) < Double.parseDouble(valor[1])) {
                            res = true;
                        }else{
                            res = false;
                        }
                    }
                    
                }
                
                break;
            case "mayor":
                if (tipo.equals("cadena")) {
                    res = false;
                }else{
                    if (tipo.equals("ent")) {
                        if (Integer.parseInt(valor[0]) > Integer.parseInt(valor[1])) {
                            res = true;
                        }else{
                            res = false;
                        }
                    }
                    if (tipo.equals("rflo") || tipo.equals("")) {
                        if (Double.parseDouble(valor[0]) > Double.parseDouble(valor[1])) {
                            res = true;
                        }else{
                            res = false;
                        }
                    }
                    
                }
                break;
            case "menor.igual":
                if (tipo.equals("cadena")) {
                    res = false;
                }else{
                    if (tipo.equals("ent")) {
                        if (Integer.parseInt(valor[0]) <= Integer.parseInt(valor[1])) {
                            res = true;
                        }else{
                            res = false;
                        }
                    }
                    if (tipo.equals("rflo") || tipo.equals("")) {
                        if (Double.parseDouble(valor[0]) <= Double.parseDouble(valor[1])) {
                            res = true;
                        }else{
                            res = false;
                        }
                    }
                    
                }
                break;
            case "mayor.igual":
                if (tipo.equals("cadena")) {
                    res = false;
                }else{
                    if (tipo.equals("ent")) {
                        if (Integer.parseInt(valor[0]) >= Integer.parseInt(valor[1])) {
                            res = true;
                        }else{
                            res = false;
                        }
                    }
                    if (tipo.equals("rflo") || tipo.equals("")) {
                        if (Double.parseDouble(valor[0]) >= Double.parseDouble(valor[1])) {
                            res = true;
                        }else{
                            res = false;
                        }
                    }
                    
                }
                break;
                
            case "igual.igual":
                if (tipo.equals("cadena")) {
                    if (valor[0].equals(valor[1])) {
                        res = true;
                    }else{
                        res = false;
                    }
                    
                }else{
                    if (tipo.equals("ent")) {
                        if (Integer.parseInt(valor[0]) == Integer.parseInt(valor[1])) {
                            res = true;
                        }else{
                            res = false;
                        }
                    }
                    if (tipo.equals("rflo") || tipo.equals("")) {
                        if (Double.parseDouble(valor[0]) == Double.parseDouble(valor[1])) {
                            res = true;
                        }else{
                            res = false;
                        }
                    }
                    
                }
                break;
            case "distinto":
                if (tipo.equals("cadena")) {
                    if (!valor[0].equals(valor[1])) {
                        res = true;
                    }else{
                        res = false;
                    }
                    
                }else{
                    if (tipo.equals("ent")) {
                        if (Integer.parseInt(valor[0]) != Integer.parseInt(valor[1])) {
                            res = true;
                        }else{
                            res = false;
                        }
                    }
                    if (tipo.equals("rflo") || tipo.equals("")) {
                        if (Double.parseDouble(valor[0]) != Double.parseDouble(valor[1])) {
                            res = true;
                        }else{
                            res = false;
                        }
                    }
                }
                break;
            default:
                break;
        }
        return res ;
    }

    
    public void Mientras(int linea, int contabla) {
        String lincod = txt.lineas[linea];
        boolean res = false;
        String[] palabras = lincod.split(" ");
        for (int i = 0; i < palabras.length; i++) {
            String palabra = palabras[i];
            int key_palabra = this.ValidarPalabraR(palabra);
            if ((key_palabra >= 7 && key_palabra <= 11) || (key_palabra >= 27 && key_palabra <= 28)) {
                res = this.Ope_relacional(linea,palabra);
                break;
            }
        }
        if (cant_ejec_mien == 0) {
            this.Buscar_ini_fin(linea,"mientras",res);
        }else{
            for(Condicionales a: sent_condicionales) {
                if (a.getLinea_cond()== linea) {
                    a.setEjecutar(res);
                }
            }
        }
        
        
        cant_ejec_mien++;
        
    }

  

    

    public void Si(int linea) {
        String lincod = txt.lineas[linea];
        boolean res = false;
        String[] palabras = lincod.split(" ");
        for (int i = 0; i < palabras.length; i++) {
            String palabra = palabras[i];
            int key_palabra = this.ValidarPalabraR(palabra);
            if ((key_palabra >= 7 && key_palabra <= 11) || (key_palabra >= 27 && key_palabra <= 28)) {
                res = this.Ope_relacional(linea,palabra);
                break;
            }
        }
        
        this.Buscar_ini_fin(linea,"si",res);
    }
    
    public void MasE(int linea) {
        int linea_finsi = 0;
        for (int i = linea; i >= 1; i--) {
            if (txt.lineas[i].equals("FIN")) {
               linea_finsi = i;
               break;
            }
        }
        
        boolean ejecutar = false;
        for(Condicionales a: sent_condicionales) {
            if (a.getLinea_final() == linea_finsi) {
                ejecutar = !a.getEjecutar();
            }
        }
        Buscar_ini_fin(linea, "masE", ejecutar);
        
    }
    
    public void Buscar_ini_fin(int linea_ini, String tipo, boolean ejecutar) {
        String lineas = "";
        int linea_inicio = 0;
        int linea_fin = 0;
        for (int i = linea_ini; i < txt.lineas.length; i++) {
            if (txt.lineas[i].equals("INICIO")) {
                linea_inicio = i;
            }
            if (linea_inicio > 0) {
                lineas = lineas + i+ ",";
            }
            if (txt.lineas[i].equals("FIN")) {
                linea_fin = i;
                break;
            }
        }
        
        Condicionales c = new Condicionales(linea_ini, linea_inicio, linea_fin, lineas, tipo, ejecutar);
        sent_condicionales.add(c);
    }

    

    public static void ParentesisA(String[] linea, int i) {
        int v = 0;
        for (int x = 0; x < linea.length; x++) {

            if (linea[x].equals(")")) {
                v = 1;
                x = linea.length;

            }

        }

    }

    public static void ParentesisC(String[] linea, int i) {
        int v = 0;

        for (int x = 0; x < i; x++) {
            if (linea[x].equals("(")) {
                v = 1;
                x = linea.length;
            }

        }
    }

    public static void Fin() {

    }

}
