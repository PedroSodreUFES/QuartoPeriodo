package src;
public class App {
    public static void main(String[] args){
        
        Livro l1 = new Livro(100, "Memórias Póstumas de Bras Cubas", "Machado de Assis");
        Livro l2 = new Livro(40, "O Alienista", "Machado de Assis");
        Livro l3 = new Livro(70, "Quincas Borba", "Machado de Assis");
        
        Jogo j1 = new Jogo(250, "Call of Duty", 18);
        Jogo j2 = new Jogo(150, "Rainbow Six Siege", 18);

        Loja e = new Loja("Loja do JP");

        e.addProductInStore(l1);
        e.addProductInStore(l2);
        e.addProductInStore(l3);
        e.addProductInStore(j1);
        e.addProductInStore(j2);

        e.printLoja();

    }
}
