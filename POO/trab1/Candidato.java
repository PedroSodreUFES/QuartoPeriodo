public class Candidato {
    private String numero, nome, genero; 
    private int votos, federacao, eleito, dia, mes, ano;
    private Partido partido;

    public Candidato(String numero, String nome, Partido partido, String nascimento, String genero, int federacao, int eleito){
        this.numero = numero;
        this.nome = nome;
        this.partido = partido;
        String data[] = nascimento.split("/");
        this.dia = Integer.parseInt(data[0]);
        this.mes =Integer.parseInt(data[1]);
        this.ano = Integer.parseInt(data[2]);
        this.genero = genero;
        this.votos=0;
        this.federacao=federacao;
        this.eleito=eleito;
    }

    public int getVotos() {
        return votos;
    }

    public int getAno() {
        return ano;
    }

    public int getMes() {
        return mes;
    }

    public int getDia() {
        return dia;
    }

    public String getNumero() {
        return numero;
    }

    public String getNome() {
        return nome;
    }

    public Partido getPartido() {
        return partido;
    }

    public String getGenero() {
        return genero;
    }

    public int getFederacao() {
        return federacao;
    }

    public int getEleito() {
        return eleito;
    }

    public void addVotos(int votos){
        this.votos += votos;
    }

    public int getIdadeNaEleicao(int diaEleicao, int mesEleicao, int anoEleicao) {
        int idade = anoEleicao - this.ano;

        // Se o aniversário ainda não ocorreu neste ano, reduz a idade em 1
        if (mesEleicao < this.mes || (mesEleicao == this.mes && diaEleicao < this.dia)) {
            idade--;
        }

        return idade;
    }
}
