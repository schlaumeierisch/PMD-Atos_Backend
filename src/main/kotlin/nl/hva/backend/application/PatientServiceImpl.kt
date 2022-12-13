package nl.hva.backend.application

import nl.hva.backend.application.api.PatientService
import nl.hva.backend.application.dto.PatientDTO
import nl.hva.backend.application.dto.many_to_many.PatientCareProviderDTO
import nl.hva.backend.domain.Patient
import nl.hva.backend.domain.api.PatientRepository
import nl.hva.backend.domain.ids.GeneralPractitionerId
import nl.hva.backend.domain.ids.MedicalRecordId
import nl.hva.backend.domain.ids.PatientId
import nl.hva.backend.domain.many_to_many.PatientCareProviderRelation
import nl.hva.backend.domain.value_objects.Address
import nl.hva.backend.domain.value_objects.Gender
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class PatientServiceImpl : PatientService {

    @Autowired
    private lateinit var patientRepository: PatientRepository

    @Transactional
    override fun createAccount(
        firstName: String,
        lastName: String,
        street: String,
        zip: String,
        city: String,
        country: String,
        gender: Enum<Gender>,
        birthDate: LocalDate,
        phoneNumber: String,
        email: String,
        isUsingApp: Boolean,
        medicalRecordId: String,
        gpId: String
    ) {
        val patientId: PatientId = this.patientRepository.nextIdentity()

        val patient = Patient(
            patientId, firstName, lastName, Address(street, zip, city, country), gender, birthDate,
            phoneNumber, email, isUsingApp, MedicalRecordId(medicalRecordId), GeneralPractitionerId(gpId)
        )

        this.patientRepository.createAccount(patient)
    }

    @Transactional
    override fun editAccount(
        patientId: PatientId,
        firstName: String,
        lastName: String,
        street: String,
        zip: String,
        city: String,
        country: String,
        gender: Enum<Gender>,
        birthDate: LocalDate,
        phoneNumber: String,
        email: String,
        isUsingApp: Boolean,
        gpId: String
    ) {
        this.patientRepository.editAccount(
            patientId, firstName, lastName, street, zip, city, country,
            gender, birthDate, phoneNumber, email, isUsingApp, GeneralPractitionerId(gpId)
        )
    }

    @Transactional
    override fun deleteAccount(patientId: PatientId) {
        this.patientRepository.deleteAccount(patientId)
    }

    @Transactional
    override fun getAccountById(patientId: PatientId): PatientDTO {
        val patient: Patient = this.patientRepository.getAccountById(patientId)

        return PatientDTO.fromPatient(patient)
    }

    @Transactional
    override fun getAllAccounts(): List<PatientDTO> {
        val patients: List<Patient> = this.patientRepository.getAllAccounts()

        return PatientDTO.fromPatients(patients)
    }

    @Transactional
    override fun getPatientCareProviderRelationsByPatientId(patientId: PatientId): List<PatientCareProviderDTO> {
        val patientCareProviderRelations: List<PatientCareProviderRelation> =
            this.patientRepository.getPatientCareProviderRelationsByPatientId(patientId)

        return PatientCareProviderDTO.fromPatientCareProviderRelations(patientCareProviderRelations)
    }

}