package src.p03.c01;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * @author Pablo Marcos Bravo
 * @since JDK 11
 * @version 2.0
 */ 

public class Parque implements IParque{

	// TODO 
	private int contadorPersonasTotales;
	private Hashtable<String, Integer> contadoresPersonasPuerta;
	
	
	public Parque() {	
		contadorPersonasTotales = 0;
		contadoresPersonasPuerta = new Hashtable<String, Integer>();
	}


	@Override
	public void entrarAlParque(String puerta){		
		if(!this.contadoresPersonasPuerta.containsKey("A"))
			this.contadoresPersonasPuerta.put("A", 0);
		else if(!this.contadoresPersonasPuerta.containsKey("B"))
			this.contadoresPersonasPuerta.put("B", 0);
		else if(!this.contadoresPersonasPuerta.containsKey("C"))
			this.contadoresPersonasPuerta.put("C", 0);
		else if(!this.contadoresPersonasPuerta.containsKey("D"))
			this.contadoresPersonasPuerta.put("D", 0);
		
		// Si no hay entradas por esa puerta, inicializamos
		if (contadoresPersonasPuerta.get(puerta) == null){
			contadoresPersonasPuerta.put(puerta, 0);
		}
		
				
		
		// Aumentamos el contador total y el individual
		contadorPersonasTotales++;		
		contadoresPersonasPuerta.put(puerta, contadoresPersonasPuerta.get(puerta)+1);
		
		// Imprimimos el estado del parque
		imprimirInfo(puerta, "Entrada");
		System.out.print("Entrada al parque por puerta " + puerta + "\n");
		System.out.print("--> Personas en el parque " + contadorPersonasTotales + "\n");
		System.out.print("----> Por puerta A " + this.contadoresPersonasPuerta.get("A") + "\n");
		System.out.print("----> Por puerta B " + this.contadoresPersonasPuerta.get("B") + "\n");
		System.out.print("----> Por puerta C " + this.contadoresPersonasPuerta.get("C") + "\n");
		System.out.print("----> Por puerta D " + this.contadoresPersonasPuerta.get("D") + "\n");
		
	
		
	}
	
	// 
	// TODO Metodo salirDelParque
	//
	
	
	private void imprimirInfo (String puerta, String movimiento){
		System.out.println(movimiento + " por puerta " + puerta);
		System.out.println("--> Personas en el parque " + contadorPersonasTotales); //+ " tiempo medio de estancia: "  + tmedio);
		
		// Iteramos por todas las puertas e imprimimos sus entradas
		for(String p: contadoresPersonasPuerta.keySet()){
			System.out.println("----> Por puerta " + p + " " + contadoresPersonasPuerta.get(p));
		}
		System.out.println(" ");
	}
	
	private int sumarContadoresPuerta() {
		int sumaContadoresPuerta = 0;
			Enumeration<Integer> iterPuertas = contadoresPersonasPuerta.elements();
			while (iterPuertas.hasMoreElements()) {
				sumaContadoresPuerta += iterPuertas.nextElement();
			}
		return sumaContadoresPuerta;
	}
	
	protected void checkInvariante() {
		assert sumarContadoresPuerta() == contadorPersonasTotales : "INV: La suma de contadores de las puertas debe ser igual al valor del contador del parte";
		
	}

	protected void comprobarAntesDeEntrar(){	// TODO
		int contador = 0;
	}

	protected void comprobarAntesDeSalir(){		// TODO
		
	}


}
