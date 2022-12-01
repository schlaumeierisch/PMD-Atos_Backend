package nl.hva.backend.application.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import nl.hva.backend.application.dto.value_objects.AddressDTO
import nl.hva.backend.domain.GeneralPractitioner

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class GeneralPractitionerDTO {
    private lateinit var id: String
    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var addressDTO: AddressDTO
    private lateinit var phoneNumber: String

    companion object {
        fun fromGeneralPractitioner(generalPractitioner: GeneralPractitioner): GeneralPractitionerDTO {
            val generalPractitionerDTO = GeneralPractitionerDTO()

            generalPractitionerDTO.id = generalPractitioner.domainId().id()
            generalPractitionerDTO.firstName = generalPractitioner.firstName()
            generalPractitionerDTO.lastName = generalPractitioner.lastName()
            generalPractitionerDTO.addressDTO = AddressDTO.fromAddress(generalPractitioner.address())
            generalPractitionerDTO.phoneNumber = generalPractitioner.phoneNumber()

            return generalPractitionerDTO
        }

        fun fromGeneralPractitioners(generalPractitioners: List<GeneralPractitioner>): List<GeneralPractitionerDTO> {
            val generalPractitionerDTOs: ArrayList<GeneralPractitionerDTO> = arrayListOf()

            for (generalPractitioner in generalPractitioners) {
                generalPractitionerDTOs.add(fromGeneralPractitioner(generalPractitioner))
            }

            return generalPractitionerDTOs
        }
    }

    // getter
    fun id(): String = this.id
    fun firstName(): String = this.firstName
    fun lastName(): String = this.lastName
    fun address(): AddressDTO = this.addressDTO
    fun phoneNumber(): String = this.phoneNumber
}