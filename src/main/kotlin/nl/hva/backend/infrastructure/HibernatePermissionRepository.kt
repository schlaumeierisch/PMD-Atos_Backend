package nl.hva.backend.infrastructure

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


    override fun createPermissionLinkMedication(medicationCareProviderRelation: MedicationCareProviderRelation) {
        val query: TypedQuery<MedicationCareProviderRelation> = this.entityManager.createQuery(
            "SELECT mcp FROM MedicationCareProviderRelation mcp WHERE mcp.cpDomainId = ?1 AND mcp.medicationId = ?2",
            MedicationCareProviderRelation::class.java
        )

        val result: List<MedicationCareProviderRelation> = query
            .setParameter(1, medicationCareProviderRelation.cpDomainId())
            .setParameter(2, medicationCareProviderRelation.medicationId())
            .resultList

        if (result.isNotEmpty()) {
            for (res in result) {
                this.entityManager.remove(res)
            }
        }

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


    override fun createPermissionLinkNote(noteCareProviderRelation: NoteCareProviderRelation) {
        val query: TypedQuery<NoteCareProviderRelation> = this.entityManager.createQuery(
            "SELECT ncp FROM NoteCareProviderRelation ncp WHERE ncp.cpDomainId = ?1 AND ncp.noteId = ?2",
            NoteCareProviderRelation::class.java
        )

        val result: List<NoteCareProviderRelation> = query
            .setParameter(1, noteCareProviderRelation.cpDomainId())
            .setParameter(2, noteCareProviderRelation.noteId())
            .resultList

        if (result.isNotEmpty()) {
            for (res in result) {
                this.entityManager.remove(res)
            }
        }

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


    override fun createPermissionLinkDiagnosis(diagnosisCareProviderRelation: DiagnosisCareProviderRelation) {
        val query: TypedQuery<DiagnosisCareProviderRelation> = this.entityManager.createQuery(
            "SELECT dcp FROM DiagnosisCareProviderRelation dcp WHERE dcp.cpDomainId = ?1 AND dcp.diagnosisId = ?2",
            DiagnosisCareProviderRelation::class.java
        )

        val result: List<DiagnosisCareProviderRelation> = query
            .setParameter(1, diagnosisCareProviderRelation.cpDomainId())
            .setParameter(2, diagnosisCareProviderRelation.diagnosisId())
            .resultList

        if (result.isNotEmpty()) {
            for (res in result) {
                this.entityManager.remove(res)
            }
        }

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


    override fun createPermissionLinkExercise(exerciseCareProviderRelation: ExerciseCareProviderRelation) {
        val query: TypedQuery<ExerciseCareProviderRelation> = this.entityManager.createQuery(
            "SELECT ecp FROM ExerciseCareProviderRelation ecp WHERE ecp.cpDomainId = ?1 AND ecp.exerciseId = ?2",
            ExerciseCareProviderRelation::class.java
        )

        val result: List<ExerciseCareProviderRelation> = query
            .setParameter(1, exerciseCareProviderRelation.cpDomainId())
            .setParameter(2, exerciseCareProviderRelation.exerciseId())
            .resultList

        if (result.isNotEmpty()) {
            for (res in result) {
                this.entityManager.remove(res)
            }
        }

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
