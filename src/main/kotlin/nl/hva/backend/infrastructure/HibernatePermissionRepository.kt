package nl.hva.backend.infrastructure

import nl.hva.backend.domain.api.PermissionRepository
import nl.hva.backend.domain.ids.CareProviderId
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


    override fun getMedicationCareProviderRelationByIds(
        medicationId: MedicationId,
        careProviderId: CareProviderId
    ): List<MedicationCareProviderRelation> {
        val query: TypedQuery<MedicationCareProviderRelation> = this.entityManager.createQuery(
            "SELECT mcp from MedicationCareProviderRelation mcp WHERE mcp.medicationId = ?1" +
                    " AND mcp.cpDomainId = ?2",
            MedicationCareProviderRelation::class.java
        )
            .setParameter(1, medicationId)
            .setParameter(2, careProviderId)
        return query.resultList
    }
}
