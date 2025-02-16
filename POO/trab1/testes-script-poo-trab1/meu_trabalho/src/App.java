//package src;

public class App {
    public static void main(String[] args) {

        Eleicao eleicao = new Eleicao(args[3]);
        
        eleicao.registraCandidatosEPartidos(args);
        
        // relatorio 1
        eleicao.printaNumeroDeVagas();
        
        eleicao.contabilizaVotos(args);
        
        eleicao.ordenaCandidatosAndPartidos();
        
        // relatorio 2
        eleicao.imprimeEleitos();
        
        // relatorio 3
        eleicao.imprimeMaisVotados();
        
        // relatorio 4
        eleicao.imprimeNaoEleitosDevidoANaoMajoritariedade();
        
        // relatorio 5
        eleicao.imprimeBeneficiados();

        // relatorio 6
        eleicao.imprimeVotosDosPartidos();

        // relatorio 7
        eleicao.imprimePrimeiroUltimo();

        // relatorio 8 e 9
        eleicao.imprimeEstatisticasDosEleitos();

        // relatorio 10
        eleicao.imprimeEstatisticasDosVotos();
    }
}
