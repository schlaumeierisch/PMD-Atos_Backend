package nl.hva.backend.rest

import nl.hva.backend.application.api.ImageService
import nl.hva.backend.application.dto.ImageDTO
import nl.hva.backend.domain.api.ImageRepository
import nl.hva.backend.domain.ids.PatientId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/rest/images/")
class ImageRestController {

    @Autowired
    private lateinit var imageService: ImageService

    @Autowired
    private lateinit var imageRepository: ImageRepository


    @PostMapping("/createImage")
    fun uploadImage(@RequestParam file: MultipartFile, @RequestParam patientId: String): ResponseEntity<String> {
        this.imageService.uploadImage(patientId, file.name, file.contentType.toString(), file.bytes)

        return ResponseEntity.status(HttpStatus.OK).body(("Image uploaded successfully: " +
                file.originalFilename))
    }

    @GetMapping("/getImageByPatientId/{patientId}" )
    @ResponseBody
    fun getImageByPatientId(@PathVariable patientId: String): ResponseEntity<List<ImageDTO>> {
        val imageDTOs: List<ImageDTO> = this.imageService.getImageByPatientId(PatientId(patientId))

        return ResponseEntity.status(HttpStatus.OK).body(imageDTOs)
    }
}