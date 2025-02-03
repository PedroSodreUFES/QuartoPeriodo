import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Comparator;

public class Eleicao {
    private int vagas;
    private java.util.ArrayList<Candidato> candidatos;
    private java.util.ArrayList<Partido> partidos;
    private int dia, mes, ano;

    public Eleicao(String data) {
        this.vagas = 0;
        this.candidatos = new java.util.ArrayList<Candidato>();
        this.partidos = new java.util.ArrayList<Partido>();
        String[] data_da_eleicao = data.split("/");
        this.dia = Integer.parseInt(data_da_eleicao[0]);
        this.mes = Integer.parseInt(data_da_eleicao[1]);
        this.ano = Integer.parseInt(data_da_eleicao[2]);
    }

    // retorna ano da eleicao
    public int getAno() {
        return ano;
    }

    // retorna mes da eleicao
    public int getMes() {
        return mes;
    }

    // retorna dia da eleicao
    public int getDia() {
        return dia;
    }

    // retorna a lista de candidatos da eleicao
    public java.util.ArrayList<Candidato> getCandidatos() {
        return candidatos;
    }

    // retorna a lista de partidos da eleicao
    public java.util.ArrayList<Partido> getPartidos() {
        return partidos;
    }

    // retorna o numero de vagas da eleicao
    public int getVagas() {
        return vagas;
    }

    // adiciona candidato na eleicao
    public void addCandidato(Candidato c) {
        this.candidatos.add(c);
    }

    // checa se o partido ja existe pela sigla
    public Partido findPartidoBySigla(String sigla) {
        for (Partido p : this.getPartidos()) {
            if (p.getSigla().equals(sigla)) {
                return p;
            }
        }
        return null;
    }

    // adiciona partido na eleicao
    public void addPartido(Partido p) {
        this.partidos.add(p);
    }

    // Diz o número de candidatos eleitos/quantas vagas tem na eleição
    public void setVagas() {
        int cont = 0;
        for (Candidato c : this.candidatos) {
            if (c.getEleito() == 2 || c.getEleito() == 3) {
                cont += 1;
            }
        }
        this.vagas = cont;
    }

    // registra os partidos e candidatos na eleicao
    public void registraCandidatosEPartidos(String[] args) {
        String municipio = args[0];
        String candidatos_path = args[1];

        String line;
        String csvSeparator = ";";
        BufferedReader reader = null;

        // ler arquivo de candidatos
        try {
            reader = new BufferedReader(
                    new FileReader(candidatos_path, StandardCharsets.ISO_8859_1));

            // pular primeira linha
            reader.readLine();

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(csvSeparator);

                String codigo_municipio = data[11].replaceAll("^\"|\"$", "");
                int cargo = Integer.parseInt(data[13]);
                String numero_candidato = data[16];
                String nome_candidato = data[18].replaceAll("^\"|\"$", "");
                String partido = data[25];
                String sigla = data[26].replaceAll("^\"|\"$", "");
                int federacao = Integer.parseInt(data[28]);
                String nascimento = data[36].replaceAll("^\"|\"$", "");
                String genero = data[38];
                int eleito = Integer.parseInt(data[48]);

                // não é vereador
                if (cargo != 13) {
                    continue;
                }

                // candidatura inválida
                if (eleito == -1) {
                    Partido party = this.findPartidoBySigla(sigla);
                    if (party == null) {
                        party = new Partido(sigla, partido);
                        this.addPartido(party);
                    }
                    continue;
                }

                // não é da cidade procurada
                if (!codigo_municipio.equals(municipio)) {
                    continue;
                }

                Partido party = this.findPartidoBySigla(sigla);
                if (party == null) {
                    party = new Partido(sigla, partido);
                    this.addPartido(party);
                }
                Candidato candidato = new Candidato(numero_candidato, nome_candidato, party, nascimento, genero,
                        federacao, eleito);
                this.addCandidato(candidato);
                party.addCandidato(candidato);
            }
        } catch (Exception e) {
            System.out.println("Não foi possível ler as informações os candidatos.");
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.setVagas();
    }

    // printa o número de vagas da eleição
    public void printaNumeroDeVagas() {
        System.out.println("Número de vagas: " + this.getVagas() + "\n");
    }

    // ecnontra candiidato pelo numero
    public Candidato findCandidato(String numero) {
        for (Candidato c : this.getCandidatos()) {
            if (c.getNumero().equals(numero)) {
                return c;
            }
        }
        return null;
    }

