package nl.hva.backend.application.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import nl.hva.backend.domain.Image

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class ImageDTO {
    private lateinit var patientId: String
    private lateinit var name: String
    private lateinit var type: String
    private lateinit var image: ByteArray


    companion object {
        fun fromImage(image: Image): ImageDTO {
            val imageDTO = ImageDTO()

            imageDTO.patientId = image.patientId().id()
            imageDTO.name = image.name()
            imageDTO.type = image.type()
            imageDTO.image = image.image()

            return imageDTO
        }

        fun fromImages(images: List<Image>): List<ImageDTO> {
            val imageDTOs: ArrayList<ImageDTO> = arrayListOf()

            for (image in images) {
                imageDTOs.add(fromImage(image))
            }

            return imageDTOs
        }
    }

    // getter
    fun patientId(): String = this.patientId
    fun name(): String = this.name
    fun type(): String = this.type
    fun image(): ByteArray = this.image
}