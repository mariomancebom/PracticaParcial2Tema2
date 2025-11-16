package es.upm.aled.practicaparcial2;

public class Palindromo {

	/*
	 * Esta clase se basa en hacer en comprobar si una palabra es palindroma, es
	 * decir, si se lee igual del derechas que del reves. Para ello, tendremos que
	 * hacer un método recursivo, que devuelva true si la palabra es palindroma, y
	 * que devuelva false si no lo es
	 */
	
	public static boolean esPalindroma(String palabra) {
        if (palabra == null || palabra.isEmpty()) {
            return true;
        }

        // 1. Limpiar
        // 2. Convertir a minúsculas para que 'Oso' sea igual que 'oso'
        String palabraLimpia = palabra.toLowerCase().replaceAll("[^a-z0-9]", "");
        
        // Llamada inicial al método recursivo
        return esPalindroma(palabraLimpia, 0, palabraLimpia.length() - 1);
    }
	
	public static boolean esPalindroma(String palabra, int inicio, int fin) {
		boolean palindromo = true;
		// Caso base
		if (inicio >= fin) {
			return palindromo;
		}
		if (palabra.charAt(inicio) != palabra.charAt(fin))
			return false;
		// Paso recursivo
		return esPalindroma(palabra, inicio + 1, fin - 1);
	}

	public static void main(String[] args) {
        System.out.println("--- Prueba de Palíndromo Recursivo ---");
        System.out.println("--------------------------------------");

        String[] pruebas = {
            "oso",              // TRUE: Simple
            "Reconocer",        // TRUE: Maneja mayúsculas
            "anilina",          // TRUE: Palabra larga
            "Casa",             // FALSE: No es palíndromo
            "A",                // TRUE: Caso base (1 letra)
            "",                 // TRUE: Caso base (vacío)
            "abuela"            // FALSE: No es
        };

        for (String palabra : pruebas) {
            // Llama al método esPalindroma(String)
            boolean resultado = esPalindroma(palabra); 
            String estado = resultado ? "✅ Sí es un palíndromo" : "❌ No es un palíndromo";
            
            System.out.printf("Entrada: \"%s\" -> Resultado: %s%n", palabra, estado);
        }
    }
}