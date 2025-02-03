public class App {
    public static void main(String[] args)
    {
        Pessoa sodras = new Pessoa("Sodras");
        Pessoa paidosodras = new Pessoa("Pai do Sodras");
        Pessoa maedosodras = new Pessoa("Mae do Sodras");
        sodras.setMae(maedosodras);
        sodras.setPai(paidosodras);
        Pessoa maedopaidosodras = new Pessoa("Avó Paterna do Sodras");
        Pessoa paidopaidosodras = new Pessoa("Avô Paterno do Sodras");
        Pessoa paidopaidopaidosodras = new Pessoa("Pai do Avô Paterno do Sodras");
        paidopaidosodras.setPai(paidopaidopaidosodras);
        paidopaidopaidosodras.setPai(sodras);
        sodras.getPai().setPai(paidopaidosodras);
        sodras.getPai().setMae(maedopaidosodras);
        java.util.Set<Pessoa> parentes = new java.util.HashSet<Pessoa>();
        parentes = sodras.getParentes();
        for(Pessoa p : parentes)
        {
            System.out.println(p.getNome());
        }
    }
}