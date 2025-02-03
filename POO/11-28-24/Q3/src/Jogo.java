package src;

public class Jogo extends Produto
{
    private int idade;

    public Jogo(double preco, String nome, int idade)
    {
        super(preco, nome);
        this.idade = idade;
    }
    
    public int getIdade() {
        return idade;
    }
    
    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public String toString()
    {
        return "Jogo: "+ getNome() + "\n" + "Para maiores de " + getIdade() + " anos\n" + "Preço: R$" + getPreco() + "0\n";
    }
}
