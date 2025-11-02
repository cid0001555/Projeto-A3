package entidades;

public class Carta extends Produto {
	private Integer Quantidade;
	private Integer QuantidadeTotal;

	public Carta(String nome, double valor, String tipo, int QuantidadeTotal, int Quantidade) {
		super(nome, valor, tipo);
		this.Quantidade = Quantidade;
		this.QuantidadeTotal = QuantidadeTotal;
	}

	public static void Broche(Carta C) {
		if (C.getQuantidadeTotal() == C.getQuantidade()) {
			System.out.println("Coleção completa");
		} else {
			System.out.println("Coleção incompleta");
		}
	}

	public Integer getQuantidade() {
		return Quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		Quantidade = quantidade;
	}

	public Integer getQuantidadeTotal() {
		return QuantidadeTotal;
	}

	public void setQuantidadeTotal(Integer quantidadeTotal) {
		QuantidadeTotal = quantidadeTotal;
	}

	{
		System.out.println("Coleção incompleta");
	}

	@Override
	public void exibirInfo() {
		super.exibirInfo();
		System.out.println("Quantidade Total : " + QuantidadeTotal);
		System.out.println("Quantidade : " + Quantidade);
	}
}