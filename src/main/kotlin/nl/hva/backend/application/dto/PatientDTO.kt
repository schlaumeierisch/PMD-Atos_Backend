package nl.hva.backend.application.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import nl.hva.backend.application.dto.value_objects.AddressDTO
import nl.hva.backend.domain.Patient
import nl.hva.backend.domain.value_objects.Gender
import java.time.LocalDate

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class PatientDTO {
    private lateinit var id: String
    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var addressDTO: AddressDTO
    private lateinit var gender: Enum<Gender>
    private lateinit var birthDate: LocalDate
    private lateinit var phoneNumber: String
    private lateinit var email: String
    private var isUsingApp: Boolean = false

    // one-to-one
    private var medicalRecordId: String = ""

    // many-to-one
    private var gpId: String = ""

    companion object {
        fun fromPatient(patient: Patient): PatientDTO {
            val patientDTO = PatientDTO()

            patientDTO.id = patient.domainId().id()
            patientDTO.firstName = patient.firstName()
            patientDTO.lastName = patient.lastName()
            patientDTO.addressDTO = AddressDTO.fromAddress(patient.address())
            patientDTO.gender = patient.gender()
            patientDTO.birthDate = patient.birthDate()
            patientDTO.phoneNumber = patient.phoneNumber()
            patientDTO.email = patient.email()
            patientDTO.isUsingApp = patient.isUsingApp()

            patientDTO.medicalRecordId = patient.medicalRecordDomainId().id()

            patientDTO.gpId = patient.gpDomainId().id()

            return patientDTO
        }

        fun fromPatients(patients: List<Patient>): List<PatientDTO> {
            val patientDTOs: ArrayList<PatientDTO> = arrayListOf()

            for (patient in patients) {
                patientDTOs.add(fromPatient(patient))
            }

            return patientDTOs
        }
    }

    // getter
    fun id(): String = this.id
    fun firstName(): String = this.firstName
    fun lastName(): String = this.lastName
    fun addressDTO(): AddressDTO = this.addressDTO
    fun gender(): Enum<Gender> = this.gender
    fun birthDate(): LocalDate = this.birthDate
    fun phoneNumber(): String = this.phoneNumber
    fun email(): String = this.email
    fun isUsingApp(): Boolean = this.isUsingApp
    fun medicalRecordId(): String = this.medicalRecordId
    fun gpId(): String = this.gpId
}