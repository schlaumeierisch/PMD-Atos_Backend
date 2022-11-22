package nl.hva.backend.application.dto

import nl.hva.backend.application.dto.value_objects.AddressDTO
import nl.hva.backend.domain.CareProvider
import nl.hva.backend.domain.value_objects.Specialism
import java.util.*

class CareProviderDTO {
    private lateinit var id: String
    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var addressDTO: AddressDTO
    private lateinit var phoneNumber: String
    private lateinit var specialism: Enum<Specialism>

    private lateinit var patientDTOs: MutableSet<PatientDTO>

    companion object {
        fun fromCareProvider(careProvider: CareProvider): CareProviderDTO {
            val careProviderDTO = CareProviderDTO()

            careProviderDTO.id = careProvider.id().toString()
            careProviderDTO.firstName = careProvider.firstName()
            careProviderDTO.lastName = careProvider.lastName()
            careProviderDTO.addressDTO = AddressDTO.fromAddress(careProvider.address())
            careProviderDTO.phoneNumber = careProvider.phoneNumber()
            careProviderDTO.specialism = careProvider.specialism()

            careProviderDTO.patientDTOs = mutableSetOf()
            for (patient in careProvider.patients()) {
                careProviderDTO.patientDTOs.add(PatientDTO.fromPatient(patient))
            }

            return careProviderDTO
        }
    }

    // getter
    fun id(): String = this.id
    fun firstName(): String = this.firstName
    fun lastName(): String = this.lastName
    fun address(): AddressDTO = this.addressDTO
    fun phoneNumber(): String = this.phoneNumber
    fun specialism(): Enum<Specialism> = this.specialism
    fun patientDTOs(): MutableSet<PatientDTO> = this.patientDTOs

    fun builder(): Builder {
        return Builder()
    }

    class Builder {
        private var instance: CareProviderDTO = CareProviderDTO()

        fun withId(id: String): Builder {
            instance.id = id
            return this
        }

        fun withFirstName(firstName: String): Builder {
            instance.firstName = firstName
            return this
        }

        fun withLastName(lastName: String): Builder {
            instance.lastName = lastName
            return this
        }

        fun withAddress(address: AddressDTO): Builder {
            instance.addressDTO = address
            return this
        }

        fun withPhoneNumber(phoneNumber: String): Builder {
            instance.phoneNumber = phoneNumber
            return this
        }

        fun withSpecialism(specialism: Enum<Specialism>): Builder {
            instance.specialism = specialism
            return this
        }

        fun withPatientDTOs(patientDTOs: MutableSet<PatientDTO>): Builder {
            instance.patientDTOs = patientDTOs
            return this
        }

        fun build(): CareProviderDTO {
            Objects.requireNonNull(instance.id, "id must be set in CareProviderDTO")
            Objects.requireNonNull(instance.firstName, "firstName must be set in CareProviderDTO")
            Objects.requireNonNull(instance.lastName, "lastName must be set in CareProviderDTO")
            Objects.requireNonNull(instance.addressDTO, "addressDTO must be set in CareProviderDTO")
            Objects.requireNonNull(instance.phoneNumber, "phoneNumber must be set in CareProviderDTO")
            Objects.requireNonNull(instance.specialism, "specialism must be set in CareProviderDTO")
            Objects.requireNonNull(instance.patientDTOs, "patientDTOs must be set in CareProviderDTO")

            return instance
        }
    }
}