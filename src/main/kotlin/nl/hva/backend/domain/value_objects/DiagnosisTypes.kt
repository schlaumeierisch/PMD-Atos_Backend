package nl.hva.backend.domain.value_objects

enum class DiagnosisTypes(val displayValue: String) {
    HEART("Heart"), BLOOD("Blood"), AIRWAY("Airway"), DIGESTION("Digestion"),
    MUSCLES("Muscles"), BONES("Bones"), OTHER("Other")
}