package xyz.smaeul.xisalone;

import java.util.LinkedList;


/**
 * Created by Madi on 10/14/16.
 */

public class Expression {

    private LinkedList<Polynomial> polynomialList=null;
    private Polynomial denominator = new Polynomial(0, 1);

    public Expression()
    {

        polynomialList = new LinkedList<Polynomial>();
    }

    public void addPolynomial(Polynomial x)
    {
        polynomialList.add(x);
    }

    public Polynomial getDenominator(){

        return denominator;
    }

    public void setDenominator(Polynomial denominator){

        this.denominator = denominator;
    }

    public LinkedList getPolynomial()
    {
        return polynomialList;
    }

    public void multiply(Polynomial x)
    {//for loop 0 to size multiply each polynomial in the linked list by the passed polynomial
        int multCoefficient=x.getCoefficient();
        int multExponent=x.getExponent();

        for(int i=0; i<polynomialList.size(); i++)
        {
            Polynomial current = polynomialList.get(i);
            current.setCoefficient(current.getCoefficient() * multCoefficient);
            current.setExponent(current.getExponent() + multExponent);
        }

    }

    public void divide(Polynomial x)
    {     int multCoefficient=x.getCoefficient();
          int multExponent=x.getExponent();

           for(int i=0; i<polynomialList.size(); i++){

          }







    }

    public void add(Polynomial x)
    {
        int multCoefficient=x.getCoefficient();
        int multExponent=x.getExponent();
        for(int i=0; i<polynomialList.size(); i++)
        {
            Polynomial current=polynomialList.get(i);
            if(multExponent==current.getExponent())     //exponents are the same, can add coefficients
            {
                current.setCoefficient(current.getCoefficient()+multCoefficient);
            }

            else //coefficients are not equal, cant combine like terms
            {
               polynomialList.add(x);
            }
       }

   }
   public void subtract(Polynomial x)
   {
       int multCoefficient=x.getCoefficient();
       int multExponent=x.getExponent();

       for(int i=0; i<polynomialList.size();i++)
       {
           Polynomial current=polynomialList.get(i);

           if(multExponent==current.getExponent())   //if exponents are the same we can subtract like terms
           {
               current.setCoefficient(current.getCoefficient()-multCoefficient);
           }
           else   //if exponents are not like terms we cant subtract coefficients
           {
                polynomialList.add(x);
           }
       }

   }

    public void simplify()    //Simplify
    {

        if(polynomialList.size() > 0){

            int gcd = gcd(polynomialList.getFirst().getCoefficient(), denominator.getCoefficient());
            int highest_shared_exponent = polynomialList.getFirst().getExponent();

            for(int i = 1; i < polynomialList.size(); i++){

                Polynomial current = polynomialList.get(i);

                if(current.getCoefficient() == 0){

                    polynomialList.remove(i);
                }else{

                    highest_shared_exponent = current.getExponent();
                    gcd = gcd(current.getCoefficient(), gcd);
                }
            }

            for(int i = 1; i < polynomialList.size(); i++){

                Polynomial current = polynomialList.get(i);

                current.setCoefficient(current.getCoefficient() - highest_shared_exponent);
                current.setCoefficient(current.getCoefficient() / gcd);
                denominator.setCoefficient(denominator.getCoefficient() / gcd);
            }
        }
    }

    public static int gcd(int p, int q) {

        while (q != 0) {
            int temp = q;
            q = p % q;
            p = temp;
        }
        return p;
    }
}
