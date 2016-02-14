package no.hib.dat102.hobby;

public class Hobby {
	private String hobbyNavn;

	public Hobby(String hobby) {
		hobbyNavn = hobby;
	}

	public String toString() {
		return hobbyNavn;
	}

	public boolean equals(Object hobby2) {
		return (hobbyNavn.equals(hobby2.toString()));
	}
}