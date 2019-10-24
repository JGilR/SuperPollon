package Practica1;
import java.io.Externalizable;
import java.io.File;
import java.util.Hashtable;

public class LexicoBasico {

	private Hashtable<String, String> palabrasreservadas;
	private int posicion;
	private int lineas;
	private char caracter;
	private String programa;

	public LexicoBasico(String programa) {
		this.posicion=0;
		this.lineas =1;
		this.palabrasreservadas= new Hashtable<String,String>();
		this.palabrasreservadas.put("break", "break");
		this.palabrasreservadas.put("do", "do");
		this.palabrasreservadas.put("else", "else");
		this.palabrasreservadas.put("float", "float");
		this.palabrasreservadas.put("for", "for");
		this.palabrasreservadas.put("if", "if");
		this.palabrasreservadas.put("int", "int");
		this.palabrasreservadas.put("while", "while");
		this.programa = programa + "#";
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
	public ComponenteLexicoBasico getComponenteLexico() {
		
		/*
		 * El analizador lexico descarta los espacios en blanco(32), los tabuladores(9),
		 * y los saltos de linea(10 y 13).
		 * 
		 */
		
		while(true) {
			this.caracter = extraeCaracter();

			if(this.caracter == ' ' || (int)this.caracter == 9 || (int)this.caracter ==10)
				continue;
			else if((int) this.caracter ==13)
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

			devuelveCaracter();

			return new ComponenteLexicoBasico("integer", Integer.parseInt(numero) + "");
		}
		
		// Identificadores y palabras reservadas
		
		if(Character.isLetter(this.caracter)) {
			String lexema= "";

			do {
				lexema = lexema +this.caracter;
				this.caracter = extraeCaracter();
				
			}while(Character.isLetterOrDigit(this.caracter));
			
			devuelveCaracter();

			if(this.palabrasreservadas.containsKey(lexema))
				return new ComponenteLexicoBasico((String)this.palabrasreservadas.get(lexema));
			else
				return new ComponenteLexicoBasico("id", lexema);
		}

		switch(this.caracter) {
		case '&':
			if(this.extraeCaracter('&'))
				return new ComponenteLexicoBasico("and");
			else
				return new ComponenteLexicoBasico("bitwise_and");

		case '|':
			if(this.extraeCaracter('|'))
				return new ComponenteLexicoBasico("or");
			else
				return new ComponenteLexicoBasico("bitwise_or");

		case '=':
			if(this.extraeCaracter('='))
				return new ComponenteLexicoBasico("equals");
			else
				return new ComponenteLexicoBasico("asiggnment");
			
		case '<':
			if(this.extraeCaracter('='))
				return new ComponenteLexicoBasico("less_equals");
			else
				return new ComponenteLexicoBasico("less_than");

		case '>':
			if(this.extraeCaracter('='))
				return new ComponenteLexicoBasico("greater_equals");
			else
				return new ComponenteLexicoBasico("greater_than");

		case ';':
			return new ComponenteLexicoBasico("sc");

		case '{':
			return new ComponenteLexicoBasico("open_bracket");

		case '}':
			return new ComponenteLexicoBasico("closed_bracket");
			
		case '(':
			return new ComponenteLexicoBasico("open_parenthesis");
			
		case ')':
			return new ComponenteLexicoBasico("closed_parenthesis");
			
		case '[':
			return new ComponenteLexicoBasico("open_square_bracket");
			
		case ']':
			return new ComponenteLexicoBasico("closed_square_bracket");
			
		case '#':
			return new ComponenteLexicoBasico("end_program");
			
		case '-':
			return new ComponenteLexicoBasico("substract");
			
		case '+':
			return new ComponenteLexicoBasico("add");
			
		case '*':
			return new ComponenteLexicoBasico("multiply");
			
		case '/':
			return new ComponenteLexicoBasico("divide");

		default: 
			return new ComponenteLexicoBasico("Error");


		}

	}


}