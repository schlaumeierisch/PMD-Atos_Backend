package nl.hva.backend.application

import nl.hva.backend.application.api.MedicalRecordService
import nl.hva.backend.application.dto.IntakeDTO
import nl.hva.backend.application.dto.MedicationDTO
import nl.hva.backend.application.dto.NoteDTO
import nl.hva.backend.domain.Intake
import nl.hva.backend.domain.MedicalRecord
import nl.hva.backend.domain.Medication
import nl.hva.backend.domain.Note
import nl.hva.backend.domain.api.MedicalRecordRepository
import nl.hva.backend.domain.ids.MedicalRecordId
import nl.hva.backend.domain.ids.MedicationId
import nl.hva.backend.domain.ids.NoteId
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
    override fun getAllNotes(medicalRecordId: MedicalRecordId): List<NoteDTO> {
        val notes: List<Note> = this.medicalRecordRepository.getAllNotes(medicalRecordId)

        return NoteDTO.fromNotes(notes)
    }

    @Transactional
    override fun createNote(title: String, description: String, medicalRecordId: String) {
        val noteId: NoteId = this.medicalRecordRepository.nextNoteIdentity()

        val note = Note(noteId, title, description, LocalDate.now(), MedicalRecordId(medicalRecordId))

        this.medicalRecordRepository.createNote(note)
    }

    override fun getAllMedication(medicalRecordId: MedicalRecordId): List<MedicationDTO> {
        val medication: List<Medication> = this.medicalRecordRepository.getAllMedication(medicalRecordId)

        return MedicationDTO.fromMedication(medication)
    }

    override fun getIntakeByMedicationId(medicationId: MedicationId): List<IntakeDTO> {
        val intakes: List<Intake> = this.medicalRecordRepository.getIntakeByMedicationId(medicationId)

        return IntakeDTO.fromIntakes(intakes)
    }

}