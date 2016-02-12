package no.hib.dat102.mengde.tabell;

import no.hib.dat102.mengde.adt.*;
import no.hib.dat102.mengde.kjedet.*;

import java.util.Iterator;
import java.util.Random;

public class TabellMengde<T> implements MengdeADT<T> {
	// ADT-en Mengde implementert som tabell
	//
	private final static Random tilf = new Random();
	private final static int STDK = 100;
	private int antall;
	private T[] tab;

	public TabellMengde() {
		this(STDK);
	}

	public TabellMengde(int start) {
		antall = 0;
		tab = (T[]) (new Object[start]);
	}

	@Override
	public int antall() {
		return antall;
	}

	@Override
	public boolean erTom() {
		return (antall == 0);
	}

	@Override
	public void leggTil(T element) {
		if (!inneholder(element)) {
			if (antall == tab.length) {
				utvidKapasitet();
			}
			tab[antall] = element;
			antall++;
		}
	}

	private void utvidKapasitet() {
		T[] hjelpetabell = (T[]) (new Object[2 * tab.length]);
		for (int i = 0; i < tab.length; i++) {
			hjelpetabell[i] = tab[i];
		}
		tab = hjelpetabell;
	}

	@Override
	public T fjernTilfeldig() {
		T svar = null;
		if (antall > 0) {
			int indeks = tilf.nextInt(antall);
			svar = tab[indeks];
			tab[indeks] = tab[antall - 1];
			antall--;
		}
		return svar;
	}

	@Override
	public T fjern(T element) {
		// S�ker etter og fjerner element.Retur med null ved ikke-funn
		//
		T svar = null;
		if (inneholder(element)) {
			for (int i = 0; i < antall && svar == null; i++) {
				if (tab[i].equals(element)) {
					svar = tab[i];
					antall--;
					tab[i] = tab[antall];
				}
			}
		}
		return svar;
	}

	@Override
	public MengdeADT<T> union(MengdeADT<T> m2) {
		TabellMengde<T> begge = new TabellMengde<T>();
		for (int i = 0; i < antall; i++) {
			begge.settInn(tab[i]);
		}
		Iterator<T> teller = m2.oppramser();

		while (teller.hasNext()) {
			begge.leggTil(teller.next());
		}

		return begge;
	}

	public MengdeADT<T> bedreUnion(MengdeADT<T> m2) {
		T element;
		TabellMengde<T> begge = new TabellMengde<T>();
		for (int i = 0; i < antall; i++) {
			begge.settInn(tab[i]);
		}
		Iterator<T> teller = m2.oppramser();

		while (teller.hasNext()) {
			element = teller.next();
			if (inneholder(element)) {
				begge.settInn(element);
			}
		}

		return begge;
	}

	private void settInn(T element) {
		if (antall == tab.length) {
			utvidKapasitet();
		}
		tab[antall] = element;
		antall++;
	}

	@Override
	public boolean inneholder(T element) {
		int pos = -1;
		for (int i = 0; (i < antall) && (pos == -1); i++) {
			if (tab[i].equals(element))
				pos = i;
		}
		return (pos != -1);
	}

	@Override
	public boolean erLik(MengdeADT<T> m2) {
		boolean likeMengder = true;
		if (m2.antall() != antall) {
			likeMengder = false;
		}
		for (int i = 0; i < antall && likeMengder; i++) {
			likeMengder = m2.inneholder(tab[i]);
		}
		return likeMengder;
	}

	public void leggTilAlle(MengdeADT<T> m2) {
		Iterator<T> teller = m2.oppramser();
		while (teller.hasNext())
			leggTil(teller.next());
	}

	@Override
	public Iterator<T> oppramser() {
		return new TabellIterator<T>(tab, antall);
	}

	@Override
	public MengdeADT<T> snitt(MengdeADT<T> m2) {
		T element;
		TabellMengde<T> snitt = new TabellMengde();
		Iterator<T> i = m2.oppramser();
		while (i.hasNext()) {
			element = i.next();
			if (inneholder(element)) {
				snitt.settInn(element);
			}
		}
		return snitt;
	}

	@Override
	public MengdeADT<T> differens(MengdeADT<T> m2) {
		TabellMengde<T> begge = (TabellMengde<T>) union(m2);
		TabellMengde<T> snitt = (TabellMengde<T>) snitt(m2);
		TabellMengde<T> diff = new TabellMengde();
		Iterator<T> beggeI = begge.oppramser();
		T element;

		while (beggeI.hasNext()) {
			element = beggeI.next();
			if (!snitt.inneholder(element)) {
				diff.settInn(element);
			}
		}

		return diff;
	}

	public String toString() {
		String resultat = "";
		for(int i = 0; i < antall; i++) {
			resultat += tab[i].toString() + "\t";
		}
		return resultat;
	}


}// class
