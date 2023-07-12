package produkt

open class Kleidung(
    marke: String,
    name: String,
    preis: Double,
    bewertung: MutableList<Double>,
    anzahl: Int
) : Produkt(marke, name, preis, bewertung, anzahl) {


}