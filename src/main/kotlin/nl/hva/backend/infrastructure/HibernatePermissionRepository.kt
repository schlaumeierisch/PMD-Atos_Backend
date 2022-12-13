package nl.hva.backend.infrastructure

import nl.hva.backend.domain.Medication
import nl.hva.backend.domain.api.PermissionRepository
import nl.hva.backend.domain.ids.CareProviderId
import nl.hva.backend.domain.ids.MedicalRecordId
import nl.hva.backend.domain.ids.MedicationId
import nl.hva.backend.domain.many_to_many.MedicationCareProviderRelation
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.TypedQuery

@Repository
class HibernatePermissionRepository : PermissionRepository {

    @PersistenceContext
    private lateinit var entityManager: EntityManager


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

}
