import java.util.*;

public class App{
    public static void main(String[] args){
        // sem importar lista:   java.util.LinkedList<Partida> partidas = new java.util.LinkedList<Partida>();
        LinkedList<Partida> partidas = new LinkedList<Partida>();

        Time flamengo = new Time("Flamengo");
        Time vasco = new Time("Vasco");

        Partida p = new Partida(flamengo, vasco);
        Partida p2 = new Partida(flamengo, vasco);
        partidas.add(p);
        partidas.add(p2);
        System.out.println(partidas.size());

        for(Partida j : partidas)
        {
            System.out.print(j.getTimeA().getNome());
            System.out.print(" x ");
            System.out.print(j.getTimeB().getNome());
            System.out.print("\n");
        }

        flamengo.incnMundiais();
        System.out.println("O " + flamengo.getNome() + " tem " + flamengo.getnMundiais() + " mundial");
    }
}


/*

SEI LA UNS CODIGOS ALEATORIOS QUE TEVE NA AULA

        Time flamengo = new Time("Mengoooooooooo");
        System.out.println(flamengo.getNome());
        flamengo.setNome("Flamengo");
        System.out.println(flamengo.getNome());
        flamengo.setNome("Vascaoooooooooooo");
        System.out.println(flamengo.getNome());
        System.out.println(flamengo.getnMundiais());
        flamengo.incnMundiais();
        System.out.println(flamengo.getnMundiais());
 */

/*
        System.out.println("Hello!");
        if(args.length >= 1)
        {
            System.out.println("Boa cria");
        }
        else
        {
            System.out.println("Malz cria");
        }
*/