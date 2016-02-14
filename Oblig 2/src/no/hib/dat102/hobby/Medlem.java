package no.hib.dat102.hobby;

import no.hib.dat102.mengde.kjedet.KjedetMengde;

public class Medlem {
	private String navn;
	private KjedetMengde<Hobby> hobbyer;
	private int statusIndeks;
	
	public Medlem(String navn) {
		this.navn = navn;
		hobbyer = new KjedetMengde<>();
		statusIndeks = 0;		
	}
	
	public String getNavn() {
		return navn;
	}


	public void setNavn(String navn) {
		this.navn = navn;
	}


	public KjedetMengde<Hobby> getHobbyer() {
		return hobbyer;
	}


	public void setHobbyer(KjedetMengde<Hobby> hobbyer) {
		this.hobbyer = hobbyer;
	}


	public int getStatusIndeks() {
		return statusIndeks;
	}


	public void setStatusIndeks(int statusIndeks) {
		this.statusIndeks = statusIndeks;
	}


	public boolean passerTil(Medlem medlem2) {
		
		
		return false;
	}
}