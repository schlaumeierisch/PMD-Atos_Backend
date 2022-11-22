package nl.hva.backend.domain

import nl.hva.backend.domain.ids.PatientId
import nl.hva.backend.domain.value_objects.Address
import java.time.LocalDate

open class Patient {
    private val id: Long = 0
    private lateinit var domainId: PatientId

    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var address: Address
    private lateinit var gender: String
    private lateinit var birthDate: LocalDate
    private lateinit var phoneNumber: String
    private lateinit var email: String
    private var isUsingApp: Boolean = false

    private lateinit var contactPersons: Set<ContactPerson>
    private lateinit var careProviders: Set<CareProvider>
    private lateinit var generalPractitioner: GeneralPractitioner

    // required by hibernate
    protected constructor()

    constructor(
        domainId: PatientId,
        firstName: String,
        lastName: String,
        address: Address,
        gender: String,
        birthDate: LocalDate,
        phoneNumber: String,
        email: String,
        isUsingApp: Boolean,
        contactPersons: Set<ContactPerson>,
        careProviders: Set<CareProvider>,
        generalPractitioner: GeneralPractitioner
    ) {
        this.domainId = domainId
        this.firstName = firstName
        this.lastName = lastName
        this.address = address
        this.gender = gender
        this.birthDate = birthDate
        this.phoneNumber = phoneNumber
        this.email = email
        this.isUsingApp = isUsingApp
        this.contactPersons = contactPersons
        this.careProviders = careProviders
        this.generalPractitioner = generalPractitioner
    }

    // getter
    fun id(): Long = this.id
    fun domainId(): PatientId = this.domainId
    fun firstName(): String = this.firstName
    fun lastName(): String = this.lastName
    fun address(): Address = this.address
    fun gender(): String = this.gender
    fun birthDate(): LocalDate = this.birthDate
    fun phoneNumber(): String = this.phoneNumber
    fun email(): String = this.email
    fun isUsingApp(): Boolean = this.isUsingApp
    fun contactPersons(): Set<ContactPerson> = this.contactPersons
    fun careProviders(): Set<CareProvider> = this.careProviders
    fun generalPractitioner(): GeneralPractitioner = this.generalPractitioner
}