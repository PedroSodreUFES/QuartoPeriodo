class FuncionarioTempoParcial extends Funcionario
{
    private int horasSemanais;

    public FuncionarioTempoParcial(String nome, float salario)
    {
        super(nome, salario);
        this.horasSemanais= 0;
    }

    public void setNumeroHorasSemanais(int horas)
    {
        this.horasSemanais = horas;
    }

    public int getNumeroHorasSemanais()
    {
        return this.horasSemanais;
    }
}