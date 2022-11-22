package nl.hva.backend.domain

import nl.hva.backend.domain.ids.ContactPersonId

open class ContactPerson {
    private val id: Long = 0
    private lateinit var domainId: ContactPersonId

    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var phoneNumber: String

    private lateinit var patient: Patient

    // required by hibernate
    protected constructor()

    constructor(
        domainId: ContactPersonId,
        firstName: String,
        lastName: String,
        phoneNumber: String,
        patient: Patient
    ) {
        this.domainId = domainId
        this.firstName = firstName
        this.lastName = lastName
        this.phoneNumber = phoneNumber
        this.patient = patient
    }

    // getter
    fun id(): Long = this.id
    fun domainId(): ContactPersonId = this.domainId
    fun firstName(): String = this.firstName
    fun lastName(): String = this.lastName
    fun phoneNumber(): String = this.phoneNumber
    fun patient(): Patient = this.patient
}