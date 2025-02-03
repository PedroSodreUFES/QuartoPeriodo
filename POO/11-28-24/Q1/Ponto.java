public class Ponto{
    private double x , y;

    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }

    public Ponto(double x, double y)
    {
        setY(y);
        setX(x);
    }

    public double getDist(Ponto p1, Ponto p2)
    {
        double dist = Math.sqrt(Math.pow(p1.getX()-p2.getX(), 2) + Math.pow(p1.getY()-p2.getY(), 2));
        return dist;
    }
}