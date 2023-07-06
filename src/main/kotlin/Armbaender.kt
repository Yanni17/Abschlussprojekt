class Armbaender(
    name: String,
    preis: Double,
    bewertung: MutableList<Double>,
    anzahl: Int,
    var farbe: String
) : Accesoires(name, preis, bewertung,anzahl) {

    override fun toString(): String {
        return super.toString() + "Farbe: $farbe"

    }
}