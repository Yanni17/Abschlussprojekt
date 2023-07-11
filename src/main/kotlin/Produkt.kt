open class Produkt(var marke: String ,var name: String, var preis: Double, var bewertung: MutableList<Double>, var anzahl: Int) {

    var averageAusrechnen = 0.0



    // Chat GPT
    override fun toString(): String {
        val brandColumnWidth = 15
        val nameColumnWidth = 20
        val priceColumnWidth = 12
        val ratingColumnWidth = 12
        val separator = " | "

        val formattedBrand = this.marke.take(brandColumnWidth).padEnd(brandColumnWidth)
        val formattedName = this.name.take(nameColumnWidth).padEnd(nameColumnWidth)
        val formattedPrice = "%.2f EUR".format(this.preis).take(priceColumnWidth).padEnd(priceColumnWidth)
        val formattedRating = "%.1f ⭐️".format(this.averageAusrechnen).take(ratingColumnWidth).padEnd(ratingColumnWidth)

        return "\t$formattedBrand$separator$formattedName$separator$formattedPrice$separator$formattedRating"
    }

}