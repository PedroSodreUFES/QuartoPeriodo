package A01_30_25;

public class INEP {
    private java.util.Set<Aluno> alunos;

    public INEP()
    {
        this.alunos = new java.util.HashSet<Aluno>();
    }

    public java.util.Set<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(java.util.Set<Aluno> alunos) {
        this.alunos = alunos;
    }

    public void addAluno(Aluno aluno)
    {
        this.alunos.add(aluno);
    }
}
