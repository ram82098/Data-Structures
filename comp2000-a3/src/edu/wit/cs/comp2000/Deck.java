/**
 * 
 */
package edu.wit.cs.comp2000;

/**
 * @author 
 *
 */
public class Deck extends Pile {
	private int size;
	/**
	 * 
	 */
	public Deck() {
		for(Rank r: Rank.values()) {
			if(r == Rank.JOKER) {
				break;
			}
			else {
				for(Suit s : Suit.values()) {
					if(s == Suit.NONE) {
						continue;
					}
					else {
						add(new Card(r,s));
					}
				}
			}
		}
		size = 52;
		shuffle();
	}

	public int getSize() {
		return size;
	}
	
	public Card deal(){
		if (isEmpty()) {
			throw new IllegalStateException();
		}
		else {
			size--;
			return remove();
		}
	}
	
	public void reset() {
		for(Rank r: Rank.values()) {
			if(r == Rank.JOKER) {
				break;
			}
			else {
				for(Suit s : Suit.values()) {
					if(s == Suit.NONE) {
						continue;
					}
					else {
						add(new Card(r,s));
					}
				}
			}
		}
		size = 52;
		shuffle();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Deck d = new Deck();
		System.out.print(d.toString());
		

	}

}
