public class App {
    public static void main(String[] args)
    {
        double v[] = new double[6];
        for (int i=0 ; i<6 ; i++)
        {
            v[i] = Double.parseDouble(args[i]);
        }
        for (int i=0 ; i<6 ; i++)
        {
            System.out.println(v[i]);
        }
        Triangulo t = new Triangulo(v);
        System.out.println(t.getPerimetro());
    }
}

