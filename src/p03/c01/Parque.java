package src.p03.c01;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * @author Jorge Fernandez Moreno
 * @author Pablo Marcos Bravo
 * @since JDK 11
 * @version 2.0
 */

public class Parque implements IParque {

	private int Max_personas;
	private int contadorPersonasTotales;
	private Hashtable<String, Integer> contadoresPersonasPuerta;

	/**
	 * Constructor de parque
	 */
	public Parque(int maximo) {
		Max_personas = maximo;
		contadorPersonasTotales = 0;
		contadoresPersonasPuerta = new Hashtable<String, Integer>();
	}

	/**
	 * Metodo que analiza las personas que entran por puerta
	 */
	@Override
	public synchronized void entrarAlParque(String puerta) {
		// Si no hay entradas por esa puerta, inicializamos
		if (contadoresPersonasPuerta.get(puerta) == null) {
			contadoresPersonasPuerta.put(puerta, 0);
		}

		try {
			this.comprobarAntesDeEntrar();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Aumentamos el contador total y el individual
		contadorPersonasTotales++;
		contadoresPersonasPuerta.put(puerta, contadoresPersonasPuerta.get(puerta) + 1);

		// Imprimimos el estado del parque
		imprimirInfo(puerta, "Entrada");
		checkInvariante();
		notifyAll();
	}

	/**
	 * Metodo que analiza las personas que salen por puerta *
	 */
	public synchronized void salirDelParque(String puerta) {

		// Si no hay salidas por esa puerta, inicializamos
		if (contadoresPersonasPuerta.get(puerta) == null) {
			contadoresPersonasPuerta.put(puerta, 0);
		}

		try {
			this.comprobarAntesDeSalir();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Disminuimos el contador total y el individual
		contadorPersonasTotales--;
		contadoresPersonasPuerta.put(puerta, contadoresPersonasPuerta.get(puerta) - 1);

		// Imprimimos el estado del parque
		imprimirInfo(puerta, "Salida");
		checkInvariante();
		notifyAll();
	}

	/**
	 * Metodo que muestra por pantalla las personas y las puertas del parque
	 * 
	 * @param puerta     del parque
	 * @param movimiento
	 */
	private void imprimirInfo(String puerta, String movimiento) {
		System.out.println(movimiento + " por puerta " + puerta);
		System.out.println("--> Personas en el parque " + contadorPersonasTotales); // + " tiempo medio de estancia: " +
																					// tmedio);

		// Iteramos por todas las puertas e imprimimos sus entradas
		for (String p : contadoresPersonasPuerta.keySet()) {
			System.out.println("----> Por puerta " + p + " " + contadoresPersonasPuerta.get(p));
		}
		System.out.println(" ");
	}

	/**
	 * Aumenta las personas por puerta
	 * 
	 * @return numero de personas en puerta
	 */
	private int sumarContadoresPuerta() {
		int sumaContadoresPuerta = 0;
		Enumeration<Integer> iterPuertas = contadoresPersonasPuerta.elements();
		while (iterPuertas.hasMoreElements()) {
			sumaContadoresPuerta += iterPuertas.nextElement();
		}
		return sumaContadoresPuerta;
	}

	protected void checkInvariante() {
		assert sumarContadoresPuerta() == contadorPersonasTotales
				: "INV: La suma de contadores de las puertas debe ser igual al valor del contador del parte";
		assert contadorPersonasTotales <= Max_personas : "INV: Hay m�s personas que capacidad";
		assert contadorPersonasTotales >= 0 : "INV: No hay valores negativos";
	}

	/**
	 * Metodo que comprueba que la persona pueda entrar
	 * 
	 * @throws InterruptedException
	 */
	protected synchronized void comprobarAntesDeEntrar() throws InterruptedException {
		while (contadorPersonasTotales == Max_personas) {
			wait();
		}
	}

	/**
	 * Metodo que comprueba que la persona pueda salir
	 * 
	 * @throws InterruptedException
	 */
	protected synchronized void comprobarAntesDeSalir() throws InterruptedException {
		while (contadorPersonasTotales == 0) {
			wait();
		}
	}

}
