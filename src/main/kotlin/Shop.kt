import account.KundenAccount
import account.ManagerAccount
import produkt.*

class Shop {

    var alleProdukte = mutableListOf<Produkt>(

        Tshirt("Áme Noire","Classic T-Shirt", 22.99, bewertung = mutableListOf(), 10, "M"),
        Tshirt("Áme Noire","Basic T-Shirt", 19.99, bewertung = mutableListOf(), 10, "M"),
        Tshirt("Áme Noire","Limited T-Shirt", 39.99, bewertung = mutableListOf(), 10, "M"),
        Pullover("Áme Noire","Classic Pullover", 29.99, bewertung = mutableListOf(), 10, "M"),
        Pullover("Áme Noire","Basic Pullover", 24.99, bewertung = mutableListOf(), 10, "M"),
        Pullover("Áme Noire","Limited Pullover", 49.99, bewertung = mutableListOf(), 10, "M"),
        Halskette("Áme Noire","Edelstahl Halskette", 14.99, bewertung = mutableListOf(), 10, "Silber"),
        Halskette("Áme Noire","Vintage Halskette", 18.99, bewertung = mutableListOf(), 10, "Braun"),
        Halskette("Áme Noire","Modern Halskette", 21.99, bewertung = mutableListOf(), 10, "Schwarz"),
        Armband("Áme Noire","Edelstahl Armband", 12.99, bewertung = mutableListOf(), 10, "Schwarz"),
        Armband("Áme Noire","Vintage Armband", 16.99, bewertung = mutableListOf(), 10, "Schwarz"),
        Armband("Áme Noire","Modern Armband", 13.99, bewertung = mutableListOf(), 10, "Braun")
    )

