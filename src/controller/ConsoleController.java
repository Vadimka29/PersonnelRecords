package controller;

import java.util.Scanner;

public class ConsoleController {
	private boolean lifeStatus;
	public ConsoleController(){
		lifeStatus = true;
		System.out.println("\t<----------Hello! This is console version of program!---------->");
		System.out.println("Please enter command: ");
		Scanner sc = new Scanner(System.in);
		while(lifeStatus){
			System.out.print("> ");
			
		}
	}
	
	
}
