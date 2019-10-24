package Practica2;

public class TestLexicoBasico1 {

	public static void main(String[] args) {
		ComponenteLexico etiquetaLexica;
		
		String programa = "int k; for (int i=0; i<10; i=i+1) k = k * 2;";
		Lexico lexico = new Lexico(programa);
		
		int c = 0;
		System.out.println("Test lexico basico \t" + programa + "\n");
		
		do {
			etiquetaLexica = lexico.getComponenteLexico();
			System.out.println(etiquetaLexica.toString());
			c++;
		}while(!etiquetaLexica.getEtiqueta().equals("end_program"));
		
		System.out.println("\nComponentes lexicos: " + c + ", lineas: " + lexico.getLineas());

	}

}
