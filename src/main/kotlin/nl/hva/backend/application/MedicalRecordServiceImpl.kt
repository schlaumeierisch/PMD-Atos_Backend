package nl.hva.backend.application

import nl.hva.backend.application.api.MedicalRecordService
import nl.hva.backend.application.dto.IntakeDTO
import nl.hva.backend.application.dto.MedicationDTO
import nl.hva.backend.application.dto.ObservationDTO
import nl.hva.backend.domain.Intake
import nl.hva.backend.domain.MedicalRecord
import nl.hva.backend.domain.Medication
import nl.hva.backend.domain.Observation
import nl.hva.backend.domain.api.MedicalRecordRepository
import nl.hva.backend.domain.ids.MedicalRecordId
import nl.hva.backend.domain.ids.MedicationId
import nl.hva.backend.domain.ids.ObservationId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class MedicalRecordServiceImpl : MedicalRecordService {

    @Autowired
    private lateinit var medicalRecordRepository: MedicalRecordRepository

    @Transactional
    override fun createMedicalRecord(): MedicalRecordId {
        val medicalRecordId: MedicalRecordId = this.medicalRecordRepository.nextIdentity()

        val medicalRecord = MedicalRecord(medicalRecordId)

        this.medicalRecordRepository.createMedicalRecord(medicalRecord)

        return medicalRecordId
    }

    @Transactional
    override fun getAllObservations(medicalRecordId: MedicalRecordId): List<ObservationDTO> {
        val observations: List<Observation> = this.medicalRecordRepository.getAllObservations(medicalRecordId)
        val observationDTOs: ArrayList<ObservationDTO> = arrayListOf()

        for (observation in observations) {
            val observationDTO = ObservationDTO().builder()
                .withId(observation.domainId().id())
                .withTitle(observation.title())
                .withDescription(observation.description())
                .withDate(observation.date())
                .build()

            observationDTOs.add(observationDTO)
        }

        return observationDTOs
    }

    @Transactional
    override fun createObservation(title: String, description: String, medicalRecordId: String) {
        val observationId: ObservationId = this.medicalRecordRepository.nextObservationIdentity()

        val observation = Observation(observationId, title, description, LocalDate.now(), MedicalRecordId(medicalRecordId))

        this.medicalRecordRepository.createObservation(observation)
    }

    override fun getAllMedication(medicalRecordId: MedicalRecordId): List<MedicationDTO> {
        val medication: List<Medication> = this.medicalRecordRepository.getAllMedication(medicalRecordId)
        val medicationDTOs: ArrayList<MedicationDTO> = arrayListOf()

        for (med in medication) {
            val medicationDTO = MedicationDTO().builder()
                .withId(med.domainId().id())
                .withTitle(med.title())
                .withDescription(med.description())
                .withStartDate(med.startDate())
                .withEndDate(med.endDate())
                .withMedicalRecordId(med.medicalRecordDomainId().id())
                .build()

            medicationDTOs.add(medicationDTO)
        }

        return medicationDTOs
    }

    override fun getIntakeByMedicationId(medicationId: MedicationId): List<IntakeDTO> {
        val intakes: List<Intake> = this.medicalRecordRepository.getIntakeByMedicationId(medicationId)
        val intakeDTOs: ArrayList<IntakeDTO> = arrayListOf()

        for (intake in intakes) {
            val intakeDTO = IntakeDTO().builder()
                .withId(intake.domainId().id())
                .withTime(intake.time())
                .withAmount(intake.amount())
                .withUnit(intake.unit())
                .withMedicationId(intake.medicationDomainId().id())
                .build()

            intakeDTOs.add(intakeDTO)
        }

        return intakeDTOs
    }

}