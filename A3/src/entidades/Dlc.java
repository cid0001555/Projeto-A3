package entidades;

public final class Dlc extends Produto {
	public String jogoBase;
	
	public Dlc(String nome, double valor, String tipo, String jogoBase) {
		super(nome, valor, tipo);
		this.jogoBase = jogoBase;
	}
	
	public String getJogoBase() {
		return jogoBase;
	}
	@Override
	public void exibirInfo() {
		super.exibirInfo();
		System.out.println("Jogo base:" + jogoBase);
	}
}
