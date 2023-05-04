package zoo;

public class Dni {
	
	private String letra;
	private long numero;
	
	public Dni() {
		super();
	}

	public Dni(String letra, long numero) {
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

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "Dni [letra=" + letra + ", numero=" + numero + "]";
	}
}
