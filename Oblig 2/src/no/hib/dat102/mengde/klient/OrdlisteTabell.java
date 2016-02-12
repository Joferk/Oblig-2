package no.hib.dat102.mengde.klient;

import no.hib.dat102.mengde.kjedet.KjedetMengde;
import no.hib.dat102.mengde.tabell.TabellMengde;

import java.util.Iterator;
import java.util.Scanner;

public class OrdlisteTabell {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		TabellMengde<String> ordListe1 = new TabellMengde<String>();

		String[] ord = { "God", "dag", "Hans", "Hansen", "Hansaby", "Olsen", "Ole", "buss", "rute", "Bergen" };

		Scanner tastatur = new Scanner(System.in);
		// Legger til ordene i mengden ordListe1

		for (int i = 0; i < ord.length; i++) {
			ordListe1.leggTil(ord[i]);
		}
		TabellMengde<String> ordListe2 = new TabellMengde<String>();

		System.out.print("Oppgi en streng, avslutt med zzz :");
		String streng = tastatur.nextLine();
		// Leser inn ord
		while (!streng.equals("zzz")) {

			ordListe2.leggTil(streng);

			System.out.print("Oppgi en streng, avslutt med zzz :");
			streng = tastatur.nextLine();
		} // while

		// Lager unionen av de to ordlistene KjedetMengde<String>
		TabellMengde<String> ordListeBegge = new TabellMengde<String>();
		ordListeBegge = (TabellMengde<String>) ordListe1.union(ordListe2);
		System.out.println("Utskrift av unionen av begge ordlistene");
		while (!ordListeBegge.erTom()) {
			System.out.println(ordListeBegge.fjernTilfeldig().toString());
		}
		
		

		// Lager snittet av de to ordlistene KjedetMengde<String>
		TabellMengde<String> ordListeSnitt = new TabellMengde<String>();
		ordListeSnitt = (TabellMengde<String>) ordListe1.snitt(ordListe2);
		System.out.println("Utskrift av snittet av begge ordlistene");
		while (!ordListeSnitt.erTom()) {
			System.out.println(ordListeSnitt.fjernTilfeldig().toString());
		}

		// Lager differansen av de to ordlistene KjedetMengde<String>
		TabellMengde<String> ordListeDiff = new TabellMengde<String>();
		ordListeDiff = (TabellMengde<String>) ordListe1.differens(ordListe2);
		System.out.println("Utskrift av differansen av begge ordlistene");
		while (!ordListeDiff.erTom()) {
			System.out.println(ordListeDiff.fjernTilfeldig().toString());
		}

	}
}
