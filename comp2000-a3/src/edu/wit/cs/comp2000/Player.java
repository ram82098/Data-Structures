/**
 * 
 */
package edu.wit.cs.comp2000;

/**
 * @author 
 *
 */
public class Player {
	
	private Hand hand;
	private Hand split;
	private String name;
	private double money = 1000;//Default start is 1000$
	private Deck d;

	/**
	 * 
	 */
	public Player(String Name) {
		
		//Initializes a player with a name and a hand consisting of 2 cards
		this.name = Name;
		this.d = new Deck();
		this.hand = new Hand(d.deal(),d.deal());
	}
	
	public void reset(Deck q) {
		this.hand.reset(q.deal(),q.deal());
	}

	public int Score(){
		
		int s = 0;
		
		//Needs card getter for hand class to work.
		//Iterates through all the cards in players hand and sums the points.
		for(int i = 0; i<hand.getSize(); i++) {
			Card c = hand.getCard(i);
			//Accounts for properties of Ace
			if(c.getRank().equals("A") && (s>10))
				s += c.getPoints();
			else
				s += c.getAltPoints();
				
		}
		return s;
	}
	
	public void Hit(Deck dec) {
		this.hand.add(dec.deal());
	}
	
	public void SetMoney(double m) {
		this.money = m;
	}
	
	public void win(double bet) {
		this.money += bet;
		reset(d);
	}
	
	public void lose(double bet) {
		
		if(this.money > bet)
			this.money -= bet;
		else 
			this.money = 0;
		reset(d);
	}
	
	public double getMoney() {
		return this.money;
	}
	
	public boolean hasMoney(){
		return this.money!=0;
	}
	
	public void dispHand() {
		 System.out.printf(name+"'s Hand \n------------------- \n"+hand.toString()+"\n Score: "+Score()+"\n");
		 System.out.format("Money: %.2f", getMoney());
		 System.out.printf("\n"+"\n");
		}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		Player one = new Player("Ryan");
//		Player dealer = new Player("House");
//		
//		one.win(120.0);
//		one.dispHand();
//		one.lose(120.114);
//		one.lose(320.0);
//		one.dispHand();
//		
//		one.win(11.1789);
//		one.dispHand();
//		one.lose(1000.0);
//		one.dispHand();

	}

}
