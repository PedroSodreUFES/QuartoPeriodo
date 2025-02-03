class Departamento
{
    private String nome;
    private java.util.Set<Funcionario> funcionarios;

    public Departamento(String nome)
    {
        this.nome = nome;
        this.funcionarios = new java.util.HashSet<Funcionario>();
    }

    public void addFuncionario(Funcionario funcionario)
    {
        this.funcionarios.add(funcionario);
    }

    
    public String getNome()
    {
        return "Departamento : " + this.nome;
    }

    public java.util.Set<Funcionario> getFuncionarios()
    {
        return this.funcionarios;
    }

    public float getMediaSalarial()
    {
        float soma = 0, n=0;
        for (Funcionario f : this.funcionarios)
            {
                soma = soma + f.getSalario();
                n+=1;
            }

        if (this.funcionarios.size() > 0 )return soma/n;
        else return 0;
    }




}