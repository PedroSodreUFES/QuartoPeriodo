public class Triangulo {
    private Ponto pontos[];
    public Triangulo(double v[])
    {
        pontos = new Ponto[3];
        for(int i = 0, j=0; i<6; i=i+2, j++)
        {
            pontos[j] = new Ponto(v[i], v[i+1]);
        }
    }

    public double getPerimetro()
    {
        return Math.sqrt(Math.pow(pontos[0].getX()-pontos[1].getX(), 2) + Math.pow(pontos[0].getY()-pontos[1].getY(), 2)) + Math.sqrt(Math.pow(pontos[0].getX()-pontos[2].getX(), 2) + Math.pow(pontos[0].getY()-pontos[2].getY(), 2)) + Math.sqrt(Math.pow(pontos[2].getX()-pontos[1].getX(), 2) + Math.pow(pontos[2].getY()-pontos[1].getY(), 2));
    }
}
