/**
 * 
 */
package edu.wit.cs.comp2000;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author 
 *
 */
public class Pile {
	private ArrayList<Card> cards;						//The first element in the list is the top of the deck, the last element is the bottom
	private static int MAX_CAPACITY = 52;

	/**
	 * 
	 */
	public Pile() {
		cards = new ArrayList<Card>();
		cards.ensureCapacity(MAX_CAPACITY);

	}

	public void shuffle() {
		Random random = new Random();
		for (int i = 0; i < cards.size()-1; i++) {		//For each card in the deck, this loop:
			int rand = random.nextInt(cards.size());	//gets a random position in the list

			Card temp = cards.get(rand);				//takes a card from that position
			cards.set(rand, cards.get(i));				//places the current card in the random position
			cards.set(i, temp);							//adds card from random position into the current position

		}
	}
	
	public Card getCard(int i) {
		return cards.get(i);
	}
	
	public void add(Card c) {

		cards.add(c);
	}
	
	public Card remove() {
		Card c = cards.remove(0);
		return c;
	}
	


	public boolean isEmpty() {
		if(cards.size() <= 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void clear() {
		cards = new ArrayList<Card>();
		cards.ensureCapacity(MAX_CAPACITY);
	}

	
	@Override
	public String toString() {
		String s = "";
		for (Card c : cards) {
			s += c.toString() + "\n";
		}
		return s;
	}

	/**
	 * @param args
	 */
/*	public static void main(String[] args) {
		Pile p = new Pile();
		
		Card c = new Card(Rank.ACE, Suit.CLUBS);
		Card c2 = new Card(Rank.TWO, Suit.SPADES);
		Card c3 = new Card(Rank.THREE, Suit.CLUBS);
		Card c4 = new Card(Rank.FOUR, Suit.DIAMONDS);
		Card c5 = new Card(Rank.FIVE, Suit.CLUBS);
		Card c6 = new Card(Rank.SIX, Suit.HEARTS);
		p.add(c);
		p.add(c2);
		p.add(c3);
		p.add(c4);
		p.add(c5);
		p.add(c6);
		System.out.println(p.toString());
		p.shuffle();
		System.out.println(p.toString());
		p.shuffle();
		System.out.println(p.toString());
		p.shuffle();
		p.remove();
		System.out.println(p.toString());
		p.shuffle();
		System.out.println(p.toString());
		p.shuffle();
		System.out.println(p.toString());
		
	}*/

}
