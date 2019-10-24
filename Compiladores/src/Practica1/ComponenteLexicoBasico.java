package Practica1;

public class ComponenteLexicoBasico {

	private String etiqueta;
	private String valor;
	
	public ComponenteLexicoBasico(String etiqueta) {
		this.etiqueta = etiqueta;
		this.valor = "";
	}
	
	public ComponenteLexicoBasico(String etiqueta, String valor) {
		this.etiqueta = etiqueta;
		this.valor = valor;
	}
	
	public String getEtiqueta() {
		return etiqueta;
	}

	public String toString() {
		if(this.valor.length() == 0)
			return "<" + this.etiqueta + ">";
		else
			return "<" + this.etiqueta + ">" + "<" + this.valor + ">";
	}

}
