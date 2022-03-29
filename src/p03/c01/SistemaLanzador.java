package src.p03.c01;

/**
 * Clase que lanza por pantalla la salida del programa
 * 
 * @author Pablo Marcos Bravo
 * @author Jorge Fernandez Moreno
 * 
 * @since JDK 11
 * @version 2.0
 */

public class SistemaLanzador {
	public static void main(String[] args) {

		IParque parque = new Parque(50);
		char letra_puerta = 'A';

		System.out.println("¡Parque abierto!");

		for (int i = 0; i < Integer.parseInt(args[0]); i++) {

			String puerta = "" + ((char) (letra_puerta++));

			// Creacion de hilos de entrada
			ActividadEntradaPuerta entradas = new ActividadEntradaPuerta(puerta, parque);
			new Thread(entradas).start();

			// Creacion de hilos de salida
			ActividadSalidaPuerta salidas = new ActividadSalidaPuerta(puerta, parque);
			new Thread(salidas).start();

		}
	}
}