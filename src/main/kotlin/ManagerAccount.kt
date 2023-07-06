class ManagerAccount(
    benutzerName: String,
    passwort: String,
    var isAdmin: Boolean
): Account(benutzerName,passwort) {


    fun produktHinzufuegen(produkt: Produkt, shop: Shop) {

        shop.alleProdukte.add(produkt)

    }

    fun produktLoeschen(produkt: Produkt, shop: Shop) {

        shop.alleProdukte.remove(produkt)
    }

    fun produktErstellen(): Produkt {

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
                    groesse = readln().first()
                    return Tshirt(name, preis, bewertung, 10, groesse)

                }

                2 -> {
                    println("In welcher Größe? z.B 'M' ")
                    groesse = readln().first()
                    return Pullover(name, preis, bewertung, 10, groesse)

                }

                3 -> {
                    println("Welche Farbe hat das Produkt: ")
                    farbe = readln()
                    return Halsketten(name, preis, bewertung, 10, farbe)

                }

                4 -> {
                    println("Welche Farbe hat das Produkt: ")
                    farbe = readln()
                    return Armbaender(name, preis, bewertung, 10, farbe)

                }
            }


        }
    }


}