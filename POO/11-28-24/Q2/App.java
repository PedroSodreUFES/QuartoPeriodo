public class App {
    public static void main(String[] args)
    {
        ClienteEspecial c1 = new ClienteEspecial();
        c1.depositar(1000);
        c1.sacar();

        ClienteComum c2 = new ClienteComum();
        c2.depositar(1000);
        c2.sacar();
    }
}
