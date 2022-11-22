package nl.hva.backend.domain

import nl.hva.backend.domain.ids.GeneralPractitionerId
import nl.hva.backend.domain.value_objects.Address

open class GeneralPractitioner {
    private val id: Long = 0
    private lateinit var domainId: GeneralPractitionerId

    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var address: Address
    private lateinit var phoneNumber: String

    private lateinit var patients: MutableSet<Patient>

    // required by hibernate
    protected constructor()

    constructor(
        generalPractitionerId: GeneralPractitionerId,
        firstName: String,
        lastName: String,
        address: Address,
        phoneNumber: String,
        patients: MutableSet<Patient>
    ) {
        this.domainId = generalPractitionerId
        this.firstName = firstName
        this.lastName = lastName
        this.address = address
        this.phoneNumber = phoneNumber
        this.patients = patients
    }

    // getter
    fun id(): Long = this.id
    fun domainId(): GeneralPractitionerId = this.domainId
    fun firstName(): String = this.firstName
    fun lastName(): String = this.lastName
    fun address(): Address = this.address
    fun phoneNumber(): String = this.phoneNumber
    fun patients(): MutableSet<Patient> = this.patients
}