package nl.hva.backend.domain.ids

import java.util.*

class PatientId {
    private lateinit var id: String

    // required by hibernate
    private constructor()

    constructor(id: String) {
        this.id = id
    }

    fun id(): String {
        return id
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val patientId = other as PatientId
        return id == patientId.id
    }

    override fun hashCode(): Int {
        return Objects.hash(id)
    }
}