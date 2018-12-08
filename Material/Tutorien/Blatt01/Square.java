/*
 * DISCLAIMER: Dies ist ein Beispiel und kann ggf. Fehler enthalten. Bei Widersprüchen zu den Inhalten der Vorlesung gilt
 * der Inhalt der Vorlesung als richtig.
 *
 *
 *
 * Dies ist einfacher mehrzeiliger Kommentar; beachte das "/*" am Anfang.
 * 
 * 
 * Der Kommentar unten ist ein "javadoc"-Kommentar, anwendbar bei Klassen/Felder/Methoden.
 * Beachte das "/**" am Anfang.
 * Der Inhalt dieses Kommentars erscheint als Info bei Mouseover oder als CTRL+SPACE Info.
 * 
 * 
 * 
 * 
 * Dieser gesamte Code ist eine "KLASSE". Sie ist ein Schema/Typ oder eine Anleitung/Beschreibung für "OBJEKTE" dieser Klasse.
 */
/**
 * This class represents a square with a modifiable side length. 
 * 
 * @author daniel
 */
public class Square {

	/*
	 * Direkt in der Klasse sind "FELDER". Jedes Objekt hat seine eigenen Werte für diese Felder.
	 */
	float sideLength;	// primitiver/flacher Datentyp für Dezimalzahlen
	Point coordinate;	// Felder sind oft Objekte
	
	/*
	 * Dies ist ein "KONSTRUKTOR". Er erzeugt ein Objekt und gibt die Referenz zurück.
	 * Syntax: "public NameDerKlasse(  Parameter  ) { ... }"
	 * Verwendung: "NameDerKlasse neuesObjekt = new NameDerKlasse(  Parameterwerte  );"
	 */
	/**
	 * Constructs a square object with a given side length.
	 * 
	 * @param sideLength The length of each side of the new square
	 */
	public Square(float sideLength) {
		// Wenn Feld und Parameter gleich heißen, ist "THIS" notwendig:
		this.sideLength = sideLength;
		// "this" ist das Objekt selbst. Also muss mit "this.sidelength" das Feld gemeint sein.
	}
	
	/*
	 * Dies ist eine "METHODE". Sie kann auf ein einzelnes Objekt angewandt werden.
	 * Verwendung: "einObjekt.methodeAusfuehren(  Parameterwerte  );"
	 */
	/** 
	 * Multiplies a square's side length by a given factor.
	 * 
	 * @param s The factor
	 */
	public void scaleBy(double s) {
		// sideLength = sideLength * s;
		sideLength *= s;
	}
	
	
	
	
	
	/*
	 * Aufgabe 2.4 - diese main-Methode sollte eigentlich in "SquareObjects.java" sein
	 */
	public static void main(String[] args) {
		// Neues Objekt erstellen
		Square oneSquare = new Square(3.0f); // f für float
		System.out.println("oneSquare: " + oneSquare.sideLength);
		
		// Noch ein Objekt erstellen
		Square anotherSquare = new Square(5.678f);
		System.out.println("anotherSquare: " + anotherSquare.sideLength);
		
		// Methode ausführen
		oneSquare.scaleBy(2);
		System.out.println("oneSquare: " + oneSquare.sideLength);
		
		// Feld kopieren
		anotherSquare.sideLength = oneSquare.sideLength;
		anotherSquare.scaleBy(10);
		System.out.println("oneSquare: " + oneSquare.sideLength);
		System.out.println("anotherSquare: " + anotherSquare.sideLength);
		
		// Referenz kopieren
		anotherSquare = oneSquare; // jetzt zeigen beide Referenzen auf das selbe Objekt!
		anotherSquare.scaleBy(10);
		System.out.println("oneSquare: " + oneSquare.sideLength);
		System.out.println("anotherSquare: " + anotherSquare.sideLength);
	}
	
}
