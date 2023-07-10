class Pullover(
    marke: String,
    name: String,
    preis: Double,
    bewertung: MutableList<Double>,
    anzahl: Int,
    var groesse: Char
) : Kleidung(marke, name, preis, bewertung,anzahl) {


    override fun toString(): String {
        return super.toString() + "| Größe - $groesse"
    }

}