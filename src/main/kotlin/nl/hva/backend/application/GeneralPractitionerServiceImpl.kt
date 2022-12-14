package nl.hva.backend.application

import nl.hva.backend.application.api.GeneralPractitionerService
import nl.hva.backend.application.dto.GeneralPractitionerDTO
import nl.hva.backend.application.dto.PatientDTO
import nl.hva.backend.domain.GeneralPractitioner
import nl.hva.backend.domain.Patient
import nl.hva.backend.domain.api.GeneralPractitionerRepository
import nl.hva.backend.domain.ids.GeneralPractitionerId
import nl.hva.backend.domain.value_objects.Address
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GeneralPractitionerServiceImpl : GeneralPractitionerService {

    @Autowired
    private lateinit var generalPractitionerRepository: GeneralPractitionerRepository

    @Transactional
    override fun createAccount(
        firstName: String,
        lastName: String,
        street: String,
        zip: String,
        city: String,
        country: String,
        phoneNumber: String
    ) {
        val generalPractitionerId: GeneralPractitionerId = generalPractitionerRepository.nextIdentity()

        val generalPractitioner = GeneralPractitioner(
            generalPractitionerId, firstName, lastName, Address(street, zip, city, country), phoneNumber
        )

        this.generalPractitionerRepository.createAccount(generalPractitioner)
    }

    @Transactional
    override fun editAccount(
        generalPractitionerId: GeneralPractitionerId,
        firstName: String,
        lastName: String,
        street: String,
        zip: String,
        city: String,
        country: String,
        phoneNumber: String
    ) {
        this.generalPractitionerRepository.editAccount(
            generalPractitionerId, firstName, lastName, street, zip, city, country, phoneNumber
        )
    }

    @Transactional
    override fun deleteAccount(generalPractitionerId: GeneralPractitionerId) {
        this.generalPractitionerRepository.deleteAccount(generalPractitionerId)
    }

    @Transactional
    override fun getAccountById(generalPractitionerId: GeneralPractitionerId): List<GeneralPractitionerDTO> {
        val generalPractitioner: List<GeneralPractitioner> =
            this.generalPractitionerRepository.getAccountById(generalPractitionerId)

        return if (generalPractitioner.isNotEmpty()) {
            listOf(GeneralPractitionerDTO.fromGeneralPractitioner(generalPractitioner[0]))
        } else {
            emptyList()
        }
    }

    @Transactional
    override fun getAllAccounts(): List<GeneralPractitionerDTO> {
        val generalPractitioners: List<GeneralPractitioner> = this.generalPractitionerRepository.getAllAccounts()

        return GeneralPractitionerDTO.fromGeneralPractitioners(generalPractitioners)
    }

    @Transactional
    override fun getPatientsOfGeneralPractitionerById(generalPractitionerId: GeneralPractitionerId): List<PatientDTO> {
        val patients: List<Patient> = this.generalPractitionerRepository.getPatientsOfGeneralPractitionerById(generalPractitionerId)

        return PatientDTO.fromPatients(patients)
    }

}