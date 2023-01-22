package nl.hva.backend.domain.api

import nl.hva.backend.domain.Image
import nl.hva.backend.domain.ids.PatientId

interface ImageRepository {
    fun uploadImage(image: Image)
    fun getImageByPatientId(patientId: PatientId): List<Image>
}