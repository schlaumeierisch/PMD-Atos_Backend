package nl.hva.backend.domain.many_to_many

import nl.hva.backend.domain.ids.CareProviderId
import nl.hva.backend.domain.ids.ExerciseId
import java.time.LocalDate

open class ExerciseCareProviderRelation {
    private val id: Long = 0

    private lateinit var cpDomainId: CareProviderId
    private lateinit var exerciseId: ExerciseId
    private lateinit var validDate: LocalDate


    // required by hibernate
    protected constructor()

    constructor(
        cpDomainId: CareProviderId,
        exerciseId: ExerciseId,
        validDate: LocalDate
    ) {
        this.cpDomainId = cpDomainId
        this.exerciseId = exerciseId
        this.validDate = validDate
    }

    fun id(): Long = this.id
    fun cpDomainId(): CareProviderId = this.cpDomainId
    fun exerciseId(): ExerciseId = this.exerciseId
    fun validDate(): LocalDate = this.validDate


}