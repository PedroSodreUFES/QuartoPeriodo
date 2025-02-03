package A01_30_25;

import java.io.FileWriter;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        String caminho = "dados.csv";
        System.out.println("Digite 1 para criar arquivo de alunos ou Digite 2 para obter dados do arquivo:");
        Scanner sc = new Scanner(System.in);
        int escolha = sc.nextInt();
        sc.nextLine();
        if (escolha == 1) {
            INEP organizacao = new INEP();
            String nome = "algo";
            String cpf= ".";
            double nota = 0;
            try {
                FileWriter escreverArquivo = new FileWriter("./A01_30_25/dados.csv");
                escreverArquivo.write("Nome,CPF,nota\n");
                while (!nome.equals(".")) {
                    System.out.print("Digite o nome do aluno:");
                    nome = sc.nextLine();
                    System.out.print("Digite a nota do aluno:");
                    nota = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Digite o cpf do aluno:");
                    cpf = sc.nextLine();
                    Aluno a = new Aluno(nome, cpf, nota);
                    organizacao.addAluno(a);
                    escreverArquivo.write(nome + "," + cpf + "," + nota + "\n");
                }

                escreverArquivo.close();
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        else if(escolha==2){}
        else{System.out.println("Você escolheu uma opção inexistente!");}
        sc.close();
    }
}
