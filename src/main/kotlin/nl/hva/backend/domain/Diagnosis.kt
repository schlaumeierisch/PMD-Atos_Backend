package nl.hva.backend.domain

import nl.hva.backend.domain.ids.DiagnosisId
import nl.hva.backend.domain.ids.MedicalRecordId
import nl.hva.backend.domain.value_objects.DiagnosisType
import java.time.LocalDate

class Diagnosis {
    private val id: Long = 0
    private lateinit var domainId: DiagnosisId

    private lateinit var title: String
    private lateinit var diagnosisType: Enum<DiagnosisType>
    private lateinit var dateDiagnosed: LocalDate
    private lateinit var cause: String
    private lateinit var treatment: String
    private lateinit var advice: String

    // one-to-one
    private var medicalRecordDomainId: MedicalRecordId = MedicalRecordId("")

    // required by hibernate
    protected constructor()

    constructor(
        domainId: DiagnosisId,
        title: String,
        diagnosisType: Enum<DiagnosisType>,
        dateDiagnosed: LocalDate,
        cause: String,
        treatment: String,
        advice: String,
        medicalRecordDomainId: MedicalRecordId
    ) {
        this.domainId = domainId
        this.title = title
        this.diagnosisType = diagnosisType
        this.dateDiagnosed = dateDiagnosed
        this.cause = cause
        this.treatment = treatment
        this.advice = advice
        this.medicalRecordDomainId = medicalRecordDomainId
    }

    // getters
    fun id(): Long = this.id
    fun domainId(): DiagnosisId = this.domainId
    fun title(): String = this.title
    fun diagnosisType(): Enum<DiagnosisType> = this.diagnosisType
    fun dateDiagnosed(): LocalDate = this.dateDiagnosed
    fun cause(): String = this.cause
    fun treatment(): String = this.treatment
    fun advice(): String = this.advice
    fun medicalRecordDomainId(): MedicalRecordId = this.medicalRecordDomainId
}