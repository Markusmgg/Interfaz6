package Interfaz;

public class Modelo{

	   
	   public Modelo(){
	      listaPersonas = new ArrayList<Persona>;
	   }
	 
	 
	 
	   public ArrayList<Persona> obtenerPersonas(){
	      return listaPersonas;
	   }
	 
	 
	 
	   public void altaPersona(String nombre, int edad){
	       Persona nuevaPersona = new Persona(nombre, edad);
	       listaPersonas.add(nuevaPersona);
	   }
	 

	 
	}