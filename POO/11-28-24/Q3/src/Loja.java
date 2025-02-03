package src;

public class Loja {
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    private java.util.Set<Produto> produtos;

    public Loja(String nome)
    {
        this.produtos = new java.util.HashSet<Produto>();
        this.nome = nome;
    }

    public void addProductInStore(Produto p)
    {
        this.produtos.add(p);
    }

    public java.util.Set<Produto> getProdutos() {
        return this.produtos;
    }

    public void printLoja()
    {
        System.out.println("Resumo da empresa: "+getNome()+"\n");
        
        for(Produto p : this.getProdutos())
        {
            System.out.println(p);
        }
    }
}
