class Halsketten(
    name: String,
    preis: Double,
    bewertung: Double,
    anzahl: Int,
    var farbe: String
) : Accesoires(name, preis, bewertung,anzahl) {


    override fun toString(): String {
        return super.toString() + "Farbe: $farbe"
    }

}