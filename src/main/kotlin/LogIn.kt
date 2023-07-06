class LogIn {


    var accountDaten = mutableListOf<Account>(
        ManagerAccount("Admin","Jonny123",true)
    )

    var isAdmin = false

    fun register(): KundenAccount {

        var passwort = ""
        println("Registration!")

        while (true) {

            println("Benutzername:")
            var benutzerName = readln()

            try {

                for (account in accountDaten) {
                    if (account is KundenAccount) {
                        if (benutzerName == account.benutzername) {
                            throw Exception("")
                        }
                    }
                }

            }catch (e:Exception){
                println("Benutzername Existiert bereits.")
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

            var account = KundenAccount(benutzerName, passwort, alter, isAdmin, zahlungsMethode = "")
            accountDaten.add(account)
            return account

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

            for (account in accountDaten) {
                if (account is KundenAccount) {
                    if (benutzerName == account.benutzername) {
                        if (passwort == account.passwort) {
                            return account
                        } else {
                            println("Falsches Passwort.")
                            println("$versuche / 5 versuche.")
                            versuche++
                        }
                    } else {
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

            for (account in accountDaten) {
                if (account is ManagerAccount) {
                    if (benutzerName == account.benutzername) {
                        if (passwort == account.passwort) {
                            return account
                        } else {
                            println("Falsches Passwort.")
                            versuche++
                        }
                    } else {
                        println("Dieser Benutzername existiert nicht.")
                    }
                }
            }


        }

        return null
    }


}