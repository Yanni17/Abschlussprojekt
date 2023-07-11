class Tshirt(
    marke: String,
    name: String,
    preis: Double,
    bewertung: MutableList<Double>,
    anzahl: Int,
    var groesse: String
) : Kleidung(marke, name, preis, bewertung,anzahl) {

    override fun toString(): String {
        return super.toString() + "| Größe - $groesse"
    }


}