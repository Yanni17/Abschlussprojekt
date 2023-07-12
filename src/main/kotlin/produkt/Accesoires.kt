package produkt

open class Accesoires(
    marke: String,
    name: String,
    preis: Double,
    bewertung: MutableList<Double>,
    anzahl: Int
) : Produkt(marke, name, preis, bewertung, anzahl) {
}