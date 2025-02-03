public class Pessoa
{
    private String nome;
    private Pessoa pai, mae;

    public Pessoa(String nome)
    {
        this.nome = nome;
    }

    public void setPai(Pessoa pai)
    {
        this.pai = pai;
    }

    public void setMae(Pessoa mae)
    {
        this.mae = mae;
    }

    public Pessoa getPai()
    {
        return this.pai;
    }

    public Pessoa getMae()
    {
        return this.mae;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getNome()
    {
        return this.nome;
    }

    public Pessoa getAvóMaterna()
    {
        return this.getMae().getMae();
    }

    public Pessoa getAvóPaterna()
    {
        return this.getPai().getMae();
    }

    public Pessoa getAvôMaterno()
    {
        return this.getMae().getPai();
    }

    public Pessoa getAvôPaterno()
    {
        return this.getPai().getPai();
    }

    public java.util.Set<Pessoa> getAvos()
    {
        java.util.Set<Pessoa> avós = new java.util.HashSet<Pessoa>();
        if (this.getMae() != null)
        {
            if (this.getAvóMaterna() != null) avós.add(this.getAvóMaterna());
            if (this.getAvôMaterno() != null) avós.add(this.getAvôMaterno());   
        }
        if (this.getPai() != null)
        {
            if (this.getAvóPaterna() != null) avós.add(this.getAvóPaterna());
            if (this.getAvôPaterno() != null) avós.add(this.getAvôPaterno());
        }
        return avós;
    }
}