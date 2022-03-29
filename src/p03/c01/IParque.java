package src.p03.c01;

/**
 * Parque.Comportamiento comun para todos los parques
 * 
 * @author Pablo Marcos Bravo
 * @author Jorge Fernandez Moreno
 * 
 * @since JDK 11
 * @version 2.0
 */

public interface IParque {
	
	/**
	 * Metodo que analiza las personas que entran por puerta
	 * 
	 * @param puerta del parque por la que se entra
	 */	
	public abstract void entrarAlParque(String puerta);
	
	/**
	 * Metodo que analiza las personas que salen por puerta
	 * 
	 * @param puerta por la que sale del parque
	 */
	public abstract void salirDelParque(String puerta);
}
