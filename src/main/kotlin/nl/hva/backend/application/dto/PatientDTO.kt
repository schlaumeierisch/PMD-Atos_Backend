package nl.hva.backend.application.dto

import nl.hva.backend.application.dto.value_objects.AddressDTO
import nl.hva.backend.domain.Patient
import java.time.LocalDate
import java.util.*

class PatientDTO {
    private lateinit var id: String
    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var address: AddressDTO
    private lateinit var gender: String
    private lateinit var birthDate: LocalDate
    private lateinit var phoneNumber: String
    private lateinit var email: String
    private var isUsingApp: Boolean = false

    private lateinit var contactPersonDTOs: MutableSet<ContactPersonDTO>
    private lateinit var careProviderDTOs: MutableSet<CareProviderDTO>
    private lateinit var generalPractitionerDTO: GeneralPractitionerDTO

    companion object {
        fun fromPatient(patient: Patient): PatientDTO {
            val patientDTO = PatientDTO()

            patientDTO.firstName = patient.firstName()
            patientDTO.lastName = patient.lastName()
            patientDTO.address = AddressDTO.fromAddress(patient.address())
            patientDTO.gender = patient.gender()
            patientDTO.birthDate = patient.birthDate()
            patientDTO.phoneNumber = patient.phoneNumber()
            patientDTO.email = patient.email()
            patientDTO.isUsingApp = patient.isUsingApp()
            patientDTO.generalPractitionerDTO = GeneralPractitionerDTO.fromGeneralPractitioner(patient.generalPractitioner())

            patientDTO.careProviderDTOs = mutableSetOf()
            for (careProvider in patient.careProviders()) {
                val careProviderDTO: CareProviderDTO = CareProviderDTO().builder()
                    .withId(careProvider.domainId().id())
                    .withFirstName(careProvider.firstName())
                    .withLastName(careProvider.lastName())
                    .withAddress(AddressDTO.fromAddress(careProvider.address()))
                    .withPhoneNumber(careProvider.phoneNumber())
                    .withSpecialism(careProvider.specialism())
                    .build()

                patientDTO.careProviderDTOs.add(careProviderDTO)
            }

            patientDTO.contactPersonDTOs = mutableSetOf()
            for (contactPerson in patient.contactPersons()) {
                val contactPersonDTO: ContactPersonDTO = ContactPersonDTO().builder()
                    .withId(contactPerson.domainId().id())
                    .withFirstName(contactPerson.firstName())
                    .withLastName(contactPerson.lastName())
                    .withPhoneNumber(contactPerson.phoneNumber())
                    .withPatientDTO(patientDTO)
                    .build()

                patientDTO.contactPersonDTOs.add(contactPersonDTO)
            }

            return patientDTO
        }
    }

    // getter
    fun id(): String = this.id
    fun firstName(): String = this.firstName
    fun lastName(): String = this.lastName
    fun address(): AddressDTO = this.address
    fun gender(): String = this.gender
    fun birthDate(): LocalDate = this.birthDate
    fun phoneNumber(): String = this.phoneNumber
    fun email(): String = this.email
    fun isUsingApp(): Boolean = this.isUsingApp
    fun contactPersonDTOs(): Set<ContactPersonDTO> = this.contactPersonDTOs
    fun careProviderDTOs(): Set<CareProviderDTO> = this.careProviderDTOs
    fun generalPractitionerDTO(): GeneralPractitionerDTO = this.generalPractitionerDTO

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

        fun withAddress(address: AddressDTO): Builder {
            instance.address = address
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

        fun withGeneralPractitionerDTO(generalPractitionerDTO: GeneralPractitionerDTO): Builder {
            instance.generalPractitionerDTO = generalPractitionerDTO
            return this
        }

        fun build(): PatientDTO {
            Objects.requireNonNull(instance.firstName, "street must be set in PatientDTO")
            Objects.requireNonNull(instance.lastName, "lastName must be set in PatientDTO")
            Objects.requireNonNull(instance.gender, "gender must be set in PatientDTO")
            Objects.requireNonNull(instance.birthDate, "birthDate must be set in PatientDTO")
            Objects.requireNonNull(instance.phoneNumber, "phoneNumber must be set in PatientDTO")
            Objects.requireNonNull(instance.email, "email must be set in PatientDTO")
            Objects.requireNonNull(instance.contactPersonDTOs, "contactPersonDTOs must be set in PatientDTO")
            Objects.requireNonNull(instance.careProviderDTOs, "careProviderDTOs must be set in PatientDTO")
            Objects.requireNonNull(instance.generalPractitionerDTO, "generalPractitionerDTO must be set in PatientDTO")

            return instance
        }
    }
}