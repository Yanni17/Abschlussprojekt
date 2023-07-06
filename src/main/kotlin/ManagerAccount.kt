class ManagerAccount(
    benutzerName: String,
    passwort: String,
    var isAdmin: Boolean
) : Account(benutzerName, passwort) {


    fun alleProdukteAnsehen() {}
    fun produktHinzufuegen(produkt: Produkt, shop: Shop) {

        shop.alleProdukte.add(produkt)

    }

    fun produktLoeschen(alleArtikel: MutableList<Produkt>) {

        for ((index, produkt) in alleArtikel.withIndex()) {
            println("[${index + 1}] $produkt")
        }
        println()
        println("Welches Produkt soll aus dem Sortiment genommen werden?")
        var eingabe = 0

        while (true) {

            try {

                eingabe = readln().toInt()
                if (eingabe > alleArtikel.size) {
                    throw Exception("")
                }

                var ausgewaehltesProdukt = alleArtikel[eingabe - 1]

                println("Sind Sie sicher das Sie $ausgewaehltesProdukt entfernen möchten?")

                var abfrage = readln().lowercase()

                if (abfrage == "ja") {

                    alleArtikel.remove(ausgewaehltesProdukt)

                    println("Produkt erfolgreich entfernt!")

                    break
                }else {
                    println("Sie werden zurück geleitet!")
                    break
                }

            } catch (e: Exception) {
                println("Ungültige Eingabe")
            }


        }


    }

    fun produktErstellen(alleArtikel: MutableList<Produkt>) {

        var auswahl = 0

        while (true) {

            println(
                """
            Welches Produkt möchten Sie erstellen?
        
            [1] Tshirt
            [2] Pullover
            [3] Halskette
            [4] Armband
        
            """.trimIndent()
            )

            try {

                auswahl = readln().toInt()

                if (auswahl !in 1 until 5) {
                    throw Exception("")
                }


            } catch (e: Exception) {

                println("Ungültige Eingabe")
                continue

            }

            println("Wie heißt das Produkt: ")
            var name = readln()
            var preis: Double
            var bewertung: Double
            var farbe: String
            var groesse: Char

            while (true) {
                try {

                    println("Wie viel kostet das Produkt : ")
                    preis = readln().toDouble()

                    break

                } catch (e: Exception) {
                    println("Ungültige Eingabe")
                }

            }
            while (true) {
                try {

                    println("Wie ist die durchschnitts Bewertung des Produktes : ")
                    bewertung = readln().toDouble()
                    if (bewertung > 5.0) {
                        throw Exception("")
                    }

                    break

                } catch (e: Exception) {
                    println("Ungültige Eingabe. Maximal Bewertung: 5.0")
                }

            }

            when (auswahl) {

                1 -> {
                    println("In welcher Größe? z.B 'M' ")
                    groesse = readln().capitalize().first()
                    var tShirt = Tshirt(name, preis, bewertung, 10, groesse)
                    alleArtikel.add(tShirt)
                    println("Produkt erfolgreich erstellt.")
                    break

                }

                2 -> {
                    println("In welcher Größe? z.B 'M' ")
                    groesse = readln().capitalize().first()
                    var pullover = Pullover(name, preis, bewertung, 10, groesse)
                    alleArtikel.add(pullover)
                    println("Produkt erfolgreich erstellt.")
                    break

                }

                3 -> {
                    println("Welche Farbe hat das Produkt: ")
                    farbe = readln()
                    var halskette = Halsketten(name, preis, bewertung, 10, farbe)
                    alleArtikel.add(halskette)
                    println("Produkt erfolgreich erstellt.")
                    break
                }

                4 -> {
                    println("Welche Farbe hat das Produkt: ")
                    farbe = readln()
                    var halskette = Armbaender(name, preis, bewertung, 10, farbe)
                    alleArtikel.add(halskette)
                    println("Produkt erfolgreich erstellt.")
                    break

                }
            }


        }
    }


}