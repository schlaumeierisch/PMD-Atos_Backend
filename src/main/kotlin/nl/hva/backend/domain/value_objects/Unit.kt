package nl.hva.backend.domain.value_objects

enum class Unit(val displayValue: String) {
    GRAM("g"), MILLIGRAM("mg"),
    LITRE("l"), MILLILITRE("ml"),
    PILL("Pill")
}
