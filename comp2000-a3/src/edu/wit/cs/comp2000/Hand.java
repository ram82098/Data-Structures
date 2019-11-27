/**
 * 
 */
package edu.wit.cs.comp2000;

/**
 * @author 
 *
 */
public class Hand extends Pile {
	private int size;
	/**
	 * 
	 */
		
	public Hand(Card c1, Card c2) {
		add(c1);
		add(c2);
		size = 2;

	}

	public int getSize() {
		return size;
	}
	
	@Override
	public void add(Card c) {
		super.add(c);
		size++;
	}
	
	public Card getCard(int i) {
		return super.getCard(i);
	}
	
	public void reset(Card c1, Card c2) {
		clear();
		add(c1);
		add(c2);
		size = 2;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Deck d = new Deck();
		Hand h = new Hand(d.deal(), d.deal());
		System.out.print(h.toString());
		System.out.print(h.getCard(1));
	}

}
