package es.upm.aled.obtenerFamilia;

import java.util.ArrayList;
import java.util.List;

public class Persona {
    private String nombre;
    private int edad;
    private List<Persona> hijos; 

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
        this.hijos = new ArrayList<>();
    }

    public void agregarHijo(Persona hijo) {
        this.hijos.add(hijo);
    }

    public List<Persona> getHijos() {
        return hijos;
    }

    public String getNombre() {
        return nombre;
    }

    /* MÉTODO A IMPLEMENTAR (Y SU AUXILIAR RECURSIVO) */
       
    public String obtenerFamilia() {
        // Llama al método recursivo auxiliar, comenzando con el nivel 0
        return obtenerFamiliaRecursivo(0); 
    }

    /**
     * TODO: Implementa este método recursivo.
     * Debe devolver un String que contenga:
     * 1. El nombre de la persona actual, seguido de un salto de línea (\n).
     * 2. Todos los descendientes, cada uno con un prefijo que indica su nivel de generación.
     * @param nivel La profundidad en el árbol (0 para el objeto actual).
     * @return String con el nombre de la persona y sus descendientes.
     */
	private String obtenerFamiliaRecursivo(int nivel) {
		String sangria = "";
		for (int i = 0; i < nivel; i++) {
			sangria = sangria + "---";
		}
		String arbolGenealogico = sangria + this.nombre;
		// 1. Caso Base: ¿Qué devuelve si no tiene hijos?
		if (hijos.size() == 0) {
			return arbolGenealogico;
		}
		// 2. Paso Recursivo:
		// a) Prepara el prefijo para la persona actual (ej: "--- ")
		// b) Concatena el nombre actual.
		// c) Itera sobre los hijos y llama recursivamente a
		// obtenerFamiliaRecursivo(nivel + 1)
		// d) Concatena los resultados de las llamadas recursivas.
		for (Persona hijo : hijos) {
			String descendientes = hijo.obtenerFamiliaRecursivo(nivel + 1);
			arbolGenealogico = arbolGenealogico + "\n" + descendientes;
		}
		return arbolGenealogico;
	}
    
    @Override
    public String toString() {
        return "Persona [nombre=" + nombre + ", edad=" + edad + "]";
    }
}
