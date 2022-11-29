package nl.hva.backend.application

import nl.hva.backend.application.api.MedicalRecordService
import nl.hva.backend.application.api.PatientService
import nl.hva.backend.application.dto.PatientDTO
import nl.hva.backend.application.dto.value_objects.AddressDTO
import nl.hva.backend.domain.MedicalRecord
import nl.hva.backend.domain.Patient
import nl.hva.backend.domain.api.MedicalRecordRepository
import nl.hva.backend.domain.api.PatientRepository
import nl.hva.backend.domain.ids.GeneralPractitionerId
import nl.hva.backend.domain.ids.MedicalRecordId
import nl.hva.backend.domain.ids.PatientId
import nl.hva.backend.domain.value_objects.Address
import nl.hva.backend.domain.value_objects.Gender
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Component
class MedicalRecordImpl : MedicalRecordService {

    @Autowired
    private lateinit var medicalRecordRepository: MedicalRecordRepository

    @Transactional
    override fun createMedicalRecord(): MedicalRecordId {
        val medicalRecordId: MedicalRecordId = this.medicalRecordRepository.nextIdentity()

        val medicalRecord = MedicalRecord(medicalRecordId)

        this.medicalRecordRepository.createMedicalRecord(medicalRecord)

        return medicalRecordId
    }

}