package nl.hva.backend.application.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import nl.hva.backend.domain.Diagnosis
import nl.hva.backend.domain.value_objects.DiagnosisType
import java.time.LocalDate

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class DiagnosisDTO {
    private lateinit var id: String
    private lateinit var title: String
    private lateinit var diagnosisType: Enum<DiagnosisType>
    private lateinit var dateDiagnosed: LocalDate
    private lateinit var cause: String
    private lateinit var treatment: String
    private lateinit var advice: String

    // one-to-one
    private lateinit var medicalRecordId: String

    companion object {
        fun fromDiagnosis(diagnosis: Diagnosis): DiagnosisDTO {
            val diagnosisDTO = DiagnosisDTO()

            diagnosisDTO.id = diagnosis.domainId().id()
            diagnosisDTO.title = diagnosis.title()
            diagnosisDTO.diagnosisType = diagnosis.diagnosisType()
            diagnosisDTO.dateDiagnosed = diagnosis.dateDiagnosed()
            diagnosisDTO.cause = diagnosis.cause()
            diagnosisDTO.treatment = diagnosis.treatment()
            diagnosisDTO.advice = diagnosis.advice()
            diagnosisDTO.medicalRecordId = diagnosis.medicalRecordDomainId().id()

            return diagnosisDTO
        }

        fun fromDiagnoses(diagnoses: List<Diagnosis>): List<DiagnosisDTO> {
            val diagnosisDTOs: ArrayList<DiagnosisDTO> = arrayListOf()

            for (diagnosis in diagnoses) {
                diagnosisDTOs.add(fromDiagnosis(diagnosis))
            }

            return diagnosisDTOs
        }
    }

    // getter
    fun id(): String = this.id
    fun title(): String = this.title
    fun diagnosisType(): Enum<DiagnosisType> = this.diagnosisType
    fun dateDiagnosed(): LocalDate = this.dateDiagnosed
    fun cause(): String = this.cause
    fun treatment(): String = this.treatment
    fun advice(): String = this.advice
    fun medicalRecordId(): String = this.medicalRecordId
}