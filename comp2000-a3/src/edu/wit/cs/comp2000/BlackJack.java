package edu.wit.cs.comp2000;

import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack extends Pile{
	
	final static Player Dealer = new Player("Dealer");
	final int CardCount = 0;
	final static Deck d = new Deck();

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		System.out.printf("BlackJack\n---------\n\n");
		System.out.print("Name: ");
		String name = s.next();
		Player one  = new Player(name);
		boolean win;
		
		Dealer.SetMoney(2000.0); //Start Dealer off with double the players.
		double bet;
		
		while(true) {
		
			double money = one.getMoney();
			
			if(money == 0)
				System.exit(0);
			
			do {
			System.out.println("You have "+money+"$");
			System.out.print("How much would you like to bet? ");
			bet = s.nextDouble();
			
			if (bet < 0 || bet > money)
                System.out.println("You can only bet between 0 and " + money + '.');
			
         } while (bet < 0 || bet > money);
			
			
			win = RunGame(one,bet);
			
			if(win) {
				one.win(bet);
				Dealer.lose(bet);
			}
			else {
				one.lose(bet);
				Dealer.win(bet);
			}
			if(money == 0) {
				System.out.println("You ran out of money. Learn how to count.");
				System.exit(0);
			}
			if(Dealer.getMoney() == 0) {
				System.out.print("You beat the house! Seems legit.");
				System.exit(0);
			}
			
	}
}
	public static void StartGame(Player temp) {
		d.reset();
		d.shuffle();
		Dealer.reset(d);
		temp.reset(d);
	}
	
	public static boolean RunGame(Player temp, double bet) {
			
		Scanner in = new Scanner(System.in);
		
		StartGame(temp);
		
		
		//Checks BlackJack at start of round.
			if(CheckBlackJack(temp) == true) {
				System.out.println("You beat the Dealer. You win "+bet*1.5+"$");
				
				//If PLayer gets BlackJack return 3:2 of original bet.
				bet = 1.5*bet;
				return true;
			}
			
			if(CheckBlackJack(Dealer) == true) {
				Dealer.dispHand();
				temp.dispHand();
				System.out.println("Dealer has BlackJack. You lose.");
				return false;
			}
			
			while(true) {
				
				temp.dispHand();
				Dealer.dispHand();
				char a;
					System.out.println("Would you like to Hit or Stand? (H/S)");
					a = in.next().charAt(0);
				
				if(a == 'S') {
					break;
				}
				
				else {
					temp.Hit(d);
					if(CheckBust(temp) == true) {
						temp.dispHand();
						Dealer.dispHand();
						System.out.println("You have busted. You lose.");
						return false;
					}
				}
			}
	
			/**
			 * The User has now either busted or stood. It is now the dealers turn to draw until 
			 * it beats the user or busts.
			 */
			
			
			//Typically if a Dealer has greater than 16 they will stand for the round.
			while(Dealer.Score()<= 16) {
				Dealer.Hit(d);
				if(CheckBust(Dealer) == true) {
					temp.dispHand();
					Dealer.dispHand();
					System.out.println("Dealer has busted. You win "+bet+"$");
					return true;
				}
			}
			
			if(temp.Score() == Dealer.Score()) {
				temp.dispHand();
				Dealer.dispHand();
				System.out.println("Dealer wins in a tie.");
				return false;
			}
			
			if(temp.Score() > Dealer.Score()) {
				temp.dispHand();
				Dealer.dispHand();
				System.out.println("You beat the Dealer. You win "+bet+"$");
				return true;
			}
			
			else {
				temp.dispHand();
				Dealer.dispHand();
				System.out.println("The Dealer beat you. You lose.");
				return false;
			}
			
		}
	
	
	
	public static boolean CheckBlackJack(Player temp) {
		return temp.Score() == 21;
	}
	
	public static boolean CheckBust(Player temp) {
		return temp.Score() >21;
	}
	
}
