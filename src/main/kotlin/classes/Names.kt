package classes

/**
 * Die Klasse names repräsentiert einfach nur Namen. Sie beinhaltet eine Namensliste
 * und eine Funktion um einen zufälligen Namen als String zurück zu geben.
 */
class Names {
    private val names = listOf(
            "Emma", "Liam", "Olivia", "Noah", "Ava", "Oliver", "Isabella", "William", "Sophia", "Elijah",
            "Charlotte", "James", "Amelia", "Benjamin", "Mia", "Lucas", "Harper", "Henry", "Evelyn", "Alexander",
            "Abigail", "Michael", "Emily", "Daniel", "Elizabeth", "Matthew", "Sofia", "Jackson", "Avery", "Sebastian",
            "Ella", "Aiden", "Madison", "David", "Scarlett", "Joseph", "Victoria", "Carter", "Aria", "Owen",
            "Grace", "Wyatt", "Chloe", "John", "Camila", "Jack", "Penelope", "Luke", "Riley"
    )

    /**
     * Aus einer Liste von Namen wird ein zufälliger ausgewählt.
     *
     * @return Den zufällig gewählten Namen als String.
     */
    fun generateRandomName(): String {
        return names.random()
    }
}