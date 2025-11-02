package entidades;

public final class Jogo extends Produto {
    public String desenvolvedor;
    public String genero;

    public Jogo(String nome, double valor, String tipo, String desenvolvedor, String genero) {
        super(nome, valor, tipo);
        this.desenvolvedor = desenvolvedor;
        this.genero = genero;
    }
    
    public String getDesenvolvedor() {
    	return desenvolvedor;
    }
    public String getGenero() {
    	return genero;
    }

    @Override
    public void exibirInfo() {
        super.exibirInfo();
        System.out.println("desenvolvedor: " +desenvolvedor);
        System.out.println("genero: " +genero);

    }
}