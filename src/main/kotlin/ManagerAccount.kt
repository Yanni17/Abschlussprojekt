class ManagerAccount(
    benutzerName: String,
    passwort: String,
) : Account(benutzerName, passwort) {


    fun alleProdukteAnsehen(alleArtikel: MutableList<Produkt>) {

        for((index,produkt) in alleArtikel.withIndex()){

            println("[${index + 1}] $produkt")

        }


    }

    fun produktHinzufuegen(produkt: Produkt, shop: Shop) {

        shop.alleProdukte.add(produkt)

    }

    fun produktLoeschen(alleArtikel: MutableList<Produkt>) {

        for ((index, produkt) in alleArtikel.withIndex()) {
            println("[${index + 1}] $produkt")
        }
        println()
        println("$yellow Welches Produkt soll aus dem Sortiment genommen werden?$reset")
        var eingabe = 0

        while (true) {

            try {

                eingabe = readln().toInt()
                if (eingabe > alleArtikel.size) {
                    throw Exception("")
                }

                var ausgewaehltesProdukt = alleArtikel[eingabe - 1]

                println("$yellow Sind Sie sicher das Sie $ausgewaehltesProdukt entfernen möchten? Ja / Nein $reset")

                var abfrage = readln().lowercase()

                if (abfrage == "ja") {

                    alleArtikel.remove(ausgewaehltesProdukt)
                    Thread.sleep(2000)
                    println("$green Produkt erfolgreich entfernt!$reset")

                    break
                } else {
                    println("Sie werden zurück geleitet!")
                    Thread.sleep(2000)
                    break
                }

            } catch (e: Exception) {
                println("$red Ungültige Eingabe$reset")
            }


        }


    }

    fun produktErstellen(alleArtikel: MutableList<Produkt>) {

        var auswahl = 0
        var preis: Double
        var bewertung: Double
        var farbe: String
        var groesse: Char


        while (true) {

            println("$yellow Welches Produkt möchten Sie erstellen?$reset")

            println(
                """
                    
            [1] Tshirt
            [2] Pullover
            [3] Halskette
            [4] Armband
        
            """.trimIndent()
            )

            try {

                auswahl = readln().toInt()

                if (auswahl !in 1..4) {
                    throw Exception("")
                }


            } catch (e: Exception) {

                println("$red Ungültige Eingabe$reset")
                continue

            }

            println("$yellow Von welcher Marke ist das Produkt:$reset")
            var marke = readln()

            println("$yellow Wie heißt das Produkt:$reset")
            var name = readln()


            while (true) {
                try {

                    println("$yellow Wie viel kostet das Produkt:$reset ")
                    preis = readln().toDouble()
                    break

                } catch (e: Exception) {
                    println("$red Ungültige Eingabe$reset")
                }

            }
            while (true) {

                try {

                    println("$yellow Wie ist die durchschnitts Bewertung des Produktes:$reset ")
                    bewertung = readln().toDouble()

                    if (bewertung > 5.0) {
                        throw Exception("$red Maximal 5.0$reset")
                    }

                    break

                } catch (e: Exception) {
                    println(e.message)
                    println("$red Ungültige Eingabe.$reset")
                }

            }

            when (auswahl) {

                1 -> {
                    println("$yellow In welcher Größe? z.B 'M'$reset ")
                    groesse = readln().capitalize().first()
                    var tShirt = Tshirt(marke, name, preis, bewertung = mutableListOf(), 10, groesse)
                    alleArtikel.add(tShirt)
                    Thread.sleep(2000)
                    println("$green Produkt erfolgreich erstellt.$reset")
                    break

                }

                2 -> {
                    println("$yellow In welcher Größe? z.B 'M'$reset ")
                    groesse = readln().capitalize().first()
                    var pullover = Pullover(marke, name, preis, bewertung = mutableListOf(), 10, groesse)
                    alleArtikel.add(pullover)
                    Thread.sleep(2000)
                    println("$green Produkt erfolgreich erstellt.$reset")
                    break

                }

                3 -> {
                    println("$yellow Welche Farbe hat das Produkt:$reset ")
                    farbe = readln()
                    var halskette = Halskette(marke, name, preis, bewertung = mutableListOf(), 10, farbe)
                    alleArtikel.add(halskette)
                    Thread.sleep(2000)
                    println("$green Produkt erfolgreich erstellt.$reset")
                    break
                }

                4 -> {
                    println("$yellow Welche Farbe hat das Produkt:$reset ")
                    farbe = readln()
                    var armband = Armband(marke, name, preis, bewertung = mutableListOf(), 10, farbe)
                    alleArtikel.add(armband)
                    Thread.sleep(2000)
                    println("$green Produkt erfolgreich erstellt.$reset")
                    break

                }
            }
        }


    }

    fun produktNachbestellen(alleArtikel: MutableList<Produkt>){

        var auswahl = 0

        for ((index,produkt) in alleArtikel.withIndex()){
            println("[${index + 1}] $produkt    Vorhanden: ${produkt.anzahl}")
        }
        println()
        println("$yellow Welches Produkt möchten Sie nachbestellen?$reset")

        while (true){

            try {

                auswahl = readln().toInt()

                if (auswahl > alleArtikel.size){

                    throw Exception()

                }else break

            }catch (e: Exception){
                println("$red Ungültige Eingabe.$reset")
            }
        }

        var ausgewaehltesProdukt = alleArtikel[auswahl - 1]

        println("$yellow Wie viele möchten Sie davon bestellen?$reset")
        var anzahl = 0

        while (true){

            try {

                anzahl = readln().toInt()
                break

            }catch (e:Exception){
                println("$red Ungültige Eingabe$reset")
                continue
            }
        }

        Thread.sleep(2000)
        ausgewaehltesProdukt.anzahl += anzahl
        println("$green Sie haben erfolgreich $ausgewaehltesProdukt $anzahl x nachbestellt.$reset")



    }


}
