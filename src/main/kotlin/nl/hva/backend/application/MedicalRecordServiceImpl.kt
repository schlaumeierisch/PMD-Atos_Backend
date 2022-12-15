package nl.hva.backend.application

import nl.hva.backend.application.api.MedicalRecordService
import nl.hva.backend.application.dto.*
import nl.hva.backend.domain.*
import nl.hva.backend.domain.api.MedicalRecordRepository
import nl.hva.backend.domain.ids.*
import nl.hva.backend.domain.value_objects.DiagnosisType
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
    override fun getMedicalRecord(medicalRecordId: MedicalRecordId): List<MedicalRecordDTO> {
        val medicalRecord: List<MedicalRecord> = this.medicalRecordRepository.getMedicalRecord(medicalRecordId)

        return if (medicalRecord.isNotEmpty()) {
            listOf(MedicalRecordDTO.fromMedicalRecord(medicalRecord[0]))
        } else {
            emptyList()
        }
    }

    @Transactional
    override fun getAllNotes(medicalRecordId: MedicalRecordId): List<NoteDTO> {
        val notes: List<Note> = this.medicalRecordRepository.getAllNotes(medicalRecordId)

        return NoteDTO.fromNotes(notes)
    }

    @Transactional
    override fun createNote(title: String, description: String, medicalRecordId: MedicalRecordId) {
        val noteId: NoteId = this.medicalRecordRepository.nextNoteIdentity()

        val note = Note(noteId, title, description, LocalDate.now(), medicalRecordId)

        this.medicalRecordRepository.createNote(note)
    }

    @Transactional
    override fun getAllMedication(medicalRecordId: MedicalRecordId): List<MedicationDTO> {
        val medication: List<Medication> = this.medicalRecordRepository.getAllMedication(medicalRecordId)

        return MedicationDTO.fromMedication(medication)
    }

    @Transactional
    override fun getMedicationById(medicationId: MedicationId): List<MedicationDTO> {
        val medication: List<Medication> = this.medicalRecordRepository.getMedicationById(medicationId)

        return if (medication.isNotEmpty()) {
            listOf(MedicationDTO.fromMedication(medication[0]))
        } else {
            emptyList()
        }
    }

    @Transactional
    override fun createMedication(
        title: String,
        description: String,
        startDate: LocalDate,
        endDate: LocalDate?,
        medicalRecordId: MedicalRecordId
    ) {
        val medicationId: MedicationId = this.medicalRecordRepository.nextMedicationIdentity()

        val medication = Medication(medicationId, title, description, startDate, endDate, medicalRecordId)

        this.medicalRecordRepository.createMedication(medication)
    }

    @Transactional
    override fun getIntakeByMedicationId(medicationId: MedicationId): List<IntakeDTO> {
        val intakes: List<Intake> = this.medicalRecordRepository.getIntakeByMedicationId(medicationId)

        return IntakeDTO.fromIntakes(intakes)
    }

    @Transactional
    override fun getAllDiagnoses(medicalRecordId: MedicalRecordId): List<DiagnosisDTO> {
        val diagnoses: List<Diagnosis> = this.medicalRecordRepository.getAllDiagnoses(medicalRecordId)

        return DiagnosisDTO.fromDiagnoses(diagnoses)
    }

    @Transactional
    override fun createDiagnosis(
        title: String,
        diagnosisType: Enum<DiagnosisType>,
        dateDiagnosed: LocalDate,
        cause: String,
        treatment: String,
        advice: String,
        medicalRecordId: MedicalRecordId
    ) {
        val diagnosisId: DiagnosisId = this.medicalRecordRepository.nextDiagnosisIdentity()

        val diagnosis = Diagnosis(diagnosisId, title, diagnosisType, dateDiagnosed, cause, treatment, advice, medicalRecordId)

        this.medicalRecordRepository.createDiagnosis(diagnosis)
    }

    @Transactional
    override fun getAllExercises(medicalRecordId: MedicalRecordId): List<ExerciseDTO> {
        val exercises: List<Exercise> = this.medicalRecordRepository.getAllExercises(medicalRecordId)

        return ExerciseDTO.fromExercises(exercises)
    }

    @Transactional
    override fun createExercise(
        title: String,
        description: String,
        startDate: LocalDate,
        endDate: LocalDate?,
        medicalRecordId: MedicalRecordId
    ) {
        val exerciseId: ExerciseId = this.medicalRecordRepository.nextExerciseIdentity()

        val exercise = Exercise(exerciseId, title, description, startDate, endDate, medicalRecordId)

        this.medicalRecordRepository.createExercise(exercise)
    }

    @Transactional
    override fun deleteNote(noteId: NoteId) {
        this.medicalRecordRepository.deleteNote(noteId)
    }

}