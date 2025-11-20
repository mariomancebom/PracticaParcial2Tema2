package es.upm.aled.practicaparcial2;

public class PuzzleTriangular {

	/**
	 * Comprueba si un salto es legal.
	 * @param tablero La matriz actual.
	 * @param fDest   Fila de destino (donde aterriza).
	 * @param cDest   Columna de destino.
	 * @param fMedio  Fila de la ficha que saltamos (la víctima).
	 * @param cMedio  Columna de la ficha que saltamos.
	 * @return true si se puede saltar, false si no.
	 */
	public static boolean esValido(int[][] tablero, int fDest, int cDest, int fMedio, int cMedio) {
	    
	    // 1. COMPROBAR LÍMITES DE FILAS (Destino)
	    // Si la fila destino es negativa o mayor que el tamaño del tablero (4), fuera.
	    if (fDest < 0 || fDest >= tablero.length) {
	        return false;
	    }

	    // 2. COMPROBAR LÍMITES DE COLUMNAS (Destino)
	    // Importante: Como es un triángulo, cada fila tiene un largo distinto.
	    // Usamos 'tablero[fDest].length' para saber el ancho real de ESA fila.
	    if (cDest < 0 || cDest >= tablero[fDest].length) {
	        return false;
	    }
	    
	    // 3. COMPROBAR CONTENIDO (Reglas del juego)
	    // Regla A: La casilla que saltamos (medio) debe tener una ficha (1).
	    // Regla B: La casilla donde caemos (destino) debe estar vacía (0).
	    if (tablero[fMedio][cMedio] == 1 && tablero[fDest][cDest] == 0) {
	        return true;
	    }

	    // Si no se cumple lo de arriba, el movimiento no vale.
	    return false;
	}
	
	// Ahora hacemos el metodo que nos pide el ejercicio en sí
	public static boolean resolver(int[][] tablero) {
		// Si en el tablero hay un 0 es un hueco
		boolean saltos = false;
		// Representamos las posiciones del tablero con i y con j, filas y columnas
		// (1,1),(2,1),(2,2)...
		int contador = 0;
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				// Caso base, solo queda una ficha
				if (tablero[i][j] == 1) {
					contador++;
				}
			}
		}
		if (contador == 1) {
			System.out.println("Solucion encontrada");
			return true;
		}
		// definimos los movimientos que puede haber
		// 0:Der, 1:Izq, 2:Abajo-Der, 3:Abajo-Izq, 4:Arriba-Der, 5:Arriba-Izq
		// Como cada movimiento se tiene que mover dos casillas ponemos el 2 y no 1
		int[] movFil = { 0, 0, 2, 2, -2, -2 };
		int[] movCol = { 2, -2, 2, 0, 0, -2 };
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				if (tablero[i][j] == 1) {
					for (int k = 0; k < 6; k++) {
						// Calculamos la coordenada de destino
						int idest = i + movFil[k];
						int jdest = j + movCol[k];
						// Calculamos la coordenada de la que comemos
						int imedio = i + (movFil[k] / 2);
						int jmedio = j + (movCol[k] / 2);
						if (esValido(tablero, idest, jdest, imedio, jmedio) == true) {
							tablero[i][j] = 0; // Origen vacio
							tablero[imedio][jmedio] = 0; // Comida desaparece
							tablero[idest][jdest] = 1; // Destino se llena

							// Aplicamos recursividad
							if (resolver(tablero)) {
								System.out.println("Saltando de (" + i + "," + j + ") a (" + idest + "," + jdest + ")");
								return true;
							}
							// Si llegamos aquí, es que el 'if(resolver)' dio falso.
							// Tenemos que dejar el tablero como estaba para probar el siguiente k
							else {
							tablero[i][j] = 1; // Devolvemos la ficha origen
							tablero[imedio][jmedio] = 1; // Resucitamos a la comida
							tablero[idest][jdest] = 0; // Vaciamos el destino
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	
	//COMPROBACION DE QUE FUNCIONA (main proporcionado por IA)
	public static void main(String[] args) {
        // 1. Definimos el tablero inicial (Triángulo de 5 filas)
        // 1 = Ficha, 0 = Hueco
        int[][] tablero = {
            {1},
            {1, 1},
            {1, 1, 1},
            {1, 1, 1, 1},
            {1, 1, 1, 1, 1}
        };

        // 2. Ponemos el HUECO INICIAL.
        // El enunciado dice "hueco en la 2ª ficha de la 2ª fila".
        // En índices (fila, col) eso suele ser (1, 1).
        // Otro inicio clásico es el centro (2, 1). Puedes probar ambos.
        System.out.println("--- TABLERO INICIAL ---");
        tablero[1][1] = 0; // Ponemos el hueco

        System.out.println("\n--- BUSCANDO SOLUCIÓN... ---");
        
        // 3. Llamamos al algoritmo
        // (Necesitamos crear una instancia si los métodos no son estáticos, 
        // o hacerlos estáticos. Aquí los he hecho estáticos para facilitar).
        long tiempoInicio = System.currentTimeMillis();
        boolean resultado = false;
		try {
			resultado = resolver(tablero);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        long tiempoFin = System.currentTimeMillis();

        // 4. Resultado
        if (resultado) {
            System.out.println("\n¡ÉXITO! Se ha resuelto el puzle.");
            System.out.println("Tiempo de cálculo: " + (tiempoFin - tiempoInicio) + "ms");
            System.out.println("(Nota: Los movimientos de arriba se muestran en ORDEN INVERSO: del último al primero)");
        } else {
            System.out.println("\nNo se encontró solución para esta configuración inicial.");
        }
    }
	
}
