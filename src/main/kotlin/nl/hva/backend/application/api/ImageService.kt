package nl.hva.backend.application.api

import nl.hva.backend.application.dto.ImageDTO
import nl.hva.backend.domain.Image
import nl.hva.backend.domain.ids.MedicalRecordId
import nl.hva.backend.domain.ids.PatientId
import nl.hva.backend.domain.value_objects.DiagnosisType
import java.time.LocalDate


interface ImageService {
    fun uploadImage(patientId: String, name: String, type: String, image: ByteArray)
    fun getImageByPatientId(patientId: PatientId): List<ImageDTO>
}