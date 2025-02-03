class Empresa
{
    private String nome;
    private java.util.Set<Departamento> departamentos;

    public Empresa(String nome)
    {
        this.nome = nome;
        this.departamentos = new java.util.HashSet<Departamento>();
    }

    public void addDepartamento(Departamento departamento)
    {
        this.departamentos.add(departamento);
    }

    @Override
    public String toString() {
        return "Empresa : " + this.nome;
    }


    public java.util.Set<Departamento> getDepartamentos()
    {
        return this.departamentos;
    }

}