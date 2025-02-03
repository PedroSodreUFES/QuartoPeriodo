public class Time
{
    private String nome;
    private int nMundiais;
    public Time(String nome)
    {
        this.nome = nome;
    }
    public void setNome(String nome)
    {
        this.nome = nome;
    }
    public String getNome()
    {
        return this.nome;
    }
    public void incnMundiais()
    {
        this.nMundiais++;
    }
    public int getnMundiais()
    {
        return this.nMundiais;
    }
}