class KundenAccount(
    benutzerName: String,
    passwort: String,
    var alter: Int,
    var isAdmin: Boolean,
    var zahlungsMoeglichkeit: String,
    var pin: String

): Account(benutzerName,passwort) {


    var warenKorb = mutableListOf<Produkt>()

    fun produktHinzufuegen(produkt: Produkt) {

        warenKorb.add(produkt)

    }

    fun produktEnfernen(produkt: Produkt) {

        warenKorb.remove(produkt)
    }

    fun gesamtWert(): Double {

        var gesamtPreis = 0.0

        for (produkt in warenKorb) {
            gesamtPreis += produkt.preis
        }
        return gesamtPreis
    }

    fun warenKorbAnzeigen() {

        if (warenKorb.isEmpty()) {
            println("Dein Warenkorb ist leer.")
        } else {
            println("Ihr Warenkorb enthält folgende Produkte:")
            warenKorb.forEachIndexed { index, produkt ->
                println("${index + 1}. ${produkt.name} - ${produkt.preis} EUR")
            }
        }
        println()
        println("Aktueller Warenkorb Betrag : ${gesamtWert()}")

        while (true) {

            println("Möchten Sie einen Artikel entfernen? Ja/Nein.")

            var auswahl = readln().lowercase()

            try {


                if (auswahl == "ja") {

                    for ((index, produkt) in warenKorb.withIndex()) {
                        println("[${index + 1}] $produkt")
                    }
                    println("Welches Produkt möchten Sie entfernen?")

                    var auswahl2 = 0

                    auswahl2 = readln().toInt()

                    if (auswahl2 > warenKorb.size) {

                        throw Exception("Das haben Sie nicht im Warenkorb.")

                    } else {

                        var produkt = warenKorb[auswahl2 - 1]
                        produktEnfernen(produkt)
                        println("Produkt erfolgreich entfernt.")
                        break
                    }


                } else if (auswahl == "nein") {
                    break
                } else {
                    throw Exception("Ja oder Nein.!")
                }
            }catch (e:Exception){
                println("Ungültige Eingabe!")
                e.message
            }
        }
    }

    fun produktBewerten(produkt: Produkt) {

        println("Wie viele Sterne möchten Sie dem Produkt geben?")
        var eingabe = 0.00

        while (true){

            try {

                eingabe = readln().toDouble()
                if (eingabe > 5){
                    throw Exception()
                }else {
                    produkt.bewertung += eingabe
                }


            }catch (e:Exception){
                println("Maximal 5.0!")
            }

        }

    }

}
