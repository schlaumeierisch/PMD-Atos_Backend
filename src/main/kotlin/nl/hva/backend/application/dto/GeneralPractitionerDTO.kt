package nl.hva.backend.application.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import nl.hva.backend.application.dto.value_objects.AddressDTO
import nl.hva.backend.domain.GeneralPractitioner
import java.util.*

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class GeneralPractitionerDTO {
    private lateinit var id: String
    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var addressDTO: AddressDTO
    private lateinit var phoneNumber: String

    private lateinit var patientDTOs: MutableSet<PatientDTO>

    companion object {
        fun fromGeneralPractitioner(generalPractitioner: GeneralPractitioner, withIteration: Boolean): GeneralPractitionerDTO {
            val generalPractitionerDTO = GeneralPractitionerDTO()

            generalPractitionerDTO.id = generalPractitioner.domainId().id()
            generalPractitionerDTO.firstName = generalPractitioner.firstName()
            generalPractitionerDTO.lastName = generalPractitioner.lastName()
            generalPractitionerDTO.addressDTO = AddressDTO.fromAddress(generalPractitioner.address())
            generalPractitionerDTO.phoneNumber = generalPractitioner.phoneNumber()

            if (withIteration) {
                for (patient in generalPractitioner.patients()) {
                    generalPractitionerDTO.patientDTOs.add(PatientDTO.fromPatient(patient))
                }
            } else {
                generalPractitionerDTO.patientDTOs = mutableSetOf()
            }

            return generalPractitionerDTO
        }
    }

    // getter
    fun id(): String = this.id
    fun firstName(): String = this.firstName
    fun lastName(): String = this.lastName
    fun address(): AddressDTO = this.addressDTO
    fun phoneNumber(): String = this.phoneNumber
    fun patientDTOs(): MutableSet<PatientDTO> = this.patientDTOs

    fun builder(): Builder {
        return Builder()
    }

    class Builder {
        private var instance: GeneralPractitionerDTO = GeneralPractitionerDTO()

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

        fun withPatientDTOs(patientDTOs: MutableSet<PatientDTO>): Builder {
            instance.patientDTOs = patientDTOs
            return this
        }

        fun build(): GeneralPractitionerDTO {
            Objects.requireNonNull(instance.id, "id must be set in GeneralPractitionerDTO")
            Objects.requireNonNull(instance.firstName, "firstName must be set in GeneralPractitionerDTO")
            Objects.requireNonNull(instance.lastName, "lastName must be set in GeneralPractitionerDTO")
            Objects.requireNonNull(instance.addressDTO, "addressDTO must be set in GeneralPractitionerDTO")
            Objects.requireNonNull(instance.phoneNumber, "phoneNumber must be set in GeneralPractitionerDTO")
            Objects.requireNonNull(instance.patientDTOs, "patientDTOs must be set in GeneralPractitionerDTO")

            return instance
        }
    }
}