package es.upm.aled.obtenerFamilia;

public class ArbolGenealogico {
    public static void main(String[] args) {
        // Nivel 0 (Raíz)
        Persona abuelo = new Persona("Abuelo Juan", 75);

        // Nivel 1 (Hijos)
        Persona padre = new Persona("Padre Carlos", 45);
        Persona tia = new Persona("Tía Ana", 40);

        abuelo.agregarHijo(padre);
        abuelo.agregarHijo(tia);

        // Nivel 2 (Nietos)
        Persona nieto1 = new Persona("Nieto Pablo", 15);
        Persona nieto2 = new Persona("Nieta Laura", 12);
        Persona primo = new Persona("Primo Marcos", 10);
        
        padre.agregarHijo(nieto1);
        padre.agregarHijo(nieto2);
        tia.agregarHijo(primo);

        // Nivel 3 (Bisnietos - Caso base de recursión)
        Persona bisnieto = new Persona("Bisnieto Lucas", 1);
        nieto2.agregarHijo(bisnieto);

        System.out.println("--- ÁRBOL GENEALÓGICO ---");
        System.out.println(abuelo.obtenerFamilia());
    }
}