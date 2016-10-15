/**
 * Created by Madi on 10/14/16.
 */

public class Polynomial {
    private int exponent=-1;
    private int coefficient=0;
    public Polynomial(int exponent, int coefficient)
    {
        this.coefficient=coefficient;
        this.exponent=exponent;
    }
    public int getExponent()
    {
        return exponent;
    }
    public void setExponent(int exponent)
    {
        this.exponent=exponent;
    }
    public int getCoefficient()
    {
        return coefficient;
    }
    public void setCoefficient(int coefficient)
    {
        this.coefficient=coefficient;
    }

}

