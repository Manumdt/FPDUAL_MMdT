package zoo;

public class Dni {
	
	private String letra;
	private String numero;
	
	public Dni() {
		super();
	}

	public Dni(String letra, String numero) {
		super();
		this.letra = letra;
		this.numero = numero;
	}

	public String getLetra() {
		return letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "Dni [letra=" + letra + ", numero=" + numero + "]";
	}
}
