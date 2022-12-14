package nl.hva.backend.infrastructure

import nl.hva.backend.domain.*
import nl.hva.backend.domain.api.MedicalRecordRepository
import nl.hva.backend.domain.ids.DiagnosisId
import nl.hva.backend.domain.ids.MedicalRecordId
import nl.hva.backend.domain.ids.MedicationId
import nl.hva.backend.domain.ids.NoteId
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

    override fun createMedicalRecord(medicalRecord: MedicalRecord) {
        this.entityManager.persist(medicalRecord)
    }

    override fun getAllNotes(medicalRecordId: MedicalRecordId): List<Note> {
        val query: TypedQuery<Note> = this.entityManager.createQuery(
            "SELECT no FROM Note no WHERE no.medicalRecordDomainId = ?1", Note::class.java
        )
        return query.setParameter(1, medicalRecordId).resultList
    }

    override fun createNote(note: Note) {
        this.entityManager.persist(note)
    }

    override fun getAllMedication(medicalRecordId: MedicalRecordId): List<Medication> {
        val query: TypedQuery<Medication> = this.entityManager.createQuery(
            "SELECT med FROM Medication med WHERE med.medicalRecordDomainId = ?1", Medication::class.java
        )
        return query.setParameter(1, medicalRecordId).resultList
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

    override fun createDiagnosis(diagnosis: Diagnosis) {
        this.entityManager.persist(diagnosis)
    }

    override fun getAllExercises(medicalRecordId: MedicalRecordId): List<Exercise> {
        val query: TypedQuery<Exercise> = this.entityManager.createQuery(
            "SELECT exerc FROM Exercise exerc WHERE exerc.medicalRecordDomainId = ?1", Exercise::class.java
        )
        return query.setParameter(1, medicalRecordId).resultList
    }

}