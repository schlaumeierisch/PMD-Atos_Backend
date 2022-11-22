package nl.hva.backend.application.dto

import nl.hva.backend.domain.ContactPerson
import java.util.*

class ContactPersonDTO {
    private lateinit var id: String
    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var phoneNumber: String

    private lateinit var patientDTO: PatientDTO

    companion object {
        fun fromcareProvider(contactPerson: ContactPerson): ContactPersonDTO {
            val contactPersonDTO = ContactPersonDTO()

            contactPersonDTO.id = contactPerson.id().toString()
            contactPersonDTO.firstName = contactPerson.firstName()
            contactPersonDTO.lastName = contactPerson.lastName()
            contactPersonDTO.phoneNumber = contactPerson.phoneNumber()
            contactPersonDTO.patientDTO = PatientDTO.fromPatient(contactPerson.patient())

            return contactPersonDTO
        }
    }

    // getter
    fun id(): String = this.id
    fun firstName(): String = this.firstName
    fun lastName(): String = this.lastName
    fun phoneNumber(): String = this.phoneNumber
    fun patientDTO(): PatientDTO = this.patientDTO

    fun builder(): Builder {
        return Builder()
    }

    class Builder {
        private var instance: ContactPersonDTO = ContactPersonDTO()

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

        fun withPhoneNumber(phoneNumber: String): Builder {
            instance.phoneNumber = phoneNumber
            return this
        }

        fun withPatientDTO(patientDTO: PatientDTO): Builder {
            instance.patientDTO = patientDTO
            return this
        }

        fun build(): ContactPersonDTO {
            Objects.requireNonNull(instance.id, "id must be set in CareProviderDTO")
            Objects.requireNonNull(instance.firstName, "firstName must be set in CareProviderDTO")
            Objects.requireNonNull(instance.lastName, "lastName must be set in CareProviderDTO")
            Objects.requireNonNull(instance.phoneNumber, "phoneNumber must be set in CareProviderDTO")
            Objects.requireNonNull(instance.patientDTO, "patientDTO must be set in CareProviderDTO")

            return instance
        }
    }
}