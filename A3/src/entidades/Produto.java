package entidades;

public abstract class Produto {
    protected String nome;
    protected Double valor;
    protected String tipo;


    public Produto (String nome, Double valor, String tipo) {
        this.nome = nome;
        this.valor = valor;
        this.tipo = tipo;

    }
    
    
    public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Double getValor() {
		return valor;
	}


	public void setValor(Double valor) {
		this.valor = valor;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public void exibirInfo() {
        System.out.println("Nome: " +nome);
        System.out.println("Valor: " +valor);
        System.out.println("tipo do produto: " +tipo);
    }
}