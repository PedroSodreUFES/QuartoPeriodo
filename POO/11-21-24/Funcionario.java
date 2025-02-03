class Funcionario
{
    private String nome;
    private float salario;

    public Funcionario(String nome, float salario)
    {
        this.nome = nome;
        this.salario = salario;
    }


    public String getNome()
    {
        return this.nome;
    }

    public float getSalario()
    {
        return this.salario;
    }

    @Override
    public String toString() {
        return "Funcionario : " + this.nome;
    }
}