package no.hib.dat102.mengde.test;

import static org.junit.Assert.*;
import org.junit.*;
import no.hib.dat102.mengde.adt.*;
import no.hib.dat102.mengde.kjedet.KjedetMengde;

public class TestMengdeADT {

	private MengdeADT<String> testStabel1;
	private MengdeADT<String> testStabel2;
	
	
	private String s0 = "per";
	private String s1 = "pål";
	private String s2 = "espen";
	private String s3 = "kåre";
	private String s4 = "nils";
	private String s5 = "truls";
	
	@Before
	public final void setup() {
		testStabel1 = new KjedetMengde();
		testStabel2 = new KjedetMengde();
	}
	
	@Test
	public final void testUnion() {
		MengdeADT<String> forventet = new KjedetMengde();
		forventet.leggTil(s0);
		forventet.leggTil(s1);
		forventet.leggTil(s2);
		forventet.leggTil(s3);
		forventet.leggTil(s4);
		forventet.leggTil(s5);
		testStabel1.leggTil(s0);
		testStabel1.leggTil(s1);
		testStabel1.leggTil(s2);
		testStabel1.leggTil(s3);
		testStabel2.leggTil(s3);
		testStabel2.leggTil(s4);
		testStabel2.leggTil(s5);
		MengdeADT<String> union = testStabel1.union(testStabel2);
		assertTrue("Union fungerer ikke", forventet.erLik(union));	
	}
	
	@Test
	public final void testBedreUnion() {
		MengdeADT<String> forventet = new KjedetMengde();
		forventet.leggTil(s0);
		forventet.leggTil(s1);
		forventet.leggTil(s2);
		forventet.leggTil(s3);
		forventet.leggTil(s4);
		forventet.leggTil(s5);
		testStabel1.leggTil(s0);
		testStabel1.leggTil(s1);
		testStabel1.leggTil(s2);
		testStabel1.leggTil(s3);
		testStabel2.leggTil(s3);
		testStabel2.leggTil(s4);
		testStabel2.leggTil(s5);
		MengdeADT<String> union = testStabel1.bedreUnion(testStabel2);
		assertTrue("Union fungerer ikke", forventet.erLik(union));	
	}
	
	@Test
	public final void testSnitt() {
		MengdeADT<String> forventet = new KjedetMengde();
		forventet.leggTil(s2);
		forventet.leggTil(s3);
		testStabel1.leggTil(s0);
		testStabel1.leggTil(s1);
		testStabel1.leggTil(s2);
		testStabel1.leggTil(s3);
		testStabel2.leggTil(s2);
		testStabel2.leggTil(s3);
		testStabel2.leggTil(s4);
		testStabel2.leggTil(s5);
		MengdeADT<String> snitt = testStabel1.snitt(testStabel2);
		assertTrue("Snitt fungerer ikke", forventet.erLik(snitt));
	}
	
	@Test
	public final void testDiff() {
		MengdeADT<String> forventet = new KjedetMengde();
		forventet.leggTil(s0);
		forventet.leggTil(s1);
		forventet.leggTil(s4);
		forventet.leggTil(s5);
		testStabel1.leggTil(s0);
		testStabel1.leggTil(s1);
		testStabel1.leggTil(s2);
		testStabel1.leggTil(s3);
		testStabel2.leggTil(s2);
		testStabel2.leggTil(s3);
		testStabel2.leggTil(s4);
		testStabel2.leggTil(s5);
		MengdeADT<String> diff = testStabel1.differens(testStabel2);
		assertTrue("Differens fungerer ikke", forventet.erLik(diff));
		
	}
}
