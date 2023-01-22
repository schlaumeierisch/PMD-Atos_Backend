package nl.hva.backend.application

import nl.hva.backend.application.api.ImageService
import nl.hva.backend.application.dto.ImageDTO
import nl.hva.backend.domain.Exercise
import nl.hva.backend.domain.Image
import nl.hva.backend.domain.api.ImageRepository
import nl.hva.backend.domain.ids.ExerciseId
import nl.hva.backend.domain.ids.MedicalRecordId
import nl.hva.backend.domain.ids.PatientId
import nl.hva.backend.rest.exceptions.NotExistingException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.io.ByteArrayOutputStream
import java.time.LocalDate
import java.util.zip.Deflater
import java.util.zip.Inflater

@Service
class ImageServiceImpl : ImageService {

    @Autowired
    private lateinit var imageRepository: ImageRepository

    @Transactional
    override fun uploadImage(
        patientId: String,
        name: String,
        type: String,
        image: ByteArray,
    ) {
        val patient = PatientId(patientId)
        val imageToUpload = Image(patient, name, type, image)
        this.imageRepository.uploadImage(imageToUpload)
    }

    @Transactional
    override fun getImageByPatientId(patientId: PatientId): List<ImageDTO> {
        val images: List<Image> = this.imageRepository.getImageByPatientId(patientId)

        return if (images.isNotEmpty()) {
            listOf(ImageDTO.fromImage(images[0]))
        } else {
            throw NotExistingException("Image with patientId \'${patientId.id()}\' does not exist.")
        }
    }
}