    fun shop(kunde: KundenAccount) {

        println("""
                            
        $yellow                                ＡＬＬＥ ＰＲＯＤＵＫＴＥ $reset
                       
        """.trimIndent())

        for ((index, produkt) in alleProdukte.withIndex()) {
            println("[${index + 1}] $produkt")
        }
        println()

        while (true) {

            println(
                """
                                           $yellow ＦＩＬＴＥＲ ／ ＳＯＲＴＩＥＲＵＮＧ$reset
            
                [1] Filtern nach Tshirts            [2] Filtern nach Halsketten         [3] Sortieren nach Preis (absteigend)
                
                [4] Filtern nach Pullover           [5] Filtern nach Armbänder          [6] Sortieren nach Alphabet
                
                
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

                    2 -> filterNachHalsketten(kunde)

                    3 -> sortiertNachPreis(kunde)

                    4 -> filterNachPullover(kunde)

                    5 -> filterNachArmbaender(kunde)

                    6 -> sortiertNachAlphabet(kunde)

                    7 -> break


                }


            } catch (e: Exception) {
                println("$red Ungültige Eingabe$reset")
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

        println("""
                            
        $yellow                                 ＡＬＰＨＡＢＥＴＩＳＣＨ $reset
                       
        """.trimIndent())

        for ((index, produkt) in sortierteListe.withIndex()) {
            println("[${index + 1}] $produkt")
        }

        var eingabe = ""
        var ausgewählterIndex = 0
        var nochEinProduktAbfrage = false

        while (true) {

            try {
                println()
                println("$yellow Möchten Sie ein Produkt in Ihren Warenkorb tun? Ja/Nein$reset")

                eingabe = readln().lowercase()

                if (eingabe == "ja") {

                    println("$yellow Welches Produkt möchten Sie hinzufügen?$reset")
                    ausgewählterIndex = readln().toInt()
                    if (ausgewählterIndex > sortierteListe.size) {
                        throw Exception("$red Das Produkt haben wir nicht in unserem Sortiment.$reset")
                    } else {
                        var produkt = sortierteListe[ausgewählterIndex - 1]
                        if (produkt.anzahl > 0) {
                            kunde.produktHinzufuegen(produkt)
                            produkt.anzahl--
                            println("$green ${produkt.name} wurde erfolgreich hinzugefügt.$reset")
                            nochEinProduktAbfrage = true
                            break
                        } else {
                            println("$red Das Produkt ist leider Ausverkauft.$reset")
                            continue
                        }

                    }

                } else if (eingabe == "nein") {
                    break
                } else {
                    throw Exception("")
                }


            } catch (e: Exception) {
                println("$red Ungültige Eingabe.$reset")
            }

        }


        var input2 = 0

        while (nochEinProduktAbfrage) {

            println("$yellow Möchten Sie ein weiteres Produkt hinzufügen? Ja/Nein$reset")

            var input = readln().lowercase()

            if (input == "ja") {

                try {
                    println("$yellow Welches Produkt möchten Sie noch hinzufügen?$reset")

                    input2 = readln().toInt()
                    if (input2 > sortierteListe.size) {

                        throw Exception("$red Dieses Produkt haben wir nicht in unserem Sortiment.$reset")
                    } else {
                        var produkt = sortierteListe[input2 - 1]
                        if (produkt.anzahl > 0) {
                            kunde.produktHinzufuegen(produkt)
                            produkt.anzahl--
                            println("$green ${produkt.name} wurde erfolgreich hinzugefügt.$reset")

                        } else {
                            println("$red Das Produkt ist leider ausverkauft.$reset")
                            continue
                        }
                    }


                } catch (e: Exception) {
                    println(e.message)
                }
            } else {
                break
            }


        }
    }

    fun sortiertNachPreis(kunde: KundenAccount) {

        var sortierteListe = alleProdukte.sortedBy { it.preis }

        println("""
                            
        $yellow                                ＰＲＥＩＳ （ＡＢＳＴＥＩＧＥＮＤ） $reset
                       
        """.trimIndent())


        for ((index, produkt) in sortierteListe.withIndex()) {
            println("[${index + 1}] $produkt")
        }

        var eingabe = ""
        var ausgewählterIndex = 0
        var nochEinProduktAbfrage = false

        while (true) {

            try {
                println("$yellow Möchten Sie ein Produkt in Ihren Warenkorb tun? Ja/Nein$reset")

                eingabe = readln().lowercase()

                if (eingabe == "ja") {

                    println("$yellow Welches Produkt möchten Sie hinzufügen?$reset")
                    ausgewählterIndex = readln().toInt()
                    if (ausgewählterIndex > sortierteListe.size) {
                        throw Exception("$red Das Produkt haben wir nicht in unserem Sortiment.$reset")
                    } else {
                        var produkt = sortierteListe[ausgewählterIndex - 1]
                        if (produkt.anzahl > 0) {
                            kunde.produktHinzufuegen(produkt)
                            produkt.anzahl--
                            println("$green ${produkt.name} wurde erfolgreich hinzugefügt.$reset")
                            nochEinProduktAbfrage = true
                            break
                        } else {
                            println("$red Das Produkt ist leider Ausverkauft.$reset")
                            continue
                        }

                    }

                } else if (eingabe == "nein") {
                    break
                } else {
                    throw Exception("")
                }


            } catch (e: Exception) {
                println("$red Ungültige Eingabe.$reset")
            }

        }


        var input2 = 0

        while (nochEinProduktAbfrage) {

            println("$yellow Möchten Sie ein weiteres Produkt hinzufügen? Ja/Nein$reset")

            var input = readln().lowercase()

            if (input == "ja") {

                try {

                    println("$yellow Welches Produkt möchten Sie noch hinzufügen?$reset")

                    input2 = readln().toInt()
                    if (input2 > sortierteListe.size) {

                        throw Exception("$red Dieses Produkt haben wir nicht in unserem Sortiment.$reset")
                    } else {
                        var produkt = sortierteListe[input2 - 1]
                        if (produkt.anzahl > 0) {
                            kunde.produktHinzufuegen(produkt)
                            produkt.anzahl--
                            println("$green ${produkt.name} wurde erfolgreich hinzugefügt.$reset")

                        } else {
                            println("$red Das Produkt ist leider ausverkauft.$reset")
                            continue
                        }
                    }


                } catch (e: Exception) {
                    println("$red Ungültige eingabe.$reset")
                }
            } else {
                break
            }


        }
    }

    fun filterNachTshirts(kunde: KundenAccount) {

        var gefilterteListe = mutableListOf<Tshirt>()

        println("""
                            
        $yellow                                   Ｔ－ＳＨＩＲＴS $reset
                       
        """.trimIndent())

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
                println("$yellow Möchten Sie ein Produkt in Ihren Warenkorb tun? Ja/Nein$reset")

                eingabe = readln().lowercase()

                if (eingabe == "ja") {

                    println("$yellow Welches Produkt möchten Sie hinzufügen?$reset")
                    ausgewählterIndex = readln().toInt()
                    if (ausgewählterIndex > gefilterteListe.size) {
                        throw Exception("$red Das Produkt haben wir nicht in unserem Sortiment.$reset")
                    } else {
                        var produkt = gefilterteListe[ausgewählterIndex - 1]
                        if (produkt.anzahl > 0) {
                            kunde.produktHinzufuegen(produkt)
                            produkt.anzahl--
                            println("$green ${produkt.name} wurde erfolgreich hinzugefügt.$reset")
                            nochEinProduktAbfrage = true
                            break
                        } else {
                            println("$red Das Produkt ist leider Ausverkauft.$reset")
                            continue
                        }

                    }

                } else if (eingabe == "nein") {
                    break
                } else {
                    throw Exception("")
                }


            } catch (e: Exception) {
                println("$red Ungültige Eingabe.$reset")
            }

        }


        var input2 = 0

        while (nochEinProduktAbfrage) {

            println("$yellow Möchten Sie ein weiteres Produkt hinzufügen? Ja/Nein$reset")

            var input = readln().lowercase()

            if (input == "ja") {

                try {
                    println("$yellow Welches Produkt möchten Sie noch hinzufügen?$reset")

                    input2 = readln().toInt()
                    if (input2 > gefilterteListe.size) {

                        throw Exception("$red Dieses Produkt haben wir nicht in unserem Sortiment.$reset")
                    } else {
                        var produkt = gefilterteListe[input2 - 1]
                        if (produkt.anzahl > 0) {
                            kunde.produktHinzufuegen(produkt)
                            produkt.anzahl--
                            println("$green ${produkt.name} wurde erfolgreich hinzugefügt.$reset")

                        } else {
                            println("$red Das Produkt ist leider ausverkauft.$reset")
                            continue
                        }
                    }


                } catch (e: Exception) {
                    println("$red Ungültige eingabe.$reset")
                }
            } else {
                break
            }


        }


    }

    fun filterNachPullover(kunde: KundenAccount) {

        var gefilterteListe = mutableListOf<Pullover>()

        println("""
                            
        $yellow                                   ＰＵＬＬＯＶＥＲ $reset
                       
        """.trimIndent())

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
                println("$yellow Möchten Sie ein Produkt in Ihren Warenkorb tun? Ja/Nein$reset")

                eingabe = readln().lowercase()

                if (eingabe == "ja") {

                    println("$yellow Welches Produkt möchten Sie hinzufügen?$reset")
                    ausgewählterIndex = readln().toInt()
                    if (ausgewählterIndex > gefilterteListe.size) {
                        throw Exception("$red Das Produkt haben wir nicht in unserem Sortiment.$reset")
                    } else {
                        var produkt = gefilterteListe[ausgewählterIndex - 1]
                        if (produkt.anzahl > 0) {
                            kunde.produktHinzufuegen(produkt)
                            produkt.anzahl--
                            println("$green ${produkt.name} wurde erfolgreich hinzugefügt.$reset")
                            nochEinProduktAbfrage = true
                            break
                        } else {
                            println("$red Das Produkt ist leider Ausverkauft.$reset")
                            continue
                        }

                    }

                } else if (eingabe == "nein") {
                    break
                } else {
                    throw Exception("")
                }


            } catch (e: Exception) {
                println("$red Ungültige Eingabe.$reset")
            }

        }


        var input2 = 0

        while (nochEinProduktAbfrage) {

            println("$yellow Möchten Sie ein weiteres Produkt hinzufügen? Ja/Nein$reset")

            var input = readln().lowercase()

            if (input == "ja") {

                try {
                    println("$yellow Welches Produkt möchten Sie noch hinzufügen?$reset")

                    input2 = readln().toInt()
                    if (input2 > gefilterteListe.size) {

                        throw Exception("$red Dieses Produkt haben wir nicht in unserem Sortiment.$reset")
                    } else {
                        var produkt = gefilterteListe[input2 - 1]
                        if (produkt.anzahl > 0) {
                            kunde.produktHinzufuegen(produkt)
                            produkt.anzahl--
                            println("$green ${produkt.name} wurde erfolgreich hinzugefügt.$reset")

                        } else {
                            println("$red Das Produkt ist leider ausverkauft.$reset")
                            continue
                        }
                    }


                } catch (e: Exception) {
                    println("$red Ungültige eingabe.$reset")
                }
            } else {
                break
            }


        }


    }

    fun filterNachHalsketten(kunde: KundenAccount) {

        var gefilterteListe = mutableListOf<Halskette>()

        println("""
                            
        $yellow                                  ＨＡＬＳＫＥＴＴＥＮ $reset
                       
        """.trimIndent())

        for (produkt in alleProdukte) {
            if (produkt is Halskette) {
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
                println("$yellow Möchten Sie ein Produkt in Ihren Warenkorb tun? Ja/Nein$reset")

                eingabe = readln().lowercase()

                if (eingabe == "ja") {

                    println("$yellow Welches Produkt möchten Sie hinzufügen?$reset")
                    ausgewählterIndex = readln().toInt()
                    if (ausgewählterIndex > gefilterteListe.size) {
                        throw Exception("$red Das Produkt haben wir nicht in unserem Sortiment.$reset")
                    } else {
                        var produkt = gefilterteListe[ausgewählterIndex - 1]
                        if (produkt.anzahl > 0) {
                            kunde.produktHinzufuegen(produkt)
                            produkt.anzahl--
                            println("$green ${produkt.name} wurde erfolgreich hinzugefügt.$reset")
                            nochEinProduktAbfrage = true
                            break
                        } else {
                            println("$red Das Produkt ist leider Ausverkauft.$reset")
                            continue
                        }

                    }

                } else if (eingabe == "nein") {
                    break
                } else {
                    throw Exception("")
                }


            } catch (e: Exception) {
                println("$red Ungültige Eingabe.$reset")
            }

        }


        var input2 = 0

        while (nochEinProduktAbfrage) {

            println("$yellow Möchten Sie ein weiteres Produkt hinzufügen? Ja/Nein$reset")

            var input = readln().lowercase()

            if (input == "ja") {

                try {
                    println("$yellow Welches Produkt möchten Sie noch hinzufügen?$reset")

                    input2 = readln().toInt()
                    if (input2 > gefilterteListe.size) {

                        throw Exception("$red Dieses Produkt haben wir nicht in unserem Sortiment.$reset")
                    } else {
                        var produkt = gefilterteListe[input2 - 1]
                        if (produkt.anzahl > 0) {
                            kunde.produktHinzufuegen(produkt)
                            produkt.anzahl--
                            println("$green ${produkt.name} wurde erfolgreich hinzugefügt.$reset")

                        } else {
                            println("$red Das Produkt ist leider ausverkauft.$reset")
                            continue
                        }
                    }


                } catch (e: Exception) {
                    println("$red Ungültige eingabe.$reset ")
                }
            } else {
                break
            }


        }
    }

    fun filterNachArmbaender(kunde: KundenAccount) {

        var gefilterteListe = mutableListOf<Armband>()

        println("""
                            
        $yellow                                  ＡＲＭＢＡＥＮＤＥＲ $reset
                       
        """.trimIndent())

        for (produkt in alleProdukte) {
            if (produkt is Armband) {
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
                println("$yellow Möchten Sie ein Produkt in Ihren Warenkorb tun? Ja/Nein$reset")

                eingabe = readln().lowercase()

                if (eingabe == "ja") {

                    println("$yellow Welches Produkt möchten Sie hinzufügen?$reset")
                    ausgewählterIndex = readln().toInt()
                    if (ausgewählterIndex > gefilterteListe.size) {
                        throw Exception("$red Das Produkt haben wir nicht in unserem Sortiment.$reset")
                    } else {
                        var produkt = gefilterteListe[ausgewählterIndex - 1]
                        if (produkt.anzahl > 0) {
                            kunde.produktHinzufuegen(produkt)
                            produkt.anzahl--
                            println("$green ${produkt.name} wurde erfolgreich hinzugefügt.$reset")
                            nochEinProduktAbfrage = true
                            break
                        } else {
                            println("$red Das Produkt ist leider Ausverkauft.$reset")
                            continue
                        }

                    }

                } else if (eingabe == "nein") {
                    break
                } else {
                    throw Exception("")
                }


            } catch (e: Exception) {
                println("$red Ungültige Eingabe.$reset")
            }

        }


        var input2 = 0

        while (nochEinProduktAbfrage) {

            println("$yellow Möchten Sie ein weiteres Produkt hinzufügen? Ja/Nein$reset")

            var input = readln().lowercase()

            if (input == "ja") {

                try {
                    println("$yellow Welches Produkt möchten Sie noch hinzufügen?$reset")

                    input2 = readln().toInt()
                    if (input2 > gefilterteListe.size) {

                        throw Exception("$red Dieses Produkt haben wir nicht in unserem Sortiment.$reset")
                    } else {
                        var produkt = gefilterteListe[input2 - 1]
                        if (produkt.anzahl > 0) {
                            kunde.produktHinzufuegen(produkt)
                            produkt.anzahl--
                            println("$green ${produkt.name} wurde erfolgreich hinzugefügt.$reset")

                        } else {
                            println("$red Das Produkt ist leider ausverkauft.$reset")
                            continue
                        }
                    }


                } catch (e: Exception) {
                    println("$red Ungültige eingabe.$reset ")
                }
            } else {
                break
            }


        }
    }

    fun userInterface(kunde: KundenAccount) {

        var eingabe = 0
        var helper = true

        while (helper) {

            while (true) {

                println(
                    """
                _____________________________
                        𝐴́𝑀𝐸 𝑁𝑂𝐼𝑅𝐸.
                        
                1) Shop
                
                2) Warenkorb ansehen
                
                3) Warenkorb bezahlen
                
