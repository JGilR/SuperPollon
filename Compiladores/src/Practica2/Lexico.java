package Practica2;
import java.io.Externalizable;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Hashtable;

public class Lexico {

	private PalabrasReservadas palabrasreservadas;
	private int posicion;
	private int lineas;
	private char caracter;
	private String programa;

	public Lexico(String programa) {
		this.posicion=0;
		this.lineas =1;
		this.palabrasreservadas = new PalabrasReservadas("lexemas.txt");
		this.programa = programa + this.palabrasreservadas.getLexema("end_program");
	}
	
	private static boolean existeFichero(String fichero) {
		File ficheroEntrada = new File(fichero);
		return ficheroEntrada.exists();
	}
	
	private static String contenidoFichero(String fichero, Charset codificacion) {
		String s = null;
		
		if(existeFichero(fichero)) {
			try {
				byte [] contenido = Files.readAllBytes(Paths.get(fichero));
				
				s = new String(contenido, codificacion);
			} catch (IOException e) {}
		}
		return s;
	}
	
	public Lexico(String ficheroEntrada, Charset codificacion) {
		this(contenidoFichero(ficheroEntrada, codificacion));
	}
	
	private char extraeCaracter() {
		return this.programa.charAt(this.posicion++);
	}

	private boolean extraeCaracter(char c) {
		if(this.posicion < this.programa.length()-1) {
			this.caracter = extraeCaracter();
			if(c == this.caracter) {
				return true;
			}else {
				devuelveCaracter();
				return false;
			}
		}
		else {
			return false;
		}
	}
	private void devuelveCaracter() {
		this.posicion--;
	}

	public int getLineas() {
		return this.lineas;
	}
	public ComponenteLexico getComponenteLexico() {
		
		/*
		 * El analizador lexico descarta los espacios en blanco(32), los tabuladores(9),
		 * y los saltos de linea(10 y 13).
		 * 
		 */
		
		while(true) {
			this.caracter = extraeCaracter();

			if(this.caracter == ' ' || (int)this.caracter == 9 || (int)this.caracter ==13)
				continue;
			else if((int) this.caracter ==10)
				this.lineas++;
			else
				break;
		}

		// Secuencias de digitos de numeros enteros o reales
		
		if(Character.isDigit(this.caracter)) {
			String numero = "";

			do {
				numero = numero + this.caracter;
				this.caracter = extraeCaracter();
				
			}while(Character.isDigit(this.caracter));
			
			if(this.caracter != '.') {
				devuelveCaracter();

				return new ComponenteLexico("integer", Integer.parseInt(numero) + "");
			}
			
			do {
				numero = numero + this.caracter;
				
				this.caracter = extraeCaracter();
			}while(Character.isDigit(this.caracter));

			devuelveCaracter();

			return new ComponenteLexico("float", Float.parseFloat(numero) + "");
		}
		
		// Identificadores y palabras reservadas
		
		if(Character.isLetter(this.caracter)) {
			String lexema= "";

			do {
				lexema = lexema +this.caracter;
				this.caracter = extraeCaracter();
				
			}while(Character.isLetterOrDigit(this.caracter));
			
			devuelveCaracter();
			
			String etiqueta = palabrasreservadas.getEtiquetaLexica(lexema);

			if(etiqueta != null)
				return new ComponenteLexico(etiqueta);
			else
				return new ComponenteLexico("id", lexema);
		}

		switch(this.caracter) {
		case '&':
			if(this.extraeCaracter('&'))
				return new ComponenteLexico("and");
			else
				return new ComponenteLexico("bitwise_and");

		case '|':
			if(this.extraeCaracter('|'))
				return new ComponenteLexico("or");
			else
				return new ComponenteLexico("bitwise_or");

		case '=':
			if(this.extraeCaracter('='))
				return new ComponenteLexico("equals");
			else
				return new ComponenteLexico("asiggnment");
			
		case '<':
			if(this.extraeCaracter('='))
				return new ComponenteLexico("less_equals");
			else
				return new ComponenteLexico("less_than");

		case '>':
			if(this.extraeCaracter('='))
				return new ComponenteLexico("greater_equals");
			else
				return new ComponenteLexico("greater_than");

		case ';':
			return new ComponenteLexico("sc");

		case '{':
			return new ComponenteLexico("open_bracket");

		case '}':
			return new ComponenteLexico("closed_bracket");
			
		case '(':
			return new ComponenteLexico("open_parenthesis");
			
		case ')':
			return new ComponenteLexico("closed_parenthesis");
			
		case '[':
			return new ComponenteLexico("open_square_bracket");
			
		case ']':
			return new ComponenteLexico("closed_square_bracket");
			
		case '#':
			return new ComponenteLexico("end_program");
			
		case '-':
			return new ComponenteLexico("substract");
			
		case '+':
			return new ComponenteLexico("add");
			
		case '*':
			return new ComponenteLexico("multiply");
			
		case '/':
			return new ComponenteLexico("divide");

		default: 
			return new ComponenteLexico("Error");


		}

	}


}