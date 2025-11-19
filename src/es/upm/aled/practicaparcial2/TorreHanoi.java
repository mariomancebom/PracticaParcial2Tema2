package es.upm.aled.practicaparcial2;

public class TorreHanoi {

	/*
	 * Mover N discos de la torre A a la torre C, pero solo se pueden mover con las
	 * siguientes restricciones: - Los discos se mueven de forma individual - No
	 * puede haber un disco mas ancho sobre otro menos ancho - Solo podemos mover el
	 * disco que se encuentra en la parte mas alta de la torre
	 */

	/*
	 * @param n El número de discos a mover.
	 * 
	 * @param origen La etiqueta del poste de origen (ej: "A").
	 * 
	 * @param destino La etiqueta del poste de destino (ej: "C").
	 * 
	 * @param auxiliar La etiqueta del poste auxiliar (ej: "B").
	 */
	public void resolver(int n, String origen, String destino, String auxiliar) {
		// TODO: Implementar el caso base y el paso recursivo aquí.

		// 1. Caso Base: ¿Qué pasa cuando solo queda 1 disco (n=1)?
		if (n == 1) {
			System.out.println("Mover disco 1 de " + origen + " a " + destino);
			return;
		}
		// 2. Paso Recursivo:
		// a) Mover n-1 discos del origen al auxiliar.
		// b) Mover el disco n del origen al destino.
		// c) Mover n-1 discos del auxiliar al destino.
		resolver(n - 1, origen, auxiliar, destino);
		System.out.println("Mover disco " + n + " de " + origen + " a " + destino);
		resolver(n - 1, auxiliar, destino, origen);
	}

	public static void main(String[] args) {
		TorreHanoi hanoi = new TorreHanoi();
		int numDiscos = 5;
		String origen = "A";
		String destino = "C";
		String auxiliar = "B";

		System.out.println("--- Torres de Hanói con " + numDiscos + " discos ---");
		hanoi.resolver(numDiscos, origen, destino, auxiliar);
	}
}

