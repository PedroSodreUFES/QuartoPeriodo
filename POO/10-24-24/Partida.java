public class Partida {
    private Time timeA, timeB;

    public Partida(Time timeA, Time timeB)
    {
        this.timeA = timeA;
        this.timeB =timeB;
    }

    public Time getTimeB()
    {
        return timeB;
    }

    public Time getTimeA()
    {
        return timeA;
    }

    public void setTimeA(Time timeA)
    {
        this.timeA = timeA;
    }

    public void setTimeB(Time timeB)
    {
        this.timeB = timeB;
    }
}
