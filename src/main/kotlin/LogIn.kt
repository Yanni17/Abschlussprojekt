class LogIn {

    var accountDaten = mutableListOf<Account>(
        ManagerAccount("Admin", "Jonny123",),
        KundenAccount("Yanni","Pechlivanis123",21,"")
    )

    fun register(): KundenAccount {

        var passwort = ""
        println("""
            _____________________________
                   Ｒｅｇｉｓｔｅｒ
                   
        """.trimIndent())

        while (true) {

            println("Benutzername:")
            var benutzerName = readln().trim()

            try {

                for (account in accountDaten) {
                    if (account is KundenAccount) {
                        if (benutzerName == account.benutzername) {
                            throw Exception("$red Benutzername existiert bereits.$reset")
                        }
                    }
                }

            } catch (e: Exception) {
                println(e.message)
                continue
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
                            println("$red Das Passwort muss mindestens 10 Zeichen haben.$reset")
                            continue
                        }
                    } else {
                        println("$red Der Anfangsbuchstabe muss Groß sein.$reset")
                        continue
                    }
                }

            } catch (e: Exception) {
                println("$red Ungültige Eingabe$reset")
            }

            var alter = 0

            while (true) {

                try {

                    println("Alter: ")

                    alter = readln().toInt()

                    if (alter < 0 || alter !in 1..100) {
                        println("Sind Sie ein Mensch? 😮")
                        continue
                    } else if (alter < 12) {
                        println("$red Sie sind leider zu Jung um sich zu registrieren.$reset")
                        continue
                    }else {
                        break
                    }

                } catch (e: Exception) {
                    println("$red Ungültige Eingabe.$reset")

                }
            }

            println()
            println("$green Registration Erfolgreich.$reset")
            var account = KundenAccount(benutzerName, passwort, alter, zahlungsMethode = "")
            accountDaten.add(account)
            return account

        }

    }

    fun logInKunde(): KundenAccount? {

        println("""
            _____________________________
                      Ｌｏｇｉｎ
                        
        """.trimIndent())
        var versuche = 0

        while (versuche < 5) {

            println("Benutzername: ")
            var benutzerName = readln()
            println("Passwort: ")
            var passwort = readln()

            for (account in accountDaten) {
                if (account is KundenAccount) {
                    if (benutzerName == account.benutzername) {
                        if (passwort == account.passwort) {
                            println()
                            println("$green Login Erfolgreich$reset")
                            return account
                        } else {
                            println("$red Falsches Passwort.$reset")
                            versuche++
                            println("$versuche / 5 Versuchen.")
                        }
                    } else {
                        println("$red Dieser Benutzername existiert nicht.$reset")

                    }
                }
            }


        }
        println("$red Sie sind gesperrt. Versuchen Sie es in 10 sekunden nochmal.$reset")
        Thread.sleep(10000)
        return null

    }

    fun logInManager(): ManagerAccount? {

        println("""
            _____________________________
                 Ａｄｍｉｎ Ｌｏｇｉｎ
                        
        """.trimIndent())
        var versuche = 0

        while (versuche < 5) {

            println("Benutzername: ")
            var benutzerName = readln().trim()
            println("Passwort: ")
            var passwort = readln()

            for (account in accountDaten) {
                if (account is ManagerAccount) {
                    if (benutzerName == account.benutzername) {
                        if (passwort == account.passwort) {
                            println()
                            println("$green Login Erfolgreich.$reset")
                            return account
                        } else {
                            println("$red Falsches Passwort.$reset")
                            versuche++
                            println("$versuche / 5 Versuchen.")
                        }
                    } else {
                        println("$red Dieser Benutzername existiert nicht.$reset")
                        versuche++
                        println("$versuche / 5 Versuchen.")
                    }
                }
            }


        }
        println("Sie sind gesperrt. Versuchen Sie es in 10 sekunden nochmal.")
        Thread.sleep(10000)
        return null
    }


}