import java.util.Scanner;

public abstract class Cliente {
    private double conta;

    public Cliente()
    {
        this.conta = 0;
    }

    public void setConta(double conta) {
        this.conta = conta;
    }

    public double getConta() {
        return conta;
    }

    public abstract double getEspecialidade();

    public double sacar()
    {
        System.out.print("Digite um valor a ser sacado: ");
        Scanner sc = new Scanner(System.in);
        double valor = sc.nextDouble();
        if (podeSacar(valor) == 1)
        {
            setConta(this.getConta() - valor*this.getEspecialidade());
            System.out.println("Sacando o valor R$: " + Double.toString(valor));
            System.out.println("Conta atual: R$: " + this.conta);
            return valor;
        }
        else
        {
            System.out.println("Você não pode sacar esse valor");
            return 0;
        }
    }

    public int podeSacar(double value)
    {
        if(this.conta >= value*getEspecialidade())
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    public void depositar(double x)
    {
        this.conta+=x;
        System.out.println("Depositando R$: " + this.conta);
    }

}
