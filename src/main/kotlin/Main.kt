import kotlin.math.log
import kotlin.system.exitProcess


fun main() {

    var macherShop = Shop()
    var logIn = LogIn()

    welcomeScreen(logIn, macherShop)

}

fun welcomeScreen(login: LogIn, shop: Shop) {

    var auswahl = 0

    while (true) {

        while (true) {

            println()
            println(
                """
                _____________________________
                        𝑊𝐸𝐿𝐶𝑂𝑀𝐸!
            
                1) Einloggen als Kunde
                
                2) Einloggen als Manager
                
                3) Konto erstellen
                
                _____________________________
                
                4) Programm beenden
            
            
                """.trimIndent()
            )

            try {

                auswahl = readln().toInt()

                if (auswahl !in 1 until 5) {
                    throw Exception()

                }

            } catch (e: Exception) {
                println("$red Ungültige Eingabe. 1 - 4 ! $reset")
                continue
            }

            when (auswahl) {

                1 -> {
                    var kunde = login.logInKunde()
                    if (kunde != null) {
                        shop.userInterface(kunde)
                        break

                    } else {
                        continue
                    }
                }

                2 -> {
                    var manager = login.logInManager()
                    if (manager != null) {
                        shop.managerInterface(manager)
                        break


                    } else {
                        continue
                    }
                }

                3 -> {
                    var neuerKunde = login.register()
                    if (neuerKunde.alter < 12) {
                        println("$red Sie sind leider zu Jung um einzukaufen.$reset")
                        continue
                    } else if (neuerKunde.alter > 12) {
                        shop.userInterface(neuerKunde)
                        break

                    } else {
                        continue
                    }

                }

                4 -> {
                    exitProcess(0)
                }
            }

        }
    }

}

