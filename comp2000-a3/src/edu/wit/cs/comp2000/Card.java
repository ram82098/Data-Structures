/**
 * 
 */
package edu.wit.cs.comp2000;

/**
 * @author Ryan Maresca
 *
 */
public class Card implements Comparable<Card> {

	/**
	 * 
	 */
	private final Suit s;
	private final Rank r;
	private final int point;
	private final int altpoint;
	
	public Card(Rank DispR, Suit DispS) {
		r = DispR;
		s = DispS;
		point = r.getPoints();
		altpoint = r.getAltPoints();
		
	}

	public String getSuit() {
		return s.getGraphic();
	}
	
	public String getRank() {
		return r.getGraphic();
	}
	
	public int getPoints() {
		return r.getPoints();
	}
	
	public int getAltPoints() {
		return r.getAltPoints();
	}
	@Override
	public String toString() {
		String card = "Rank: "+getRank()+" "+"Suit: "+getSuit();
		return card;
	}
	/*
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Card o) {
		
		if ((this.s != o.s) && (this.r != o.r))
			return -1;
		else
			return 0;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) { 
		Card test1 = new Card(Rank.ACE,Suit.CLUBS);
		Card test2 = new Card(Rank.KING,Suit.DIAMONDS);
		
		if(test1.compareTo(test2) == -1)
		System.out.print(test1.toString()+" ");
		
	}

}
