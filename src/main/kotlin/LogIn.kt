class LogIn {


    var accountDaten = mutableListOf<Account>()

    var isAdmin = false

    fun register(): KundenAccount {

        var passwort = ""
        println("Registration!")


        while (true) {

            println("Benutzername:")
            var benutzerName = readln()

            for (account in accountDaten) {
                if (account is KundenAccount) {
                    if (benutzerName == account.benutzername) {
                        println("Benutzername existiert bereits.")
                        continue
                    }
                }
            }

            try {

                while (true) {
                    var minLength = 10
                    println("Passwort:  ")
                    passwort = readln()
                    if (passwort == passwort.capitalize()) {
                        if (passwort.length >= minLength) {
                            break
                        } else {
                            println("Das Passwort muss mindestens 10 Zeichen haben.")
                            continue
                        }
                    } else {
                        println("Der Anfangsbuchstabe muss Groß sein.")
                        continue
                    }
                }

            } catch (e: Exception) {
                println("Ungültige Eingabe")
            }

            var alter = 0

            while (true) {

                try {

                    println("Alter: ")

                    alter = readln().toInt()

                    if (alter < 0 || alter !in 1..100) {
                        println("Sind Sie ein Mensch? 😮")
                        continue
                    } else {
                        break
                    }

                } catch (e: Exception) {
                    println("Ungültige Eingabe.")

                }
            }

            println("Zahlungsmöglichkeiten: ")
            var auswahl = 0
            println()

            while (true) {

                try {

                    println(
                        """
                        
                        [1] PayPal
                        [2] Apple Pay
                        [3] Sofort-Überweisung
                        
                    """.trimIndent()
                    )

                    auswahl = readln().toInt()
                    if (auswahl !in 1..3) {
                        throw Exception("Die Auswahl haben wir nicht.")
                    }

                    when (auswahl) {

                        1 -> {
                            println("Bitte geben sie einen Pin ein.")
                            println("max. 4 Zeichen // z.B 1996 || z.B Auto")
                            var pin = readln()
                            if (pin.length <= 4) {
                                println("Account erfolgreich erstellt.")
                                var account = KundenAccount(benutzerName, passwort, alter, isAdmin, "PayPal", pin)
                                accountDaten.add(account)
                                return account
                            } else {
                                throw Exception("max. 4 Zeichen!")
                            }
                        }

                        2 -> {
                            println("Bitte geben sie einen Pin ein.")
                            println("max. 4 Zeichen // z.B 1996 || z.B Auto")
                            var pin = readln()
                            if (pin.length <= 4) {
                                println("Account erfolgreich erstellt.")
                                var account = KundenAccount(benutzerName, passwort, alter, isAdmin, "Apple Pay", pin)
                                accountDaten.add(account)
                                return account
                            } else {
                                throw Exception("max. 4 Zeichen!")
                            }

                        }

                        3 -> {
                            println("Bitte geben sie einen Pin ein.")
                            println("max. 4 Zeichen // z.B 1996 || z.B Auto")
                            var pin = readln()
                            if (pin.length <= 4) {
                                println("Account erfolgreich erstellt.")
                                var account = KundenAccount(benutzerName, passwort, alter, isAdmin, "Sofort-Überweisung", pin)
                                accountDaten.add(account)
                                return account
                            } else {
                                throw Exception("max. 4 Zeichen!")
                            }
                        }


                    }


                } catch (e: Exception) {
                    println("Ungültige Eingabe")
                    e.message
                }


            }


        }

    }

    fun logInKunde(): KundenAccount? {

        println("Einloggen!")
        println()
        var versuche = 0

        while (versuche < 5) {

            println("Benutzername: ")
            var benutzerName = readln()

            println("Passwort: ")
            var passwort = readln()

            for (account in accountDaten){
                if (account is KundenAccount){
                    if (benutzerName == account.benutzername){
                        if (passwort == account.passwort){
                            return account
                        }else {
                            println("Falsches Passwort.")
                            versuche++
                        }
                    }else{
                        println("Dieser Benutzername existiert nicht.")
                    }
                }
            }

        }
        return null
    }

    fun logInManager(): ManagerAccount? {

        println("Manager Login!")
        println()
        var versuche = 0

        while (versuche < 5) {

            println("Benutzername: ")
            var benutzerName = readln()
            println("Passwort: ")
            var passwort = readln()

            for (account in accountDaten){
                if (account is ManagerAccount){
                    if (benutzerName == account.benutzername){
                        if (passwort == account.passwort){
                            return account
                        }else {
                            println("Falsches Passwort.")
                            versuche++
                        }
                    }else{
                        println("Dieser Benutzername existiert nicht.")
                    }
                }
            }



        }

        return null
    }


}