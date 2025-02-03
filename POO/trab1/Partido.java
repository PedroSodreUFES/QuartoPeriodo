import java.util.Collections;
import java.util.Comparator;

public class Partido {
    private String sigla, numero;
    private int votos, votos_legenda;
    private java.util.ArrayList<Candidato> candidatos;

    public Partido(String sigla, String numero)
    {
        this.votos=0;
        this.votos_legenda=0;
        this.sigla=sigla;
        this.numero=numero;
        this.candidatos = new java.util.ArrayList<Candidato>();
    }

    public java.util.ArrayList<Candidato> getCandidatos() {
        return candidatos;
    }

    public String getNumero() {
        return numero;
    }

    public String getSigla() {
        return sigla;
    }

    public int getVotos() {
        return votos;
    }

    public int getVotos_legenda() {
        return votos_legenda;
    }

    public void addCandidato(Candidato c){
        candidatos.add(c);
    }

    public void addVotos(int votos){
        this.votos += votos;
    }

    public void addVotosLegenda(int votos)
    {
        this.votos_legenda+=votos;
    }
    // ordena candidatos do partido por voto e aniversário
    public void ordenaCandidatos() {
        Collections.sort(this.candidatos, new Comparator<Candidato>() {
            @Override
            public int compare(Candidato c1, Candidato c2) {
                // Ordenação principal: votos em ordem decrescente
                if (c2.getVotos() != c1.getVotos()) {
                    return Integer.compare(c2.getVotos(), c1.getVotos());
                }

                // Desempate: o mais velho ganha (ano)
                if (c1.getAno() != c2.getAno()) {
                    return Integer.compare(c1.getAno(), c2.getAno());
                }

                // Se o ano for igual, desempata pelo mês
                if (c1.getMes() != c2.getMes()) {
                    return Integer.compare(c1.getMes(), c2.getMes());
                }

                // Se o mês for igual, desempata pelo dia
                return Integer.compare(c1.getDia() , c2.getDia());
            }
        });
    }
}
