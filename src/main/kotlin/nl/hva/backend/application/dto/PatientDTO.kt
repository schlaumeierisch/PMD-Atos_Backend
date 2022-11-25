package nl.hva.backend.application.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import nl.hva.backend.application.dto.value_objects.AddressDTO
import nl.hva.backend.domain.Patient
import java.time.LocalDate
import java.util.*

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class PatientDTO {
    private lateinit var id: String
    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var addressDTO: AddressDTO
    private lateinit var gender: String
    private lateinit var birthDate: LocalDate
    private lateinit var phoneNumber: String
    private lateinit var email: String
    private var isUsingApp: Boolean = false

    private var contactPersonDTOs: MutableSet<ContactPersonDTO> = mutableSetOf()
    private var careProviderDTOs: MutableSet<CareProviderDTO> = mutableSetOf()

    // many-to-one
    private var gpId: String = ""

    companion object {
        fun fromPatient(patient: Patient): PatientDTO {
            val patientDTO = PatientDTO()

            patientDTO.id = patient.domainId().id()
            patientDTO.firstName = patient.firstName()
            patientDTO.lastName = patient.lastName()
            patientDTO.addressDTO = AddressDTO.fromAddress(patient.address())
            patientDTO.gender = patient.gender()
            patientDTO.birthDate = patient.birthDate()
            patientDTO.phoneNumber = patient.phoneNumber()
            patientDTO.email = patient.email()
            patientDTO.isUsingApp = patient.isUsingApp()

            patientDTO.gpId = patient.gpDomainId().id()

            for (careProvider in patient.careProviders()) {
                patientDTO.careProviderDTOs.add(CareProviderDTO.fromCareProvider(careProvider))
            }

            for (contactPerson in patient.contactPersons()) {
                patientDTO.contactPersonDTOs.add(ContactPersonDTO.fromContactPerson(contactPerson))
            }

            return patientDTO
        }
    }

    // getter
    fun id(): String = this.id
    fun firstName(): String = this.firstName
    fun lastName(): String = this.lastName
    fun addressDTO(): AddressDTO = this.addressDTO
    fun gender(): String = this.gender
    fun birthDate(): LocalDate = this.birthDate
    fun phoneNumber(): String = this.phoneNumber
    fun email(): String = this.email
    fun isUsingApp(): Boolean = this.isUsingApp
    fun contactPersonDTOs(): MutableSet<ContactPersonDTO> = this.contactPersonDTOs
    fun careProviderDTOs(): MutableSet<CareProviderDTO> = this.careProviderDTOs
    fun gpId(): String = this.gpId

    fun builder(): Builder {
        return Builder()
    }

    class Builder {
        private var instance: PatientDTO = PatientDTO()

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

        fun withAddressDTO(address: AddressDTO): Builder {
            instance.addressDTO = address
            return this
        }

        fun withGender(gender: String): Builder {
            instance.gender = gender
            return this
        }

        fun withBirthDate(birthDate: LocalDate): Builder {
            instance.birthDate = birthDate
            return this
        }

        fun withPhoneNumber(phoneNumber: String): Builder {
            instance.phoneNumber = phoneNumber
            return this
        }

        fun withEmail(email: String): Builder {
            instance.email = email
            return this
        }

        fun withIsUsingApp(isUsingApp: Boolean): Builder {
            instance.isUsingApp = isUsingApp
            return this
        }

        fun withContactPersonDTOs(contactPersonDTOs: MutableSet<ContactPersonDTO>): Builder {
            instance.contactPersonDTOs = contactPersonDTOs
            return this
        }

        fun withCareProviderDTOs(careProviderDTOs: MutableSet<CareProviderDTO>): Builder {
            instance.careProviderDTOs = careProviderDTOs
            return this
        }

        fun withGpId(gpId: String): Builder {
            instance.gpId = gpId
            return this
        }

        fun build(): PatientDTO {
            Objects.requireNonNull(instance.firstName, "street must be set in PatientDTO")
            Objects.requireNonNull(instance.lastName, "lastName must be set in PatientDTO")
            Objects.requireNonNull(instance.addressDTO, "addressDTO must be set in PatientDTO")
            Objects.requireNonNull(instance.gender, "gender must be set in PatientDTO")
            Objects.requireNonNull(instance.birthDate, "birthDate must be set in PatientDTO")
            Objects.requireNonNull(instance.phoneNumber, "phoneNumber must be set in PatientDTO")
            Objects.requireNonNull(instance.email, "email must be set in PatientDTO")
            Objects.requireNonNull(instance.contactPersonDTOs, "contactPersonDTOs must be set in PatientDTO")
            Objects.requireNonNull(instance.careProviderDTOs, "careProviderDTOs must be set in PatientDTO")
            Objects.requireNonNull(instance.gpId, "gpId must be set in PatientDTO")

            return instance
        }
    }
}