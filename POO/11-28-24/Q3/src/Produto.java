package src;

public class Produto {
    private String nome;
    private double preco;

    public Produto(double preco, String nome){
        this.preco = preco;
        this.nome = nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }
}
