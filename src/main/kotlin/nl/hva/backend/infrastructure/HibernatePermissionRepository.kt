package nl.hva.backend.infrastructure

import nl.hva.backend.domain.Diagnosis
import nl.hva.backend.domain.Exercise
import nl.hva.backend.domain.Medication
import nl.hva.backend.domain.Note
import nl.hva.backend.domain.api.PermissionRepository
import nl.hva.backend.domain.ids.*
import nl.hva.backend.domain.many_to_many.DiagnosisCareProviderRelation
import nl.hva.backend.domain.many_to_many.ExerciseCareProviderRelation
import nl.hva.backend.domain.many_to_many.MedicationCareProviderRelation
import nl.hva.backend.domain.many_to_many.NoteCareProviderRelation
import org.springframework.stereotype.Repository
import java.time.LocalDate
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.Query
import javax.persistence.TypedQuery

@Repository
class HibernatePermissionRepository : PermissionRepository {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    /**
     ********************************** Medication **********************************
     */

    override fun getMedicationCareProviderRelationById(
        careProviderId: CareProviderId
    ): List<MedicationCareProviderRelation> {
        val query: TypedQuery<MedicationCareProviderRelation> = this.entityManager.createQuery(
            "SELECT mcp from MedicationCareProviderRelation mcp WHERE mcp.cpDomainId = ?1",
            MedicationCareProviderRelation::class.java
        )
            .setParameter(1, careProviderId)
        return query.resultList
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

    override fun createPermissionLinkMedication(medicationCareProviderRelation: MedicationCareProviderRelation) {
        this.entityManager.persist(medicationCareProviderRelation)
    }

    override fun removeExpiredMedicationPermissions(currentDay: LocalDate) {
        val query: Query = this.entityManager.createQuery(
            "delete from MedicationCareProviderRelation where validDate < ?1"
        )
            .setParameter(1, currentDay)
        query.executeUpdate()
    }

    override fun removeSelectedMedicationPermission(medicationId: MedicationId, careProviderId: CareProviderId) {
        val query: Query = this.entityManager.createQuery(
            "delete from MedicationCareProviderRelation where medicationId = ?1 and cpDomainId = ?2"
        )
            .setParameter(1, medicationId)
            .setParameter(2, careProviderId)
        query.executeUpdate()
    }

    /**
     ********************************** Notes **********************************
     */

    override fun getNoteCareProviderRelationById(careProviderId: CareProviderId): List<NoteCareProviderRelation> {
        val query: TypedQuery<NoteCareProviderRelation> = this.entityManager.createQuery(
            "SELECT ncp from NoteCareProviderRelation ncp WHERE ncp.cpDomainId = ?1",
            NoteCareProviderRelation::class.java
        )
            .setParameter(1, careProviderId)
        return query.resultList
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

    override fun createPermissionLinkNote(noteCareProviderRelation: NoteCareProviderRelation) {
        this.entityManager.persist(noteCareProviderRelation)
    }

    override fun removeExpiredNotePermissions(currentDay: LocalDate) {
        val query: Query = this.entityManager.createQuery(
            "delete from NoteCareProviderRelation where validDate < ?1"
        )
            .setParameter(1, currentDay)
        query.executeUpdate()
    }

    override fun removeSelectedNotePermission(noteId: NoteId, careProviderId: CareProviderId) {
        val query: Query = this.entityManager.createQuery(
            "delete from NoteCareProviderRelation where noteId = ?1 and cpDomainId = ?2"
        )
            .setParameter(1, noteId)
            .setParameter(2, careProviderId)
        query.executeUpdate()
    }

    /**
     ********************************** Diagnosis **********************************
     */

    override fun getDiagnosisCareProviderRelationById(careProviderId: CareProviderId): List<DiagnosisCareProviderRelation> {
        val query: TypedQuery<DiagnosisCareProviderRelation> = this.entityManager.createQuery(
            "SELECT dcp from DiagnosisCareProviderRelation dcp WHERE dcp.cpDomainId = ?1",
            DiagnosisCareProviderRelation::class.java
        )
            .setParameter(1, careProviderId)
        return query.resultList
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

    override fun createPermissionLinkDiagnosis(diagnosisCareProviderRelation: DiagnosisCareProviderRelation) {
        this.entityManager.persist(diagnosisCareProviderRelation)
    }

    override fun removeExpiredDiagnosisPermissions(currentDay: LocalDate) {
        val query: Query = this.entityManager.createQuery(
            "delete from DiagnosisCareProviderRelation where validDate < ?1"
        )
            .setParameter(1, currentDay)
        query.executeUpdate()
    }

    override fun removeSelectedDiagnosisPermission(diagnosisId: DiagnosisId, careProviderId: CareProviderId) {
        val query: Query = this.entityManager.createQuery(
            "delete from DiagnosisCareProviderRelation where diagnosisId = ?1 and cpDomainId = ?2"
        )
            .setParameter(1, diagnosisId)
            .setParameter(2, careProviderId)
        query.executeUpdate()
    }

    /**
     ********************************** Exercise **********************************
     */

    override fun getExerciseCareProviderRelationById(careProviderId: CareProviderId): List<ExerciseCareProviderRelation> {
        val query: TypedQuery<ExerciseCareProviderRelation> = this.entityManager.createQuery(
            "SELECT excp from ExerciseCareProviderRelation excp WHERE excp.cpDomainId = ?1",
            ExerciseCareProviderRelation::class.java
        )
            .setParameter(1, careProviderId)
        return query.resultList
    }

    override fun getExerciseByIdAndMr(exerciseId: ExerciseId, medicalRecordId: MedicalRecordId): Exercise {
        val query: TypedQuery<Exercise> = this.entityManager.createQuery(
            "SELECT exer FROM Exercise exer WHERE exer.medicalRecordDomainId = ?1 AND exer.domainId = ?2",
            Exercise::class.java
        )
            .setParameter(1, medicalRecordId)
            .setParameter(2, exerciseId)

        return query.singleResult
    }

    override fun createPermissionLinkExercise(exerciseCareProviderRelation: ExerciseCareProviderRelation) {
        this.entityManager.persist(exerciseCareProviderRelation)
    }

    override fun removeExpiredExercisePermissions(currentDay: LocalDate) {
        val query: Query = this.entityManager.createQuery(
            "delete from ExerciseCareProviderRelation where validDate < ?1"
        )
            .setParameter(1, currentDay)
        query.executeUpdate()
    }

    override fun removeSelectedExercisePermission(exerciseId: ExerciseId, careProviderId: CareProviderId) {
        val query: Query = this.entityManager.createQuery(
            "delete from ExersiseCareProviderRelation where exerciseId = ?1 and cpDomainId = ?2"
        )
            .setParameter(1, exerciseId)
            .setParameter(2, careProviderId)
        query.executeUpdate()
    }
}
