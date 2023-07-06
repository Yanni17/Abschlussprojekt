import kotlin.math.log


fun main() {

    var macherShop = Shop()
    var logIn = LogIn()

    welcomeScreen(logIn, macherShop)


}

fun welcomeScreen(login: LogIn, shop: Shop) {

    var auswahl = 0

    while (true) {

        while (true) {

            println("Herzlich Willkommen im Áme Noire Store.")
            println(
                """
            
                [1] Einloggen als Kunde
                
                [2] Einloggen als Manager
                
                [3] Konto erstellen
            
            
                """.trimIndent()
            )

            try {

                auswahl = readln().toInt()

                if (auswahl !in 1 until 4) {
                    throw Exception()

                }

            } catch (e: Exception) {
                println("Ungültige Eingabe. 1 - 3 !")
                continue
            }

            when (auswahl) {

                1 -> {
                    var kunde = login.logInKunde()
                    if (kunde != null) {
                        shop.userInterface(kunde)
                        break

                    }
                }

                2 -> {
                    var manager = login.logInManager()
                    if (manager != null) {
                        shop.managerInterface(manager)
                        break


                    }
                }

                3 -> {
                    var neuerKunde = login.register()
                    login.logInKunde()
                    if (neuerKunde.alter < 12) {
                        println("Sie sind leider zu Jung um einzukaufen.")
                        continue
                    } else {
                        shop.userInterface(neuerKunde)
                        break


                    }

                }
            }

        }
    }

}


