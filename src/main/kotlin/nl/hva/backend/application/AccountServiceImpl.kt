package nl.hva.backend.application

import nl.hva.backend.application.api.AccountService
import nl.hva.backend.application.dto.CareProviderDTO
import nl.hva.backend.application.dto.GeneralPractitionerDTO
import nl.hva.backend.application.dto.PatientDTO
import nl.hva.backend.application.dto.many_to_many.PatientCareProviderRelationDTO
import nl.hva.backend.domain.CareProvider
import nl.hva.backend.domain.GeneralPractitioner
import nl.hva.backend.domain.Patient
import nl.hva.backend.domain.api.AccountRepository
import nl.hva.backend.domain.ids.CareProviderId
import nl.hva.backend.domain.ids.GeneralPractitionerId
import nl.hva.backend.domain.ids.PatientId
import nl.hva.backend.domain.many_to_many.PatientCareProviderRelation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountServiceImpl : AccountService {

    @Autowired
    private lateinit var accountRepository: AccountRepository

    @Transactional
    override fun getAllGeneralPractitioners(): List<GeneralPractitionerDTO> {
        val generalPractitioners: List<GeneralPractitioner> = this.accountRepository.getAllGeneralPractitioners()

        return GeneralPractitionerDTO.fromGeneralPractitioners(generalPractitioners)
    }

    @Transactional
    override fun getGeneralPractitionerById(generalPractitionerId: GeneralPractitionerId): List<GeneralPractitionerDTO> {
        val generalPractitioner: List<GeneralPractitioner> =
            this.accountRepository.getGeneralPractitionerById(generalPractitionerId)

        return if (generalPractitioner.isNotEmpty()) {
            listOf(GeneralPractitionerDTO.fromGeneralPractitioner(generalPractitioner[0]))
        } else {
            emptyList()
        }
    }

    @Transactional
    override fun getPatientsOfGeneralPractitionerById(generalPractitionerId: GeneralPractitionerId): List<PatientDTO> {
        val patients: List<Patient> = this.accountRepository.getPatientsOfGeneralPractitionerById(generalPractitionerId)

        return PatientDTO.fromPatients(patients)
    }

    @Transactional
    override fun getAllPatients(): List<PatientDTO> {
        val patients: List<Patient> = this.accountRepository.getAllPatients()

        return PatientDTO.fromPatients(patients)
    }

    @Transactional
    override fun getPatientById(patientId: PatientId): List<PatientDTO> {
        val patient: List<Patient> = this.accountRepository.getPatientById(patientId)

        return if (patient.isNotEmpty()) {
            listOf(PatientDTO.fromPatient(patient[0]))
        } else {
            emptyList()
        }
    }

    @Transactional
    override fun getPatientCareProviderRelationsByPatientId(patientId: PatientId): List<PatientCareProviderRelationDTO> {
        val patientCareProviderRelations: List<PatientCareProviderRelation> =
            this.accountRepository.getPatientCareProviderRelationsByPatientId(patientId)

        return PatientCareProviderRelationDTO.fromPatientCareProviderRelations(patientCareProviderRelations)
    }

    @Transactional
    override fun createPatientCareProviderRelation(patientId: PatientId, careProviderId: CareProviderId) {
        this.accountRepository.createPatientCareProviderRelation(PatientCareProviderRelation(patientId, careProviderId))
    }

    @Transactional
    override fun getAllCareProviders(): List<CareProviderDTO> {
        val careProviders: List<CareProvider> = this.accountRepository.getAllCareProviders()

        return CareProviderDTO.fromCareProviders(careProviders)
    }

    @Transactional
    override fun getCareProviderById(careProviderId: CareProviderId): List<CareProviderDTO> {
        val careProvider: List<CareProvider> = this.accountRepository.getCareProviderById(careProviderId)

        return if (careProvider.isNotEmpty()) {
            listOf(CareProviderDTO.fromCareProvider(careProvider[0]))
        } else {
            emptyList()
        }
    }

}