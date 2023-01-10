package nl.hva.backend.infrastructure

import nl.hva.backend.domain.*
import nl.hva.backend.domain.api.MedicalRecordRepository
import nl.hva.backend.domain.ids.*
import org.springframework.stereotype.Repository
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.TypedQuery

@Repository
class HibernateMedicalRecordRepository : MedicalRecordRepository {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun nextIdentity(): MedicalRecordId = MedicalRecordId(UUID.randomUUID().toString())

    override fun nextNoteIdentity(): NoteId = NoteId(UUID.randomUUID().toString())

    override fun nextDiagnosisIdentity(): DiagnosisId = DiagnosisId(UUID.randomUUID().toString())

    override fun nextMedicationIdentity(): MedicationId = MedicationId(UUID.randomUUID().toString())

    override fun nextExerciseIdentity(): ExerciseId = ExerciseId(UUID.randomUUID().toString())

    override fun getMedicalRecord(medicalRecordId: MedicalRecordId): List<MedicalRecord> {
        val query: TypedQuery<MedicalRecord> = this.entityManager.createQuery(
            "SELECT mr FROM MedicalRecord mr WHERE mr.domainId = ?1", MedicalRecord::class.java
        )
        return query.setParameter(1, medicalRecordId).resultList
    }

    override fun getAllNotes(medicalRecordId: MedicalRecordId): List<Note> {
        val query: TypedQuery<Note> = this.entityManager.createQuery(
            "SELECT no FROM Note no WHERE no.medicalRecordDomainId = ?1", Note::class.java
        )
        return query.setParameter(1, medicalRecordId).resultList
    }

    override fun getNoteByIdAndMedicalRecordId(noteId: NoteId, medicalRecordId: MedicalRecordId): List<Note> {
        val query: TypedQuery<Note> = this.entityManager.createQuery(
            "SELECT note FROM Note note WHERE note.medicalRecordDomainId = ?1 AND note.domainId = ?2",
            Note::class.java
        )
            .setParameter(1, medicalRecordId)
            .setParameter(2, noteId)

        return query.resultList
    }

    override fun createNote(note: Note) {
        this.entityManager.persist(note)
    }

    override fun deleteNote(noteId: NoteId) {
        val query: TypedQuery<Note> = this.entityManager.createQuery(
            "SELECT no FROM Note no WHERE no.domainId = ?1", Note::class.java
        )
        val result: Note = query.setParameter(1, noteId).singleResult
        this.entityManager.remove(result)
    }

    override fun getAllMedication(medicalRecordId: MedicalRecordId): List<Medication> {
        val query: TypedQuery<Medication> = this.entityManager.createQuery(
            "SELECT med FROM Medication med WHERE med.medicalRecordDomainId = ?1", Medication::class.java
        )
        return query.setParameter(1, medicalRecordId).resultList
    }

    override fun getMedicationById(medicationId: MedicationId): List<Medication> {
        val query: TypedQuery<Medication> = this.entityManager.createQuery(
            "SELECT med FROM Medication med WHERE med.domainId = ?1", Medication::class.java
        )
        return query.setParameter(1, medicationId).resultList
    }

    override fun getMedicationByIdAndMedicalRecordId(
        medicationId: MedicationId,
        medicalRecordId: MedicalRecordId
    ): List<Medication> {
        val query: TypedQuery<Medication> = this.entityManager.createQuery(
            "SELECT med FROM Medication med WHERE med.medicalRecordDomainId = ?1 AND med.domainId = ?2",
            Medication::class.java
        )
            .setParameter(1, medicalRecordId)
            .setParameter(2, medicationId)

        return query.resultList
    }

    override fun createMedication(medication: Medication) {
        this.entityManager.persist(medication)
    }

    override fun getIntakeByMedicationId(medicationId: MedicationId): List<Intake> {
        val query: TypedQuery<Intake> = this.entityManager.createQuery(
            "SELECT itk FROM Intake itk WHERE itk.medicationDomainId = ?1", Intake::class.java
        )
        return query.setParameter(1, medicationId).resultList
    }

