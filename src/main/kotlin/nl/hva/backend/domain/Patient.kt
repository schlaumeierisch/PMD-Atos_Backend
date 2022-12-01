package nl.hva.backend.domain

import nl.hva.backend.domain.ids.GeneralPractitionerId
import nl.hva.backend.domain.ids.MedicalRecordId
import nl.hva.backend.domain.ids.PatientId
import nl.hva.backend.domain.value_objects.Address
import nl.hva.backend.domain.value_objects.Gender
import java.time.LocalDate

open class Patient {
    private val id: Long = 0
    private lateinit var domainId: PatientId

    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var address: Address
    private lateinit var gender: Enum<Gender>
    private lateinit var birthDate: LocalDate
    private lateinit var phoneNumber: String
    private lateinit var email: String
    private var isUsingApp: Boolean = false

    // one-to-one
    private var medicalRecordDomainId: MedicalRecordId = MedicalRecordId("")

    // many-to-one
    private var gpDomainId: GeneralPractitionerId = GeneralPractitionerId("")

    // required by hibernate
    protected constructor()

    constructor(
        domainId: PatientId,
        firstName: String,
        lastName: String,
        address: Address,
        gender: Enum<Gender>,
        birthDate: LocalDate,
        phoneNumber: String,
        email: String,
        isUsingApp: Boolean,
        medicalRecordDomainId: MedicalRecordId,
        gpDomainId: GeneralPractitionerId
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
        this.medicalRecordDomainId = medicalRecordDomainId
        this.gpDomainId = gpDomainId
    }

    // getter
    fun id(): Long = this.id
    fun domainId(): PatientId = this.domainId
    fun firstName(): String = this.firstName
    fun lastName(): String = this.lastName
    fun address(): Address = this.address
    fun gender(): Enum<Gender> = this.gender
    fun birthDate(): LocalDate = this.birthDate
    fun phoneNumber(): String = this.phoneNumber
    fun email(): String = this.email
    fun isUsingApp(): Boolean = this.isUsingApp
    fun medicalRecordDomainId(): MedicalRecordId = this.medicalRecordDomainId
    fun gpDomainId(): GeneralPractitionerId = this.gpDomainId
}