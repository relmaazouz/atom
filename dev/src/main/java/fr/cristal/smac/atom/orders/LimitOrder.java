/********************************************************** 
ATOM : ArTificial Open Market

Author  : P Mathieu, O Brandouy, Y Secq, Univ Lille, France
Email   : philippe.mathieu@lifl.fr
Address : Philippe MATHIEU, CRISTAL, UMR CNRS 9189, 
          Lille  University
          59655 Villeneuve d'Ascq Cedex, france
Date    : 14/12/2008

***********************************************************/

package fr.cristal.smac.atom.orders;

import fr.cristal.smac.atom.*;

public class LimitOrder extends Order implements Comparable<LimitOrder> {
    public char direction;
    public int quantity;
    public int initQuty;
    public long price; /*
                        * Should be final Because of TreeSet and its comparator you can't change the
                        * price
                        */
    public long validity;
    public static final char ASK = 'A';
    public static final char BID = 'B';

    public LimitOrder(String obName, String extId, char direction, int quantity, long price, int validity) {
        super(obName, extId);
        this.direction = direction;
        this.quantity = quantity;
        this.initQuty = quantity;
        this.price = price;
        this.validity = validity;
        this.type = 'L';
    }

    public LimitOrder(String obName, String extId, char direction, int quantity, long price) {
        this(obName, extId, direction, quantity, price, -1);
    } // infinite life

    public void execute(OrderBook ob) {
        if (direction == LimitOrder.ASK)
            ob.ask.add(this); // Collections.sort(ask,Sort.ASK);
        else
            ob.bid.add(this); // Collections.sort(bid,Sort.BID);
    }

    @Override
    public String toString() {
        return super.toString() + ";" + direction + ";" + price + ";" + quantity + ";" + validity;
    } // plus naturel !

    @Override
    public int compareTo(LimitOrder other) {
        return Double.compare(this.price, other.price); // Sort by price in ascending order
    }

    public int getQuantity(){
        return this.quantity;
    }


    public double getPrice(){
        return this.price;
    }
}