                4) Produkt bewerten
                
                _____________________________
                
                5) Ausloggen
                
             
                """.trimIndent()
                )

                try {

                    eingabe = readln().toInt()
                    if (eingabe !in 1..5) {
                        throw Exception()
                    }


                } catch (e: Exception) {
                    println("$red Ungültige Eingabe. 1 - 4 !$reset")
                }

                when (eingabe) {

                    1 -> shop(kunde)

                    2 -> kunde.warenKorbAnzeigen()

                    3 -> {
                        if (kunde.warenKorb.isEmpty()){
                            println("Sie haben nichts in Ihrem Warenkorb.")
                            Thread.sleep(2000)
                            break
                        }else {

                            warenKorbBezahlen(kunde)
                        }
                    }

                    4 -> kunde.produktBewerten(alleProdukte)


                    5 -> {
                        helper = false
                        break
                    }
                }
            }


        }
    }

    fun managerInterface(manager: ManagerAccount) {

        var eingabe = 0

        while (true) {

            try {

                println(
                    """
                _____________________________
                        𝐴́𝑀𝐸 𝑁𝑂𝐼𝑅𝐸.
                          𝐴𝑑𝑚𝑖𝑛
                        
                1) Alle Produkte ansehen
                
                2) Produkt erstellen
                
                3) Produkt löschen
                
                4) Produkt nachbestellen
                
                _____________________________
                
                5) Ausloggen
                
             
                """.trimIndent()
                )

                eingabe = readln().toInt()

                if (eingabe > 5) {
                    throw Exception()
                }


            } catch (e: Exception) {
                println("$red Ungültige Eingabe$reset")
                continue
            }


            when (eingabe) {

                1 -> manager.alleProdukteAnsehen(alleProdukte)
                2 -> manager.produktErstellen(alleProdukte)
                3 -> manager.produktLoeschen(alleProdukte)
                4 -> manager.produktNachbestellen(alleProdukte)
                5 -> break


            }

        }
    }

    fun warenKorbBezahlen(kunde: KundenAccount) {

        println()
        println("$yellow Wie möchten Sie bezahlen?$reset")
        var eingabe = 0
        while (true) {

            try {

                println(
                    """
                
                1) PayPal
                2) ApplePay
                3) Sofort-Überweisung
                
                4) Abbrechen
                
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
                        println()
                        println("Herzlich Willkommen zu PayPal")
                        println()
                        println("Benutzername: ")
                        var benutzerName = readln()
                        println("Passwort: ")
                        var passwort = readln()
                        if (benutzerName == kunde.benutzername) {

                            if (passwort == kunde.passwort) {

                                println("Der Betrag ist: %.2f EUR".format(kunde.gesamtWert()))

                                println()

                                println("$yellow Bezahlen? Ja/Nein$reset")

                                var auswahl = readln().lowercase()

                                if (auswahl == "ja") {
                                    println("Bestellung wird bearbeitet...")
                                    Thread.sleep(3000)
                                    println()
                                    println("$green Bestellung erfolgreich.$reset")
                                    kunde.warenKorb.clear()
                                    break

                                } else {
                                    Thread.sleep(2000)
                                    println("Sie werden zurückgeleitet.")
                                    break
                                }

                            } else {
                                println("$red Falsches Passwort.$reset")
                            }
                        } else {
                            println("$red Es existiert kein Account mit dem Benutzernamen.$reset")
                            continue
                        }

                    }

                    2 -> {
                        println("Sie werden weitergeleitet...")
                        Thread.sleep(4000)
                        println()
                        println("Herzlich Willkommen zu ApplePay")
                        println("Benutzername: ")
                        var benutzerName = readln()
                        println("Passwort: ")
                        var passwort = readln()
                        if (benutzerName == kunde.benutzername) {

                            if (passwort == kunde.passwort) {

                                println("Der Betrag ist: %.2f EUR".format(kunde.gesamtWert()))

                                println()

                                println("$yellow Bezahlen? Ja/Nein$reset")

                                var auswahl = readln().lowercase()

                                if (auswahl == "ja") {
                                    println("Bestellung wird bearbeitet...")
                                    Thread.sleep(3000)
                                    println()
                                    println("$green Bestellung erfolgreich.$reset")
                                    kunde.warenKorb.clear()
                                    break

                                } else {
                                    println("Sie werden zurückgeleitet.")
                                    break
                                }

                            } else {
                                println("$red Falsches Passwort.$reset")
                            }
                        } else {
                            println("$red Es existiert kein Account mit dem Benutzernamen.$reset")
                            continue
                        }

                    }

                    3 -> {
                        println("Sie werden weitergeleitet...")
                        Thread.sleep(4000)
                        println()
                        println("Herzlich Willkommen zur Sofort-Überweisung")
                        println("Benutzername: ")
                        var benutzerName = readln()
                        println("Passwort: ")
                        var passwort = readln()
                        if (benutzerName == kunde.benutzername) {

                            if (passwort == kunde.passwort) {

                                println("Der Betrag ist: %.2f EUR".format(kunde.gesamtWert()))

                                println()

                                println("$yellow Bezahlen? Ja/Nein$reset")

                                var auswahl = readln().lowercase()

                                if (auswahl == "ja") {
                                    println("Bestellung wird bearbeitet...")
                                    Thread.sleep(3000)
                                    println()
                                    println("$green Bestellung erfolgreich.$reset")
                                    kunde.warenKorb.clear()
                                    break

                                } else {
                                    println("Sie werden zurückgeleitet.")
                                    Thread.sleep(2000)
                                    break
                                }

                            } else {
                                println("$red Falsches Passwort.$reset")
                            }
                        } else {
                            println("$red Es existiert kein Account mit dem Benutzernamen.$reset")
                            continue
                        }
                    }

                    4 -> {
                        break
                    }
                }

            } catch (e: Exception) {
                println("$red Ungültige Eingabe.$reset")
            }


        }

    }
}


