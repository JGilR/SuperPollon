package Practica1;
import java.util.Hashtable;

public class TablaHash {

	/*
	 * Uso de ka clase Hastable de Java, la tabla hash alumnos usa una clave entera(key) asociada
	 * con el nombre del alumno(String), la tabla notas usa una clave String y un valor float
	 */
	
	public static void main(String[] args) {
		Hashtable<Integer, String> alumnos = new Hashtable<Integer, String>();
		
		alumnos.put(10, "Carlos");
		alumnos.put(20, "Luis");
		alumnos.put(30, "Maria");
		alumnos.put(40, "Pedro");
		alumnos.put(50, "Paula");
		alumnos.put(60, "Cristina");
		alumnos.put(70, "Javier");
		alumnos.put(80, "Laura");
		alumnos.put(90, "Fernando");
		alumnos.put(100, "Lucia");
		
		System.out.println("La tabla alumnos " + alumnos + "\n");
		System.out.println("");
		
		
		
		// tabla hash con clave de tipo String y valor de tipo float
		
		Hashtable<String, Float> notas = new Hashtable<String, Float>();
		
		notas.put("Carlos", 7.0f);
		notas.put("Paula", 7.0f);
		notas.put("Cristina", 7.0f);
		notas.put("Fernando", 7.0f);
		notas.put("Alicia", 7.0f);
		
		System.out.println("");
		System.out.println("La tabla notas " + notas + "\n");
		System.out.println("<Carlos>  " + notas.get("Carlos"));
		System.out.println("<Paula>  " + notas.get("Paula"));
		System.out.println("<Cristina>  " + notas.get("Cristina"));
		System.out.println("<Aida>  " + notas.get("Aida"));
		
	}

}
