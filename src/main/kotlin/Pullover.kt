class Pullover(
    name: String,
    preis: Double,
    bewertung: MutableList<Double>,
    anzahl: Int,
    var groesse: Char
) : Kleidung(name, preis, bewertung,anzahl) {


    override fun toString(): String {
        return super.toString() + "Größe -> $groesse"
    }

}