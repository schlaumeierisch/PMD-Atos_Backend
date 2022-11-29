package nl.hva.backend.domain.ids

import java.util.*

class ObservationId {
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
        val observationId = other as ObservationId
        return id == observationId.id
    }

    override fun hashCode(): Int {
        return Objects.hash(id)
    }
}