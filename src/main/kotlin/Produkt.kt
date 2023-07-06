open class Produkt(var name: String, var preis: Double, var bewertung: Double, var anzahl: Int) {

    fun produktAnzeigen(){}


    override fun toString(): String {
        return "${this.name} -- Preis: ${this.preis} EUR -- Bewertung: ${this.bewertung} ⭐️ "
    }

}