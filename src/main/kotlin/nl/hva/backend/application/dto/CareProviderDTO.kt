package nl.hva.backend.application.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import nl.hva.backend.application.dto.value_objects.AddressDTO
import nl.hva.backend.domain.CareProvider
import nl.hva.backend.domain.value_objects.Specialism

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class CareProviderDTO {
    private lateinit var id: String
    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var addressDTO: AddressDTO
    private lateinit var phoneNumber: String
    private lateinit var specialism: Enum<Specialism>

    companion object {
        fun fromCareProvider(careProvider: CareProvider): CareProviderDTO {
            val careProviderDTO = CareProviderDTO()

            careProviderDTO.id = careProvider.domainId().id()
            careProviderDTO.firstName = careProvider.firstName()
            careProviderDTO.lastName = careProvider.lastName()
            careProviderDTO.addressDTO = AddressDTO.fromAddress(careProvider.address())
            careProviderDTO.phoneNumber = careProvider.phoneNumber()
            careProviderDTO.specialism = careProvider.specialism()

            return careProviderDTO
        }

        fun fromCareProviders(careProviders: List<CareProvider>): List<CareProviderDTO> {
            val careProviderDTOs: ArrayList<CareProviderDTO> = arrayListOf()

            for (careProvider in careProviders) {
                careProviderDTOs.add(fromCareProvider(careProvider))
            }

            return careProviderDTOs
        }
    }

    // getter
    fun id(): String = this.id
    fun firstName(): String = this.firstName
    fun lastName(): String = this.lastName
    fun address(): AddressDTO = this.addressDTO
    fun phoneNumber(): String = this.phoneNumber
    fun specialism(): Enum<Specialism> = this.specialism
}