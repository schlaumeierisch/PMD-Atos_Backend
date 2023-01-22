package nl.hva.backend.infrastructure

import nl.hva.backend.domain.Image
import nl.hva.backend.domain.api.ImageRepository
import nl.hva.backend.domain.ids.PatientId
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.TypedQuery

@Repository
class HibernateImageRepository : ImageRepository {
    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun uploadImage(image: Image) {
        println(image.patientId().id())
        this.entityManager.persist(image)
    }

    override fun getImageByPatientId(patientId: PatientId): List<Image> {
        val query: TypedQuery<Image> = this.entityManager.createQuery(
            "SELECT image FROM Image image WHERE image.patientId = ?1", Image::class.java
        )
        return query.setParameter(1, patientId).resultList
    }
}