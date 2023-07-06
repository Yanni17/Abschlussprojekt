class Shop {

    var alleProdukte = mutableListOf<Produkt>(

        Tshirt("Áme Noire Classic T-Shirt", 22.99, bewertung = mutableListOf(), 10, 'M'),
        Tshirt("Áme Noire Basic T-Shirt", 19.99, bewertung = mutableListOf(), 10, 'M'),
        Tshirt("Áme Noire Limited T-Shirt", 39.99, bewertung = mutableListOf(), 10, 'M'),
        Pullover("Áme Noire Classic Pullover", 29.99, bewertung = mutableListOf(), 10, 'M'),
        Pullover("Áme Noire Basic Pullover", 24.99, bewertung = mutableListOf(), 10, 'M'),
        Pullover("Áme Noire Limited Pullover", 49.99, bewertung = mutableListOf(), 10, 'M'),
        Halsketten("Áme Noire Edelstahl Halskette", 14.99, bewertung = mutableListOf(), 10, "Silber"),
        Halsketten("Áme Noire Vintage Halskette", 18.99, bewertung = mutableListOf(), 10, "Braun"),
        Halsketten("Áme Noire Modern Halskette", 21.99, bewertung = mutableListOf(), 10, "Schwarz"),
        Armbaender("Áme Noire Edelstahl Armband", 12.99, bewertung = mutableListOf(), 10, "Schwarz"),
        Armbaender("Áme Noire Vintage Armband", 16.99, bewertung = mutableListOf(), 10, "Schwarz"),
        Armbaender("Áme Noire Modern Armband", 13.99, bewertung = mutableListOf(), 10, "Braun")
    )

    fun shop(kunde: KundenAccount) {

        for ((index, produkt) in alleProdukte.withIndex()) {
            println("[${index + 1}] $produkt")
        }
        println()

        while (true) {

            println("Möchten Sie Die Produkte Filtern / Sortieren?")

            println(
                """
            
                [1] Filtern nach Tshirts            [3] Filtern nach Halsketten         [5] Sortieren nach Preis (absteigend)
                
                [2] Filtern nach Pullover           [4] Filtern nach Armbänder          [6] Sortieren nach Alphabet
                
                
                                                    [7] Zurück
                
                """.trimIndent()
            )


            var eingabe = 0

            try {

                eingabe = readln().toInt()
                if (eingabe !in 1..7) {
                    throw Exception()
                }

                when (eingabe) {

                    1 -> filterNachTshirts(kunde)

                    2 -> filterNachPullover(kunde)

                    3 -> filterNachArmbaender(kunde)

                    4 -> filterNachHalsketten(kunde)

                    5 -> sortiertNachPreis(kunde)

                    6 -> sortiertNachAlphabet(kunde)

                    7 -> break


                }


            } catch (e: Exception) {
                println("Ungültige Eingabe")
            }

        }


    }

    fun alleAccessoires() {
        var counter = 0
        for (produkt in alleProdukte) {
            if (produkt is Accesoires) {
                println("[$counter] $produkt")
                counter++
            }
        }
    }

    fun alleKleidungen() {
        var counter = 0
        for (produkt in alleProdukte) {
            if (produkt is Kleidung) {
                println("[$counter] $produkt")
                counter++
            }
        }
    }

    fun sortiertNachAlphabet(kunde: KundenAccount) {

        var sortierteListe = alleProdukte.sortedBy { it.name }

        for ((index, produkt) in sortierteListe.withIndex()) {
            println("[${index + 1}] $produkt")
        }

        var eingabe = ""
        var ausgewählterIndex = 0
        var nochEinProduktAbfrage = false

        while (true) {

            try {
                println("Möchten Sie ein Produkt in Ihren Warenkorb tun? Ja/Nein")

                eingabe = readln().lowercase()

                if (eingabe == "ja") {

                    println("Welches Produkt möchten Sie hinzufügen?")
                    ausgewählterIndex = readln().toInt()
                    if (ausgewählterIndex > sortierteListe.size) {
                        throw Exception("Das Produkt haben wir nicht in unserem Sortiment.")
                    } else {
                        var produkt = sortierteListe[ausgewählterIndex - 1]
                        if (produkt.anzahl > 0) {
                            kunde.produktHinzufuegen(produkt)
                            produkt.anzahl--
                            println("${produkt.name} wurde erfolgreich hinzugefügt.")
                            nochEinProduktAbfrage = true
                            break
                        } else {
                            println("Das Produkt ist leider Ausverkauft.")
                            continue
                        }

                    }

                } else if (eingabe == "nein") {
                    break
                } else {
                    throw Exception("")
                }


            } catch (e: Exception) {
                println("Ungültige Eingabe.")
            }

        }


        var input2 = 0

        while (nochEinProduktAbfrage) {

            println("Möchten Sie ein weiteres Produkt hinzufügen? Ja/Nein")

            var input = readln().lowercase()

            if (input == "ja") {

                try {
                    println("Welches Produkt möchten Sie noch hinzufügen?")

                    input2 = readln().toInt()
                    if (input2 > sortierteListe.size) {

                        throw Exception("Dieses Produkt haben wir nicht in unserem Sortiment.")
                    } else {
                        var produkt = sortierteListe[input2 - 1]
                        if (produkt.anzahl > 0) {
                            kunde.produktHinzufuegen(produkt)
                            produkt.anzahl--
                            println("${produkt.name} wurde erfolgreich hinzugefügt.")

                        } else {
                            println("Das Produkt ist leider ausverkauft.")
                            continue
                        }
                    }


                } catch (e: Exception) {
                    println("Ungültige eingabe. ")
                }
            } else {
                break
            }


        }
    }

    fun sortiertNachPreis(kunde: KundenAccount) {

        var sortierteListe = alleProdukte.sortedBy { it.preis }

        for ((index, produkt) in sortierteListe.withIndex()) {
            println("[${index + 1}] $produkt")
        }

        var eingabe = ""
        var ausgewählterIndex = 0
        var nochEinProduktAbfrage = false

        while (true) {

            try {
                println("Möchten Sie ein Produkt in Ihren Warenkorb tun? Ja/Nein")

                eingabe = readln().lowercase()

                if (eingabe == "ja") {

                    println("Welches Produkt möchten Sie hinzufügen?")
                    ausgewählterIndex = readln().toInt()
                    if (ausgewählterIndex > sortierteListe.size) {
                        throw Exception("Das Produkt haben wir nicht in unserem Sortiment.")
                    } else {
                        var produkt = sortierteListe[ausgewählterIndex - 1]
                        if (produkt.anzahl > 0) {
                            kunde.produktHinzufuegen(produkt)
                            produkt.anzahl--
                            println("${produkt.name} wurde erfolgreich hinzugefügt.")
                            nochEinProduktAbfrage = true
                            break
                        } else {
                            println("Das Produkt ist leider Ausverkauft.")
                            continue
                        }

                    }

                } else if (eingabe == "nein") {
                    break
                } else {
                    throw Exception("")
                }


            } catch (e: Exception) {
                println("Ungültige Eingabe.")
            }

        }


        var input2 = 0

        while (nochEinProduktAbfrage) {

            println("Möchten Sie ein weiteres Produkt hinzufügen? Ja/Nein")

            var input = readln().lowercase()

            if (input == "ja") {

                try {
                    println("Welches Produkt möchten Sie noch hinzufügen?")

                    input2 = readln().toInt()
                    if (input2 > sortierteListe.size) {

                        throw Exception("Dieses Produkt haben wir nicht in unserem Sortiment.")
                    } else {
                        var produkt = sortierteListe[input2 - 1]
                        if (produkt.anzahl > 0) {
                            kunde.produktHinzufuegen(produkt)
                            produkt.anzahl--
                            println("${produkt.name} wurde erfolgreich hinzugefügt.")

                        } else {
                            println("Das Produkt ist leider ausverkauft.")
                            continue
                        }
                    }


                } catch (e: Exception) {
                    println("Ungültige eingabe. ")
                }
            } else {
                break
            }


        }
    }

    fun filterNachTshirts(kunde: KundenAccount) {

        var gefilterteListe = mutableListOf<Tshirt>()

        for (produkt in alleProdukte) {
            if (produkt is Tshirt) {
                gefilterteListe.add(produkt)
            }
        }

        for ((index, produkt) in gefilterteListe.withIndex()) {
            println("[${index + 1}] $produkt")
        }


        var eingabe = ""
        var ausgewählterIndex = 0
        var nochEinProduktAbfrage = false

        while (true) {

            try {
                println("Möchten Sie ein Produkt in Ihren Warenkorb tun? Ja/Nein")

                eingabe = readln().lowercase()

                if (eingabe == "ja") {

                    println("Welches Produkt möchten Sie hinzufügen?")
                    ausgewählterIndex = readln().toInt()
                    if (ausgewählterIndex > gefilterteListe.size) {
                        throw Exception("Das Produkt haben wir nicht in unserem Sortiment.")
                    } else {
                        var produkt = gefilterteListe[ausgewählterIndex - 1]
                        if (produkt.anzahl > 0) {
                            kunde.produktHinzufuegen(produkt)
                            produkt.anzahl--
                            println("${produkt.name} wurde erfolgreich hinzugefügt.")
                            nochEinProduktAbfrage = true
                            break
                        } else {
                            println("Das Produkt ist leider Ausverkauft.")
                            continue
                        }

                    }

                } else if (eingabe == "nein") {
                    break
                } else {
                    throw Exception("")
                }


            } catch (e: Exception) {
                println("Ungültige Eingabe.")
            }

        }


        var input2 = 0

        while (nochEinProduktAbfrage) {

            println("Möchten Sie ein weiteres Produkt hinzufügen? Ja/Nein")

            var input = readln().lowercase()

            if (input == "ja") {

                try {
                    println("Welches Produkt möchten Sie noch hinzufügen?")

                    input2 = readln().toInt()
                    if (input2 > gefilterteListe.size) {

                        throw Exception("Dieses Produkt haben wir nicht in unserem Sortiment.")
                    } else {
                        var produkt = gefilterteListe[input2 - 1]
                        if (produkt.anzahl > 0) {
                            kunde.produktHinzufuegen(produkt)
                            produkt.anzahl--
                            println("${produkt.name} wurde erfolgreich hinzugefügt.")

                        } else {
                            println("Das Produkt ist leider ausverkauft.")
                            continue
                        }
                    }


                } catch (e: Exception) {
                    println("Ungültige eingabe. ")
                }
            } else {
                break
            }


        }


    }

    fun filterNachPullover(kunde: KundenAccount) {

        var gefilterteListe = mutableListOf<Pullover>()

        for (produkt in alleProdukte) {
            if (produkt is Pullover) {
                gefilterteListe.add(produkt)
            }
        }

        for ((index, produkt) in gefilterteListe.withIndex()) {
            println("[${index + 1}] $produkt")
        }

        var eingabe = ""
        var ausgewählterIndex = 0
        var nochEinProduktAbfrage = false

        while (true) {

            try {
                println("Möchten Sie ein Produkt in Ihren Warenkorb tun? Ja/Nein")

                eingabe = readln().lowercase()

                if (eingabe == "ja") {

                    println("Welches Produkt möchten Sie hinzufügen?")
                    ausgewählterIndex = readln().toInt()
                    if (ausgewählterIndex > gefilterteListe.size) {
                        throw Exception("Das Produkt haben wir nicht in unserem Sortiment.")
                    } else {
                        var produkt = gefilterteListe[ausgewählterIndex - 1]
                        if (produkt.anzahl > 0) {
                            kunde.produktHinzufuegen(produkt)
                            produkt.anzahl--
                            println("${produkt.name} wurde erfolgreich hinzugefügt.")
                            nochEinProduktAbfrage = true
                            break
                        } else {
                            println("Das Produkt ist leider Ausverkauft.")
                            continue
                        }

                    }

                } else if (eingabe == "nein") {
                    break
                } else {
                    throw Exception("")
                }


            } catch (e: Exception) {
                println("Ungültige Eingabe.")
            }

        }


        var input2 = 0

        while (nochEinProduktAbfrage) {

            println("Möchten Sie ein weiteres Produkt hinzufügen? Ja/Nein")

            var input = readln().lowercase()

            if (input == "ja") {

                try {
                    println("Welches Produkt möchten Sie noch hinzufügen?")

                    input2 = readln().toInt()
                    if (input2 > gefilterteListe.size) {

                        throw Exception("Dieses Produkt haben wir nicht in unserem Sortiment.")
                    } else {
                        var produkt = gefilterteListe[input2 - 1]
                        if (produkt.anzahl > 0) {
                            kunde.produktHinzufuegen(produkt)
                            produkt.anzahl--
                            println("${produkt.name} wurde erfolgreich hinzugefügt.")

                        } else {
                            println("Das Produkt ist leider ausverkauft.")
                            continue
                        }
                    }


                } catch (e: Exception) {
                    println("Ungültige eingabe. ")
                }
            } else {
                break
            }


        }


    }

    fun filterNachHalsketten(kunde: KundenAccount) {

        var gefilterteListe = mutableListOf<Halsketten>()

        for (produkt in alleProdukte) {
            if (produkt is Halsketten) {
                gefilterteListe.add(produkt)
            }
        }

        for ((index, produkt) in gefilterteListe.withIndex()) {
            println("[${index + 1}] $produkt")
        }

        var eingabe = ""
        var ausgewählterIndex = 0
        var nochEinProduktAbfrage = false

        while (true) {

            try {
                println("Möchten Sie ein Produkt in Ihren Warenkorb tun? Ja/Nein")

                eingabe = readln().lowercase()

                if (eingabe == "ja") {

                    println("Welches Produkt möchten Sie hinzufügen?")
                    ausgewählterIndex = readln().toInt()
                    if (ausgewählterIndex > gefilterteListe.size) {
                        throw Exception("Das Produkt haben wir nicht in unserem Sortiment.")
                    } else {
                        var produkt = gefilterteListe[ausgewählterIndex - 1]
                        if (produkt.anzahl > 0) {
                            kunde.produktHinzufuegen(produkt)
                            produkt.anzahl--
                            println("${produkt.name} wurde erfolgreich hinzugefügt.")
                            nochEinProduktAbfrage = true
                            break
                        } else {
                            println("Das Produkt ist leider Ausverkauft.")
                            continue
                        }

                    }

                } else if (eingabe == "nein") {
                    break
                } else {
                    throw Exception("")
                }


            } catch (e: Exception) {
                println("Ungültige Eingabe.")
            }

        }


        var input2 = 0

        while (nochEinProduktAbfrage) {

            println("Möchten Sie ein weiteres Produkt hinzufügen? Ja/Nein")

            var input = readln().lowercase()

            if (input == "ja") {

                try {
                    println("Welches Produkt möchten Sie noch hinzufügen?")

                    input2 = readln().toInt()
                    if (input2 > gefilterteListe.size) {

                        throw Exception("Dieses Produkt haben wir nicht in unserem Sortiment.")
                    } else {
                        var produkt = gefilterteListe[input2 - 1]
                        if (produkt.anzahl > 0) {
                            kunde.produktHinzufuegen(produkt)
                            produkt.anzahl--
                            println("${produkt.name} wurde erfolgreich hinzugefügt.")

                        } else {
                            println("Das Produkt ist leider ausverkauft.")
                            continue
                        }
                    }


                } catch (e: Exception) {
                    println("Ungültige eingabe. ")
                }
            } else {
                break
            }


        }
    }

    fun filterNachArmbaender(kunde: KundenAccount) {

        var gefilterteListe = mutableListOf<Armbaender>()

        for (produkt in alleProdukte) {
            if (produkt is Armbaender) {
                gefilterteListe.add(produkt)
            }
        }

        for ((index, produkt) in gefilterteListe.withIndex()) {
            println("[${index + 1}] $produkt")
        }

        var eingabe = ""
        var ausgewählterIndex = 0
        var nochEinProduktAbfrage = false

        while (true) {

            try {
                println("Möchten Sie ein Produkt in Ihren Warenkorb tun? Ja/Nein")

                eingabe = readln().lowercase()

                if (eingabe == "ja") {

                    println("Welches Produkt möchten Sie hinzufügen?")
                    ausgewählterIndex = readln().toInt()
                    if (ausgewählterIndex > gefilterteListe.size) {
                        throw Exception("Das Produkt haben wir nicht in unserem Sortiment.")
                    } else {
                        var produkt = gefilterteListe[ausgewählterIndex - 1]
                        if (produkt.anzahl > 0) {
                            kunde.produktHinzufuegen(produkt)
                            produkt.anzahl--
                            println("${produkt.name} wurde erfolgreich hinzugefügt.")
                            nochEinProduktAbfrage = true
                            break
                        } else {
                            println("Das Produkt ist leider Ausverkauft.")
                            continue
                        }

                    }

                } else if (eingabe == "nein") {
                    break
                } else {
                    throw Exception("")
                }


            } catch (e: Exception) {
                println("Ungültige Eingabe.")
            }

        }


        var input2 = 0

        while (nochEinProduktAbfrage) {

            println("Möchten Sie ein weiteres Produkt hinzufügen? Ja/Nein")

            var input = readln().lowercase()

            if (input == "ja") {

                try {
                    println("Welches Produkt möchten Sie noch hinzufügen?")

                    input2 = readln().toInt()
                    if (input2 > gefilterteListe.size) {

                        throw Exception("Dieses Produkt haben wir nicht in unserem Sortiment.")
                    } else {
                        var produkt = gefilterteListe[input2 - 1]
                        if (produkt.anzahl > 0) {
                            kunde.produktHinzufuegen(produkt)
                            produkt.anzahl--
                            println("${produkt.name} wurde erfolgreich hinzugefügt.")

                        } else {
                            println("Das Produkt ist leider ausverkauft.")
                            continue
                        }
                    }


                } catch (e: Exception) {
                    println("Ungültige eingabe. ")
                }
            } else {
                break
            }


        }
    }

    fun userInterface(kunde: KundenAccount) {

        println("Herzlich Willkommen ${kunde.benutzername}")

        var eingabe = 0
        var helper = true

        while (helper) {

            while (true) {

                println(
                    """
            
                [1] Shop
                
                [2] Warenkorb ansehen
                
                [3] Warenkorb bezahlen
                
                [4] Ausloggen
                
             
                """.trimIndent()
                )

                try {

                    eingabe = readln().toInt()
                    if (eingabe !in 1..4) {
                        throw Exception()
                    }


                } catch (e: Exception) {
                    println("Ungültige Eingabe. 1 - 4 !")
                }

                when (eingabe) {

                    1 -> shop(kunde)

                    2 -> kunde.warenKorbAnzeigen()

                    3 -> warenKorbBezahlen(kunde)

                    4 -> {
                        helper = false
                        break
                    }
                }
            }


        }
    }

    fun managerInterface(manager: ManagerAccount) {

        println("Herzlich Willkommen ${manager.benutzername}")
        var eingabe = 0

        while (true) {

            println(
                """ 
            
            [1] Produkt hinzufügen
            [2] Produkt entfernen
            [3] Produkt nachbestellen
            
            [4] Ausloggen
           
        """.trimIndent()
            )

            eingabe = readln().toInt()

            when (eingabe) {

                1 -> manager.produktErstellen(alleProdukte)
                2 -> manager.produktLoeschen(alleProdukte)
                3 -> manager.produktErstellen(alleProdukte)//TODO punkt 3


            }

        }
    }

    fun warenKorbBezahlen(kunde: KundenAccount) {

        println("Wie möchten Sie bezahlen?")
        var eingabe = 0
        while (true) {

            try {

                println(
                    """
                
                [1] PayPal
                [2] ApplePay
                [3] Sofort-Überweisung
                [4] Abbrechen
                
            """.trimIndent()
                )

                eingabe = readln().toInt()
                if (eingabe > 4) {
                    throw Exception("")
                }

                when (eingabe) {

                    1 -> {
                        println("Sie werden weitergeleitet...")
                        Thread.sleep(4000)
                        println("Herzlich Willkommen zu PayPal")
                        println("Benutzername: ")
                        var benutzerName = readln()
                        println("Passwort: ")
                        var passwort = readln()
                        if (benutzerName == kunde.benutzername) {

                            if (passwort == kunde.passwort) {

                                println("Der Betrag ist: ${kunde.gesamtWert()} EUR")

                                println()

                                println("Bezahlen? Ja/Nein")

                                var auswahl = readln().lowercase()

                                if (auswahl == "ja") {
                                    println("Bestellung wird bearbeitet...")
                                    Thread.sleep(3000)
                                    println()
                                    println("Bestellung erfolgreich.")
                                    kunde.warenKorb.clear()
                                    break

                                } else {
                                    println("Sie werden zurückgeleitet.")
                                    break
                                }

                            } else {
                                println("Falsches Passwort.")
                            }
                        } else {
                            println("Es existiert kein Account mit dem Benutzernamen.")
                            continue
                        }

                    }

                    2 -> {
                        println("Sie werden weitergeleitet...")
                        Thread.sleep(4000)
                        println("Herzlich Willkommen zu ApplePay")
                        println("Benutzername: ")
                        var benutzerName = readln()
                        println("Passwort: ")
                        var passwort = readln()
                        if (benutzerName == kunde.benutzername) {

                            if (passwort == kunde.passwort) {

                                println("Der Betrag ist: ${kunde.gesamtWert()} EUR")

                                println()

                                println("Bezahlen? Ja/Nein")

                                var auswahl = readln().lowercase()

                                if (auswahl == "ja") {
                                    println("Bestellung wird bearbeitet...")
                                    Thread.sleep(3000)
                                    println()
                                    println("Bestellung erfolgreich.")
                                    kunde.warenKorb.clear()
                                    break

                                } else {
                                    println("Sie werden zurückgeleitet.")
                                    break
                                }

                            } else {
                                println("Falsches Passwort.")
                            }
                        } else {
                            println("Es existiert kein Account mit dem Benutzernamen.")
                            continue
                        }

                    }

                    3 -> {
                        println("Sie werden weitergeleitet...")
                        Thread.sleep(4000)
                        println("Herzlich Willkommen zur Sofort-Überweisung")
                        println("Benutzername: ")
                        var benutzerName = readln()
                        println("Passwort: ")
                        var passwort = readln()
                        if (benutzerName == kunde.benutzername) {

                            if (passwort == kunde.passwort) {

                                println("Der Betrag ist: ${kunde.gesamtWert()} EUR")

                                println()

                                println("Bezahlen? Ja/Nein")

                                var auswahl = readln().lowercase()

                                if (auswahl == "ja") {
                                    println("Bestellung wird bearbeitet...")
                                    Thread.sleep(3000)
                                    println()
                                    println("Bestellung erfolgreich.")
                                    kunde.warenKorb.clear()
                                    break

                                } else {
                                    println("Sie werden zurückgeleitet.")
                                    Thread.sleep(2000)
                                    break
                                }

                            } else {
                                println("Falsches Passwort.")
                            }
                        } else {
                            println("Es existiert kein Account mit dem Benutzernamen.")
                            continue
                        }
                    }

                    4 -> {
                        break
                    }
                }

            } catch (e: Exception) {
                println("Ungültige Eingabe.")
            }


        }

    }
}


