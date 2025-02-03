class FuncionarioTempoIntegral extends Funcionario
{
    public FuncionarioTempoIntegral(String nome, float salario)
    {
        super(nome, salario);
    }

    public int getNumeroHorasSemanais()
    {
        return 44;
    }
}