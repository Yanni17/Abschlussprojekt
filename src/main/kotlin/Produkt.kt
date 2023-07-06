open class Produkt(var name: String, var preis: Double, var bewertung: MutableList<Double>, var anzahl: Int) {

    var averageAusrechnen = 0.0
    init {
        if (bewertung.isNotEmpty()){
            averageAusrechnen = bewertung.average()
        }else {
            averageAusrechnen = 0.0
        }
    }

    fun produktAnzeigen(){}


    override fun toString(): String {
        return "${this.name} -- Preis: ${this.preis} EUR -- Bewertung: ${this.bewertung} ⭐️ "
    }

}