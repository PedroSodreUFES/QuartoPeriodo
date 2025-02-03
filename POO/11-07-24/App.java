public class App {
    public static void main(String[] args)
    {
        Pessoa sodras = new Pessoa("Sodras");
        Pessoa paidosodras = new Pessoa("Pai do Sodras");
        Pessoa maedosodras = new Pessoa("Mae do Sodras");
        sodras.setMae(maedosodras);
        sodras.setPai(paidosodras);
        sodras.setPai(paidosodras);
        sodras.setMae(maedosodras);
        Pessoa maedopaidosodras = new Pessoa("Avó do Sodras");
        Pessoa paidopaidosodras = new Pessoa("Avô do Sodras");
        sodras.getPai().setPai(paidopaidosodras);
        sodras.getPai().setMae(maedopaidosodras);
        for(Pessoa p : sodras.getAvos())
        {
            System.out.println(p.getNome());
        }
    }
}