package no.hib.dat102.mengde.klient;

import no.hib.dat102.mengde.kjedet.KjedetMengde;
import no.hib.dat102.mengde.tabell.TabellMengde;

import java.util.Iterator;
import java.util.Scanner;

public class OrdlisteKjedet {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		KjedetMengde<String> ordListe1 = new KjedetMengde<String>();

		String[] ord = { "God", "dag", "Hans", "Hansen", "Hansaby", "Olsen", "Ole", "buss", "rute", "Bergen" };

		Scanner tastatur = new Scanner(System.in);
		// Legger til ordene i mengden ordListe1

		for (int i = 0; i < ord.length; i++) {
			ordListe1.leggTil(ord[i]);
		}
		KjedetMengde<String> ordListe2 = new KjedetMengde<String>();

		System.out.print("Oppgi en streng, avslutt med zzz :");
		String streng = tastatur.nextLine();
		// Leser inn ord
		while (!streng.equals("zzz")) {

			ordListe2.leggTil(streng);

			System.out.print("Oppgi en streng, avslutt med zzz :");
			streng = tastatur.nextLine();
		} // while

		// Lager unionen av de to ordlistene KjedetMengde<String>
		KjedetMengde<String> ordListeBegge = new KjedetMengde<String>();
		ordListeBegge = (KjedetMengde<String>) ordListe1.union(ordListe2);
		System.out.println("Utskrift av unionen av begge ordlistene");
		while (!ordListeBegge.erTom()) {
			System.out.println(ordListeBegge.fjernTilfeldig().toString());
		}
		
		

		// Lager snittet av de to ordlistene KjedetMengde<String>
		KjedetMengde<String> ordListeSnitt = new KjedetMengde<String>();
		ordListeSnitt = (KjedetMengde<String>) ordListe1.snitt(ordListe2);
		System.out.println("Utskrift av snittet av begge ordlistene");
		while (!ordListeSnitt.erTom()) {
			System.out.println(ordListeSnitt.fjernTilfeldig().toString());
		}

		// Lager differansen av de to ordlistene KjedetMengde<String>
		KjedetMengde<String> ordListeDiff = new KjedetMengde<String>();
		ordListeDiff = (KjedetMengde<String>) ordListe1.differens(ordListe2);
		System.out.println("Utskrift av differansen av begge ordlistene");
		while (!ordListeDiff.erTom()) {
			System.out.println(ordListeDiff.fjernTilfeldig().toString());
		}

	}
}