    // checa se o partido ja existe
    public Partido findPartidoByNumber(String numero) {
        for (Partido p : this.getPartidos()) {
            if (p.getNumero().equals(numero)) {
                return p;
            }
        }
        return null;
    }

    // contabiliza os votos do partido e do candidato
    public void contabilizaVotos(String[] args) {
        String municipio = args[0];
        String secao_path = args[2];
        String line;
        String csvSeparator = ";";

        BufferedReader reader = null;
        // ler arquivo de votos
        try {
            reader = new BufferedReader(
                    new FileReader(secao_path, StandardCharsets.ISO_8859_1));

            // pular primeira linha
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(csvSeparator);

                String cargo = data[17];
                String codigo_municipio = data[13];
                int numero_candidato = Integer.parseInt(data[19]);
                int qntd_votos = Integer.parseInt(data[21]);

                // voto nao eh de vereador
                if (!cargo.equals("13")) {
                    continue;
                }

                // Voto inválido
                if (numero_candidato >= 95 && numero_candidato <= 98) {
                    continue;
                }

                // não é da cidade procurada
                if (!codigo_municipio.equals(municipio)) {
                    continue;
                }

                Candidato c = this.findCandidato(String.valueOf(numero_candidato));
                if (c != null) {
                    c.getPartido().addVotos(qntd_votos); // adiciona em votos totais do partido
                    c.addVotos(qntd_votos); // adiciona em votos do candidato
                }

                Partido p = this.findPartidoByNumber(String.valueOf(numero_candidato));
                if (p != null) {
                    p.addVotos(qntd_votos); // adiciona em votos totais do partido
                    p.addVotosLegenda(qntd_votos); // adiciona em votos de legenda do partido
                }
            }
        } catch (Exception e) {
            System.out.println("Erro de leitura dos votos.");
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // ordena candidatos e partidos por seus criterios
    public void ordenaCandidatosAndPartidos() {

        // ordena candidatos de forma geral por voto e aniversario
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
                return Integer.compare(c1.getDia(), c2.getDia());
            }
        });

        // Ordena partidos por votos e numero
        Collections.sort(partidos, new Comparator<Partido>() {
            @Override
            public int compare(Partido p1, Partido p2) {
                // Ordena por votos em ordem decrescente
                if (p2.getVotos() != p1.getVotos()) {
                    return Integer.compare(p2.getVotos(), p1.getVotos());
                }

                // Se empatar ordena por numero da sigla
                return Integer.compare(Integer.parseInt(p1.getNumero()), Integer.parseInt(p2.getNumero()));
            }
        });

        // ordena todos os candidatos dentro de um partido seguindo critério de voto e
        // aniversario
        for (Partido p : this.partidos) {
            p.ordenaCandidatos();
        }
        ;
    }

    // imprime os candidatos eleitos cumprindo a ordenação
    public void imprimeEleitos() {
        int contEleito = 0;
        System.out.println("Vereadores eleitos:");
        for (Candidato c : this.getCandidatos()) {
            if (c.getEleito() == 2 || c.getEleito() == 3) {
                contEleito++;
                System.out.print(contEleito + " - ");
                if (c.getFederacao() != -1) {
                    System.out.print("*");
                }
                System.out.print(c.getNome() + " (" + c.getPartido().getSigla() + ", "
                        + String.format("%,d", c.getVotos()) + " votos)\n");
            }

            if (contEleito == this.vagas) {
                break;
            }
        }
        System.out.print("\n");
    }

    // imprime os candidatos com mais votos
    public void imprimeMaisVotados() {
        int contEleito = 0;
        System.out.println("Candidatos mais votados (em ordem decrescente de votação e respeitando número de vagas):");
        for (Candidato c : this.getCandidatos()) {
            contEleito++;
            System.out.print(contEleito + " - ");
            if (c.getFederacao() != -1) {
                System.out.print("*");
            }
            System.out.print(c.getNome() + " (" + c.getPartido().getSigla() + ", " + String.format("%,d", c.getVotos())
                    + " votos)\n");

            if (contEleito == this.vagas) {
                break;
            }
        }
        System.out.print("\n");
    }

    // imprime os não eleitos devido a votação não ser por majoritariedade
    public void imprimeNaoEleitosDevidoANaoMajoritariedade() {

        System.out.println("Teriam sido eleitos se a votação fosse majoritária, e não foram eleitos:");
        System.out.println("(com sua posição no ranking de mais votados)");

        int contEleito = 0;

        for (Candidato c : this.getCandidatos()) {
            contEleito++;
            if (c.getEleito() < 2 || c.getEleito() > 3) {
                System.out.print(contEleito + " - ");
                if (c.getFederacao() != -1) {
                    System.out.print("*");
                }
                System.out.print(c.getNome() + " (" + c.getPartido().getSigla() + ", "
                        + String.format("%,d", c.getVotos()) + " votos)\n");
            }

            if (contEleito == this.vagas) {
                break;
            }
        }
        System.out.print("\n");
    }

    // imprime eleitos que nao estao na lista dos mais votados
    public void imprimeBeneficiados() {
        int contEleito = 0;
        int contPosicao = 0;
        System.out.println("Eleitos, que se beneficiaram do sistema proporcional:");
        for (Candidato c : this.getCandidatos()) {
            contPosicao++;
            if (c.getEleito() == 2 || c.getEleito() == 3) {
                contEleito++;
                if (contPosicao > this.vagas) {
                    System.out.print(contPosicao + " - ");
                    if (c.getFederacao() != -1) {
                        System.out.print("*");
                    }
                    System.out.print(c.getNome() + " (" + c.getPartido().getSigla() + ", "
                            + String.format("%,d", c.getVotos()) + " votos)\n");
                }
            }

            if (contEleito == this.vagas) {
                break;
            }
        }
        System.out.print("\n");
    }

    // imprime votos do partido e numero de candidatos eleitos
    public void imprimeVotosDosPartidos() {
        System.out.println("Votação dos partidos e número de candidatos eleitos:");
        int cont = 0;
        // checa dados de voto dos partidos
        for (Partido p : this.partidos) {
            cont++;
            System.out.print(cont + " - " + p.getSigla() + " - " + p.getNumero() + ", ");
            System.out.print(String.format("%,d", p.getVotos()));
            if (p.getVotos() > 1) {
                System.out.print(" votos ");
            } else {
                System.out.print(" voto ");
            }
            System.out.print("(" + String.format("%,d", p.getVotos() - p.getVotos_legenda()));
            if (p.getVotos() - p.getVotos_legenda() > 1) {
                System.out.print(" nominais e ");
            } else {
                System.out.print(" nominal e ");
            }
            System.out.print(String.format("%,d", p.getVotos_legenda()) + " de legenda), ");

            int contEleitos = 0;
            for (Candidato c : p.getCandidatos()) {
                if (c.getEleito() == 2 || c.getEleito() == 3) {
                    contEleitos++;
                }
            }
            if (contEleitos > 1) {
                System.out.print(String.format("%,d", contEleitos) + " candidatos eleitos\n");
            } else {
                System.out.print(String.format("%,d", contEleitos) + " candidato eleito\n");
            }
        }
        System.out.print("\n");
    }

    // ordena candidatos por voto, numero do partido e idade
    public void ordenaCandidatoPorVotoPartidoIdade() {
        Collections.sort(this.candidatos, new Comparator<Candidato>() {
            @Override
            public int compare(Candidato c1, Candidato c2) {
                // ordena por votos
                if (c2.getVotos() != c1.getVotos()) {
                    return Integer.compare(c2.getVotos(), c1.getVotos());
                }

                // votos iguais => ordena pela sigla do partido
                int comparacaoPartido = Integer.compare(Integer.parseInt(c1.getPartido().getNumero()),
                        Integer.parseInt(c2.getPartido().getNumero()));
                if (comparacaoPartido != 0) {
                    return comparacaoPartido;
                }

                // votos e partidos iguais => mais velho primeiro
                if (c1.getAno() != c2.getAno()) {
                    return Integer.compare(c1.getAno(), c2.getAno());
                }
                if (c1.getMes() != c2.getMes()) {
                    return Integer.compare(c1.getMes(), c2.getMes());
                }
                return Integer.compare(c1.getDia(), c2.getDia());
            }
        });
    }

    // imprime o primeiro e o ultimo candidato de um partido com mais de 0 votos.
    public void imprimePrimeiroUltimo() {
        System.out.println("Primeiro e último colocados de cada partido:");

        this.ordenaCandidatoPorVotoPartidoIdade();

        java.util.ArrayList<Partido> aux_list = new java.util.ArrayList<Partido>();
        int cont = 0;

        for (Candidato c : this.getCandidatos()) {
            if (!aux_list.contains(c.getPartido())) {
                aux_list.add(c.getPartido());
                cont++;

                // dados do primeiro
                System.out.print(cont + " - " + c.getPartido().getSigla() + " - " + c.getPartido().getNumero() + ", ");
                System.out.print(c.getNome() + " (" + c.getNumero() + ", ");
                System.out.print(String.format("%,d", c.getVotos()) + " ");
                if (c.getVotos() > 1) {
                    System.out.print("votos) / ");
                } else {
                    System.out.print("voto) / ");
                }

                // dados do ultimo
                System.out.print(c.getPartido().getCandidatos().getLast().getNome() + " ("
                        + c.getPartido().getCandidatos().getLast().getNumero() + ", ");
                System.out.print(String.format("%,d", c.getPartido().getCandidatos().getLast().getVotos()) + " ");
                if (c.getPartido().getCandidatos().getLast().getVotos() > 1) {
                    System.out.print("votos)");
                } else {
                    System.out.print("voto)");
                }
                System.out.print("\n");
            }
        }
        System.out.print("\n");
    }

    // imprime estatisticas de faixa etaria, genero e total de votos
    public void imprimeEstatisticasDosEleitos() {
        float menosde30 = 0, menosde40 = 0, menosde50 = 0, menosde60 = 0, maisde60 = 0, feminino = 0, masculino = 0;

        for (Candidato c : this.getCandidatos()) {
            if (c.getEleito() == 2 || c.getEleito() == 3) {
                if (c.getIdadeNaEleicao(this.getDia(), this.getMes(), this.getAno()) < 30) {
                    menosde30 += 1;
                } else if (c.getIdadeNaEleicao(this.getDia(), this.getMes(), this.getAno()) < 40) {
                    menosde40 += 1;
                } else if (c.getIdadeNaEleicao(this.getDia(), this.getMes(), this.getAno()) < 50) {
                    menosde50 += 1;
                } else if (c.getIdadeNaEleicao(this.getDia(), this.getMes(), this.getAno()) < 60) {
                    menosde60 += 1;
                } else {
                    maisde60 += 1;
                }

                if (c.getGenero().equals("2")) {
                    masculino += 1;
                } else if (c.getGenero().equals("4")) {
                    feminino += 1;
                }

            }
        }
        // imprime dados de idade
        System.out.println("Eleitos, por faixa etária (na data da eleição):");
        System.out.println("      Idade < 30: " + (int) menosde30 + " ("
                + String.format("%.2f", 100 * (menosde30 / (float) this.getVagas())) + "%)");
        System.out.println("30 <= Idade < 40: " + (int) menosde40 + " ("
                + String.format("%.2f", 100 * (menosde40 / (float) this.getVagas())) + "%)");
        System.out.println("40 <= Idade < 50: " + (int) menosde50 + " ("
                + String.format("%.2f", 100 * (menosde50 / (float) this.getVagas())) + "%)");
        System.out.println("50 <= Idade < 60: " + (int) menosde60 + " ("
                + String.format("%.2f", 100 * (menosde60 / (float) this.getVagas())) + "%)");
        System.out.println("60 <= Idade     : " + (int) maisde60 + " ("
                + String.format("%.2f", 100 * (maisde60 / (float) this.getVagas())) + "%)");
        System.out.print("\n");

        // imprime dados por genero
        System.out.println("Eleitos, por gênero:");
        System.out.println("Feminino: " + (int) feminino + " ("
                + String.format("%.2f", (feminino / (float) this.getVagas()) * 100).replace(".", ",") + "%)");
        System.out.println("Masculino: " + (int) masculino + " ("
                + String.format("%.2f", (masculino / (float) this.getVagas()) * 100).replace(".", ",") + "%)");
    
        System.out.print("\n");
    }

    // imprime as estatisticas de votos da eleicao
    public void imprimeEstatisticasDosVotos() {
        int votos = 0;
        int votos_legenda=0;
        for(Partido p : this.partidos){
            votos+= p.getVotos();
            votos_legenda+=p.getVotos_legenda();
        }

        System.out.println("Total de votos válidos:    " + String.format("%,d", votos));
        System.out.println("Total de votos nominais:   " + String.format("%,d", votos - votos_legenda) + " (" + String.format("%.2f", ((votos - votos_legenda) / (float) votos) * 100).replace(".", ",") + "%)");
        System.out.println("Total de votos de legenda: " + String.format("%,d", votos_legenda) + " (" + String.format("%.2f", (votos_legenda / (float) votos) * 100).replace(".", ",") + "%)");
        System.out.print("\n");
        System.out.print("\n");
    }

}
