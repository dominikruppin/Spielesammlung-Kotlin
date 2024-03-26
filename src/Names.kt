import kotlin.random.Random

class Names {
    private val names = listOf(
            "Emma", "Liam", "Olivia", "Noah", "Ava", "Oliver", "Isabella", "William", "Sophia", "Elijah",
            "Charlotte", "James", "Amelia", "Benjamin", "Mia", "Lucas", "Harper", "Henry", "Evelyn", "Alexander",
            "Abigail", "Michael", "Emily", "Daniel", "Elizabeth", "Matthew", "Sofia", "Jackson", "Avery", "Sebastian",
            "Ella", "Aiden", "Madison", "David", "Scarlett", "Joseph", "Victoria", "Carter", "Aria", "Owen",
            "Grace", "Wyatt", "Chloe", "John", "Camila", "Jack", "Penelope", "Luke", "Riley"
    )

    fun generateRandomName(): String {
        return names.random()
    }
}