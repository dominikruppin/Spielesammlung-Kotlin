fun werWirdMillionaer(player: Player) {
    val level = listOf(50, 100, 200, 300, 500, 1000, 2000, 4000, 8000, 16000, 32000, 64000, 125000, 500000, 1000000)
    var risiko = false

    println("""Willkommen bei Wer wird Millionär, ${player.name}!
        |Bitte wähle aus, ob du den Risikomodus spielst oder nicht.
        |Bei Risiko erhältst du einen zusätzlichen Joker, ABER,
        |die Sicherheitsstufe bei 16.000€ fällt weg. Du kannst also auf
        |500€ runterfallen.
        |
        |1) Risikomodus wählen
        |2) KEIN Risikomodus
    """.trimMargin())

    while (true) {
        when (readln()) {
            "1" -> {
                println("Du hast den Risikomodus gewählt und erhältst einen Zusatzjoker.")
                player.joker.addAll(listOf("50/50", "Publikumsjoker", "Telefonjoker", "Zusatzjoker"))
                break
            }

            "2" -> {
                println("Du hast den Risikomodus abgelehnt. Du erhältst daher nur die Standardjoker.")
                player.joker.addAll(listOf("50/50", "Publikumsjoker", "Telefonjoker"))
                break
            }

            else -> println("Ungültige Eingabe, probiere es erneut:")
        }
    }

    println("Beginnen wir nun mit der ersten Frage.\n")
    var question = newQuestion(player.round)
    println("Frage ${player.round + 1} für ${level[player.round]}€:")
    println(question.question)
    question.options.forEachIndexed { index, option ->
        val char = 'a' + index
        println("$char) $option")
    }

    var answer: Int
    println("Bitte gib deine Lösung ein, du hast dafür maximal 60 Sekunden Zeit:")
    while (true) {
        when (readln().lowercase()) {
            "a" -> {
                println("Du hast Lösung a) ${question.options[0]} ausgewählt...")
                answer = 0
            }
            "b" -> {
                println("Du hast Lösung b) ${question.options[1]} ausgewählt...")
                answer = 1
            }
            "c" -> {
                println("Du hast Lösung c) ${question.options[2]} ausgewählt...")
                answer = 1
            }
            "d" -> {
                println("Du hast Lösung d) ${question.options[3]} ausgewählt...")
                answer = 1
            }
            else -> println("Ungültige Eingabe, probiere es erneut:")
        }
    }


}