    override fun getAllDiagnoses(medicalRecordId: MedicalRecordId): List<Diagnosis> {
        val query: TypedQuery<Diagnosis> = this.entityManager.createQuery(
            "SELECT diag FROM Diagnosis diag WHERE diag.medicalRecordDomainId = ?1", Diagnosis::class.java
        )
        return query.setParameter(1, medicalRecordId).resultList
    }

    override fun getDiagnosisByIdAndMedicalRecordId(diagnosisId: DiagnosisId, medicalRecordId: MedicalRecordId): List<Diagnosis> {
        val query: TypedQuery<Diagnosis> = this.entityManager.createQuery(
            "SELECT diag FROM Diagnosis diag WHERE diag.medicalRecordDomainId = ?1 AND diag.domainId = ?2",
            Diagnosis::class.java
        )
            .setParameter(1, medicalRecordId)
            .setParameter(2, diagnosisId)

        return query.resultList
    }

    override fun createDiagnosis(diagnosis: Diagnosis) {
        this.entityManager.persist(diagnosis)
    }

    override fun getAllExercises(medicalRecordId: MedicalRecordId): List<Exercise> {
        val query: TypedQuery<Exercise> = this.entityManager.createQuery(
            "SELECT exerc FROM Exercise exerc WHERE exerc.medicalRecordDomainId = ?1", Exercise::class.java
        )
        return query.setParameter(1, medicalRecordId).resultList
    }

    override fun getExerciseByIdAndMedicalRecordId(exerciseId: ExerciseId, medicalRecordId: MedicalRecordId): List<Exercise> {
        val query: TypedQuery<Exercise> = this.entityManager.createQuery(
            "SELECT exer FROM Exercise exer WHERE exer.medicalRecordDomainId = ?1 AND exer.domainId = ?2",
            Exercise::class.java
        )
            .setParameter(1, medicalRecordId)
            .setParameter(2, exerciseId)

        return query.resultList
    }

    override fun createExercise(exercise: Exercise) {
        this.entityManager.persist(exercise)
    }

    override fun deleteExercise(exerciseId: ExerciseId) {
        val query: TypedQuery<Exercise> = this.entityManager.createQuery(
            "SELECT exerc FROM Exercise exerc WHERE exerc.domainId = ?1", Exercise::class.java
        )
        val result: Exercise = query.setParameter(1, exerciseId).singleResult
        this.entityManager.remove(result)
    }

    override fun getMedicationOfPatientByIdAndMr(
        medicationId: MedicationId,
        medicalRecordId: MedicalRecordId
    ): Medication {
        val query: TypedQuery<Medication> = this.entityManager.createQuery(
            "SELECT med FROM Medication med WHERE med.medicalRecordDomainId = ?1 AND med.domainId = ?2",
            Medication::class.java
        )
            .setParameter(1, medicalRecordId)
            .setParameter(2, medicationId)

        return query.singleResult
    }

    override fun getNoteByIdAndMr(noteId: NoteId, medicalRecordId: MedicalRecordId): Note {
        val query: TypedQuery<Note> = this.entityManager.createQuery(
            "SELECT note FROM Note note WHERE note.medicalRecordDomainId = ?1 AND note.domainId = ?2",
            Note::class.java
        )
            .setParameter(1, medicalRecordId)
            .setParameter(2, noteId)

        return query.singleResult
    }

    override fun getDiagnosisByIdAndMr(diagnosisId: DiagnosisId, medicalRecordId: MedicalRecordId): Diagnosis {
        val query: TypedQuery<Diagnosis> = this.entityManager.createQuery(
            "SELECT diag FROM Diagnosis diag WHERE diag.medicalRecordDomainId = ?1 AND diag.domainId = ?2",
            Diagnosis::class.java
        )
            .setParameter(1, medicalRecordId)
            .setParameter(2, diagnosisId)

        return query.singleResult
    }

}