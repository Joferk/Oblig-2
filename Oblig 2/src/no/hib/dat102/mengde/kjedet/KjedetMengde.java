package no.hib.dat102.mengde.kjedet;

import no.hib.dat102.mengde.adt.*;
import no.hib.dat102.mengde.tabell.TabellMengde;

//********************************************************************
// Kjedet implementasjon av en mengde. 
//********************************************************************
import java.util.*;

public class KjedetMengde<T> implements MengdeADT<T> {
	private static Random rand = new Random();
	private int antall; // antall elementer i mengden
	private LinearNode<T> start;

	/**
	 * Oppretter en tom mengde.
	 */
	public KjedetMengde() {
		antall = 0;
		start = null;
	}//

	@Override
	public void leggTil(T element) {
		if (!(inneholder(element))) {
			LinearNode<T> node = new LinearNode<T>(element);
			node.setNeste(start);
			start = node;
			antall++;
		}
	}

	public void leggTilAlle(MengdeADT<T> m2) {
		Iterator<T> teller = m2.oppramser();
		while (teller.hasNext()) {
			leggTil(teller.next());
		}
	}

	@Override
	public T fjernTilfeldig() {
		LinearNode<T> forgjenger, aktuell;
		T resultat = null;
		if (!erTom()) {
			int valg = rand.nextInt(antall) + 1;
			if (valg == 1) {
				resultat = start.getElement();
				start = start.getNeste();
			} else {
				forgjenger = start;
				for (int nr = 2; nr < valg; nr++) {
					forgjenger = forgjenger.getNeste();
				}
				aktuell = forgjenger.getNeste();
				resultat = aktuell.getElement();
				forgjenger.setNeste(aktuell.getNeste());
			}
			antall--;
		} // if
		return resultat;
	}//

	@Override
	public T fjern(T element) {
		T resultat = null;
		if (!erTom()) {
			boolean inneholder = inneholder(element);
			boolean slettet = false;
			LinearNode<T> forgjenger = start;
			LinearNode<T> aktuell = start.getNeste();
			if (inneholder && start.getElement().equals(element)) {
				start = start.getNeste();
				slettet = true;
			}
			while (inneholder && !slettet) {
				if (aktuell.getElement().equals(element)) {
					resultat = aktuell.getElement();
					forgjenger.setNeste(aktuell.getNeste());
					slettet = true;
				}
				forgjenger = aktuell;
				aktuell = forgjenger.getNeste();
			}
		}
		return resultat;
	}//

	@Override
	public MengdeADT<T> union(MengdeADT<T> m2) {// OBS! En bedre i kladdeopg4
		KjedetMengde<T> begge = new KjedetMengde<T>();
		LinearNode<T> aktuell = start;
		while (aktuell != null) {
			begge.settInn(aktuell.getElement());
			aktuell = aktuell.getNeste();
		} // while
		Iterator<T> teller = m2.oppramser();
		while (teller.hasNext()) {
			begge.leggTil(teller.next());
		}
		return begge;
	}//

	@Override
	public MengdeADT<T> bedreUnion(MengdeADT<T> m2) {// OBS! En bedre i
														// kladdeopg4
		T element;
		KjedetMengde<T> begge = new KjedetMengde<T>();
		LinearNode<T> aktuell = start;
		while (aktuell != null) {
			begge.settInn(aktuell.getElement());
			aktuell = aktuell.getNeste();
		} // while
		Iterator<T> teller = m2.oppramser();
		while (teller.hasNext()) {
			element = teller.next();
			if (!inneholder(element)) {
				begge.settInn(element);
			}
		}
		return begge;
	}//

	private void settInn(T element) {
		LinearNode<T> nyNode = new LinearNode<T>(element);
		nyNode.setNeste(start);
		start = nyNode;
		antall++;
	}

	@Override
	public boolean inneholder(T element) {
		boolean funnet = false;
		LinearNode<T> aktuell = start;
		for (int sok = 0; sok < antall && !funnet; sok++) {
			if (aktuell.getElement().equals(element)) {
				funnet = true;
			} else {
				aktuell = aktuell.getNeste();
			}
		}
		return funnet;
	}

	@Override
	public boolean erLik(MengdeADT<T> m2) {
		boolean likeMengder = true;
		T element = null;
		if (antall != m2.antall()) {
			likeMengder = false;
		}
		Iterator m2i = m2.oppramser();
		for (int i = 0; i < antall && likeMengder; i++) {
			likeMengder = inneholder((T) m2i.next());
		}
		return likeMengder;

	}

	@Override
	public boolean erTom() {
		return antall == 0;
	}

	@Override
	public int antall() {
		return antall;
	}

	@Override
	public Iterator<T> oppramser() {
		return new KjedetIterator<T>(start);
	}

	@Override
	public MengdeADT<T> snitt(MengdeADT<T> m2) {
		T element;
		KjedetMengde<T> snitt = new KjedetMengde();
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
		KjedetMengde<T> begge = (KjedetMengde<T>) union(m2);
		KjedetMengde<T> snitt = (KjedetMengde<T>) snitt(m2);
		KjedetMengde<T> diff = new KjedetMengde();
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
		LinearNode<T> aktuell = start;
		while (aktuell != null) {
			resultat += aktuell.getElement().toString() + "\t";
			aktuell = aktuell.getNeste();
		}
		return resultat;
	}

}// class
