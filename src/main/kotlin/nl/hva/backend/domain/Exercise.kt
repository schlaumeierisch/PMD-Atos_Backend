package nl.hva.backend.domain

import nl.hva.backend.domain.ids.ExerciseId
import nl.hva.backend.domain.ids.MedicalRecordId
import java.time.LocalDate

open class Exercise {
    private val id: Long = 0
    private lateinit var domainId: ExerciseId

    private lateinit var title: String
    private lateinit var description: String
    private lateinit var startDate: LocalDate
    private var endDate: LocalDate? = null

    // one-to-one
    private var medicalRecordDomainId: MedicalRecordId = MedicalRecordId("")

    // required by hibernate
    protected constructor()

    constructor(
        domainId: ExerciseId,
        title: String,
        description: String,
        startDate: LocalDate,
        endDate: LocalDate? = null,
        medicalRecordDomainId: MedicalRecordId
    ) {
        this.domainId = domainId
        this.title = title
        this.description = description
        this.startDate = startDate
        this.endDate = endDate
        this.medicalRecordDomainId = medicalRecordDomainId
    }

    // getter
    fun id(): Long = this.id
    fun domainId(): ExerciseId = this.domainId
    fun title(): String = this.title
    fun description(): String = this.description
    fun startDate(): LocalDate = this.startDate
    fun endDate(): LocalDate? = this.endDate
    fun medicalRecordDomainId(): MedicalRecordId = this.medicalRecordDomainId
}