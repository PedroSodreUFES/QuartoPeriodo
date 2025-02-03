/**
 * Atividade no laboratório:
 * 
 * Crie classes Empresa, Departamento, Funcionario, 
 * FuncionarioTempoIntegral e FuncionarioTempoParcial compatíveis com a classe App fornecida.
 * 
 */

public class App {
    public static void main(String[] args) {

        Empresa cocaCola = new Empresa("Coca-Cola");
        Departamento rh = new Departamento("RH");
        Departamento vendas = new Departamento("Vendas");
        cocaCola.addDepartamento(rh);
        cocaCola.addDepartamento(vendas);

        System.out.println(cocaCola);

        Funcionario maria = new FuncionarioTempoIntegral("Maria", 12000.0f);
        rh.addFuncionario(maria);
        Funcionario pedro =  new FuncionarioTempoIntegral("Pedro", 9000.0f); 
        rh.addFuncionario(pedro);

        FuncionarioTempoParcial joao = new FuncionarioTempoParcial("João", 10000.0f);
        joao.setNumeroHorasSemanais(30);
        rh.addFuncionario(joao);

        for (Departamento d : cocaCola.getDepartamentos())
        {
            System.out.println(d.getNome());
            System.out.println("Média salarial do departamento : " + String.format("%.2f", d.getMediaSalarial()) );
            for (Funcionario f : d.getFuncionarios())
            {
                System.out.println(f);
            }
        }
        
    }
}
