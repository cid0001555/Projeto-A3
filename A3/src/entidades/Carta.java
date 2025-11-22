package entidades;

public class Carta extends Produto {

	private String raridade;

	public Carta(String nome, double valor, String tipo, String raridade) {
		super(nome, valor, tipo);
		this.raridade = raridade;
	}

	public String getRaridade() {
		return raridade;
	}

	public void setRaridade(String raridade) {
		this.raridade = raridade;
	}

	@Override
	public void exibirInfo() {
		super.exibirInfo();
		System.out.println("Raridade:" + raridade);
	}

}
