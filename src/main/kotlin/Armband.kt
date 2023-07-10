class Armband(
    marke: String,
    name: String,
    preis: Double,
    bewertung: MutableList<Double>,
    anzahl: Int,
    var farbe: String
) : Accesoires(marke, name, preis, bewertung,anzahl) {

    override fun toString(): String {
        return super.toString() + "| Farbe - $farbe"

    }
}