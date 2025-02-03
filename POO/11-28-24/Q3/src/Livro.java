package src;

public class Livro extends Produto{
    private String autor;

    public Livro(double preco, String nome, String autor)
    {
        super(preco, nome);
        this.autor=autor;
    }
    
    public String getAutor() {
        return autor;
    }
    
    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String toString()
    {
        return "Livro: " + getNome() + "\n" + "Autor: "+ getAutor() + "\n" + "Preço: R$" + getPreco() + "0\n";
    }
}
