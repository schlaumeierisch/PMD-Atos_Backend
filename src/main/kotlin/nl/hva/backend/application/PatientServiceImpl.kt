package nl.hva.backend.application

import nl.hva.backend.application.api.PatientService
import nl.hva.backend.application.dto.GeneralPractitionerDTO
import nl.hva.backend.application.dto.PatientDTO
import nl.hva.backend.application.dto.value_objects.AddressDTO
import nl.hva.backend.domain.Patient
import nl.hva.backend.domain.api.PatientRepository
import nl.hva.backend.domain.ids.PatientId
import nl.hva.backend.domain.value_objects.Address
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Component
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
        gender: String,
        birthDate: LocalDate,
        phoneNumber: String,
        email: String,
        isUsingApp: Boolean
    ) {
        val patientId: PatientId = patientRepository.nextIdentity()

        val patient = Patient(
            patientId,
            firstName,
            lastName,
            Address(street, zip, city, country),
            gender,
            birthDate,
            phoneNumber,
            email,
            isUsingApp
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
        gender: String,
        birthDate: LocalDate,
        phoneNumber: String,
        email: String,
        isUsingApp: Boolean
    ) {
        this.patientRepository.editAccount(
            patientId,
            firstName,
            lastName,
            street,
            zip,
            city,
            country,
            gender,
            birthDate,
            phoneNumber,
            email,
            isUsingApp
        )
    }

    @Transactional
    override fun deleteAccount(patientId: PatientId) {
        this.patientRepository.deleteAccount(patientId)
    }

    @Transactional
    override fun getAccountById(patientId: PatientId): PatientDTO {
        val patient: Patient =
            this.patientRepository.getAccountById(patientId)

        if (patient.generalPractitioner() != null) {
            return PatientDTO().builder()
                .withId(patient.domainId().id())
                .withFirstName(patient.firstName())
                .withLastName(patient.lastName())
                .withAddressDTO(AddressDTO.fromAddress(patient.address()))
                .withGender(patient.gender())
                .withBirthDate(patient.birthDate())
                .withPhoneNumber(patient.phoneNumber())
                .withEmail(patient.email())
                .withIsUsingApp(patient.isUsingApp())
                .withGeneralPractitionerDTO(
                    GeneralPractitionerDTO.fromGeneralPractitioner(
                        patient.generalPractitioner()!!,
                        false
                    )
                )
                .build()
        } else {
            return PatientDTO().builder()
                .withId(patient.domainId().id())
                .withFirstName(patient.firstName())
                .withLastName(patient.lastName())
                .withAddressDTO(AddressDTO.fromAddress(patient.address()))
                .withGender(patient.gender())
                .withBirthDate(patient.birthDate())
                .withPhoneNumber(patient.phoneNumber())
                .withEmail(patient.email())
                .withIsUsingApp(patient.isUsingApp())
                .build()
        }
    }

    @Transactional
    override fun getAllAccounts(): List<PatientDTO> {
        val patients: List<Patient> = this.patientRepository.getAllAccounts()
        val patientDTOs: ArrayList<PatientDTO> = arrayListOf()

        for (patient in patients) {
            val patientDTO: PatientDTO
            if (patient.generalPractitioner() != null) {
                patientDTO = PatientDTO().builder()
                    .withId(patient.domainId().id())
                    .withFirstName(patient.firstName())
                    .withLastName(patient.lastName())
                    .withAddressDTO(AddressDTO.fromAddress(patient.address()))
                    .withGender(patient.gender())
                    .withBirthDate(patient.birthDate())
                    .withPhoneNumber(patient.phoneNumber())
                    .withEmail(patient.email())
                    .withIsUsingApp(patient.isUsingApp())
                    .withGeneralPractitionerDTO(
                        GeneralPractitionerDTO.fromGeneralPractitioner(
                            patient.generalPractitioner()!!,
                            false
                        )
                    )
                    .build()
            } else {
                patientDTO = PatientDTO().builder()
                    .withId(patient.domainId().id())
                    .withFirstName(patient.firstName())
                    .withLastName(patient.lastName())
                    .withAddressDTO(AddressDTO.fromAddress(patient.address()))
                    .withGender(patient.gender())
                    .withBirthDate(patient.birthDate())
                    .withPhoneNumber(patient.phoneNumber())
                    .withEmail(patient.email())
                    .withIsUsingApp(patient.isUsingApp())
                    .build()
            }

            patientDTOs.add(patientDTO)
        }

        return patientDTOs
    }

}