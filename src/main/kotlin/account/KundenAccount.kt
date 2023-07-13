package account

import green
import produkt.Produkt
import red
import reset
import yellow

class KundenAccount(
    benutzerName: String,
    passwort: String,
    var alter: Int,
    var zahlungsMethode: String
) : Account(benutzerName, passwort) {


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

        var helper = false

        if (warenKorb.isEmpty()) {
            println("Dein Warenkorb ist leer.")
            Thread.sleep(2000)
        } else {
            println("""
                
                $yellow                                ＷＡＲＥＮＫＯＲＢ $reset
                          
            """.trimIndent())
            warenKorb.forEachIndexed { index, produkt ->
                println("${index + 1}) $produkt")
                helper = true
            }
        }
        println()
        println("Aktueller Warenkorb Betrag : %.2f EUR".format(gesamtWert()))

        while (helper) {

            println("$yellow Möchten Sie einen Artikel entfernen? Ja/Nein.$reset")

            var auswahl = readln().lowercase()

            try {


                if (auswahl == "ja") {

                    for ((index, produkt) in warenKorb.withIndex()) {
                        println("[${index + 1}] $produkt")
                    }
                    println("$yellow Welches Produkt möchten Sie entfernen?$reset")

                    var auswahl2 = 0

                    auswahl2 = readln().toInt()

                    if (auswahl2 > warenKorb.size) {

                        throw Exception("${red}Das haben Sie nicht im Warenkorb.$reset")

                    } else {

                        var produkt = warenKorb[auswahl2 - 1]
                        produktEnfernen(produkt)
                        produkt.anzahl++
                        println("${green}Produkt erfolgreich entfernt.$reset")
                        break
                    }


                } else if (auswahl == "nein") {
                    break
                } else {
                    throw Exception("${red}Ja oder Nein.!$reset")
                }
            } catch (e: Exception) {
                println("${red}Ungültige Eingabe!$reset")
                e.message
            }
        }
    }

    fun produktBewerten(alleArtikel: MutableList<Produkt>) {

        var auswahl = ""

        for ((index, produkt) in alleArtikel.withIndex()) {
            println("[${index + 1}] $produkt")
        }
        while (true) {
            try {


                println("$yellow Welches Produkt möchten Sie bewerten?$reset")

                var eingabe = readln().toInt()
                if (eingabe > alleArtikel.size) {
                    throw Exception("${red}Ungültige Zahl.$reset")
                } else {
                    var ausgewaehltesProdukt = alleArtikel[eingabe - 1]
                    println("$yellow Wie viele Sterne möchten Sie dem Produkt geben?$reset")
                    var bewertung = readln().toDouble()
                    if (bewertung > 5) {
                        throw Exception("${red}Maximal 5 Sterne.$reset")
                    } else {
                        ausgewaehltesProdukt.bewertung.add(bewertung)
                        ausgewaehltesProdukt.averageAusrechnen = ausgewaehltesProdukt.bewertung.average()
                        println("${green}Vielen Dank für Ihre Bewertung.$reset")
                        Thread.sleep(2000)
                        break

                    }

                }


            } catch (e: Exception) {
                println(e.message)
                continue
            }
        }

    }

}
