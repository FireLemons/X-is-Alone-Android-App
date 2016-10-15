package xyz.smaeul.xisalone;

import java.util.LinkedList;


/**
 * Created by Madi on 10/14/16.
 */

public class Expression {

    private LinkedList<Polynomial> polynomialList=null;
    private Polynomial demoninator = new Polynomial(0, 1);

    public Expression()
    {

    }

    public void addPolynomial(Polynomial x)
    {
        polynomialList.add(x);
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

    public void Simplify()    //Simplify
    {

    }
}
