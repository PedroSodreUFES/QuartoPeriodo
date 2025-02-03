package A01_30_25;

public class Aluno {
    private String nome, cpf;
    private double nota;

    public Aluno(String nome, String cpf, double nota)
    {   
        this.nome=nome;
        this.cpf=cpf;
        this.nota=nota;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public double getNota() {
        return nota;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
}
