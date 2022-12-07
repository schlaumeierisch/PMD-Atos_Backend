package nl.hva.backend.domain

import nl.hva.backend.domain.ids.ExerciseId
import nl.hva.backend.domain.ids.MedicalRecordId

open class Exercise {
    private val id: Long = 0
    private lateinit var domainId: ExerciseId

    private lateinit var title: String
    private lateinit var description: String
    private lateinit var progress: Number
    private lateinit var duration: Number

    // one-to-one
    private var medicalRecordDomainId: MedicalRecordId = MedicalRecordId("")

    // required by hibernate
    protected constructor()

    constructor(
        domainId: ExerciseId,
        title: String,
        description: String,
        progress: Number,
        duration: Number,
        medicalRecordDomainId: MedicalRecordId
    ) {
        this.domainId = domainId
        this.title = title
        this.description = description
        this.progress = progress
        this.duration = duration
        this.medicalRecordDomainId = medicalRecordDomainId
    }

    // getter
    fun id(): Long = this.id
    fun domainId(): ExerciseId = this.domainId
    fun title(): String = this.title
    fun description(): String = this.description
    fun progress(): Number = this.progress
    fun duration(): Number = this.duration
    fun medicalRecordDomainId(): MedicalRecordId = this.medicalRecordDomainId
}