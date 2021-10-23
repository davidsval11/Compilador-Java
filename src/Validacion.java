/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Oscar
 */
public class Validacion {
    
       public static String validarPalabra(String palabra) {
           palabra = palabra.trim();
        if (PalabraR.ValidarPalabraR(palabra) > -1) {

            if (PalabraR.ValidarPalabraR(palabra) == 0) {
               return "RESERVADA";
            }
            if (PalabraR.ValidarPalabraR(palabra) >= 1 && PalabraR.ValidarPalabraR(palabra) <= 4) {
               return "OPERADOR ARITMETICO";
            }else if (PalabraR.ValidarPalabraR(palabra) >= 7 && PalabraR.ValidarPalabraR(palabra) <= 11) {
                return "OPERADOR RELACIONAL";
            }else if (PalabraR.ValidarPalabraR(palabra) >= 27 && PalabraR.ValidarPalabraR(palabra) <= 28) {
                return "OPERADOR RELACIONAL";
            }else if (PalabraR.ValidarPalabraR(palabra) == 20) {
                return "OPERADOR DE CONCATENACION";    
            }else if (PalabraR.ValidarPalabraR(palabra) == 21) {
                return "OPERADOR DE SINTAXIS";  
            }else if (PalabraR.ValidarPalabraR(palabra) >= 24 && PalabraR.ValidarPalabraR(palabra) <= 25) {
                return "OPERADOR LOGICO";
            }else{
                return "RESERVADA";
            }
        }
        
        return "VARIABLE";
    }
}
