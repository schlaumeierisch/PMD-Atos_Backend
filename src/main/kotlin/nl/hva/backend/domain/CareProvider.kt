package nl.hva.backend.domain

import nl.hva.backend.domain.ids.CareProviderId
import nl.hva.backend.domain.value_objects.Address
import nl.hva.backend.domain.value_objects.Specialism

open class CareProvider {
    private val id: Long = 0
    private lateinit var domainId: CareProviderId

    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var address: Address
    private lateinit var phoneNumber: String
    private lateinit var email: String
    private lateinit var specialism: Enum<Specialism>

    // required by hibernate
    protected constructor()

    constructor(
        generalPractitionerId: CareProviderId,
        firstName: String,
        lastName: String,
        address: Address,
        phoneNumber: String,
        email: String,
        specialism: Enum<Specialism>
    ) {
        this.domainId = generalPractitionerId
        this.firstName = firstName
        this.lastName = lastName
        this.address = address
        this.phoneNumber = phoneNumber
        this.email = email
        this.specialism = specialism
    }

    // getter
    fun id(): Long = this.id
    fun domainId(): CareProviderId = this.domainId
    fun firstName(): String = this.firstName
    fun lastName(): String = this.lastName
    fun address(): Address = this.address
    fun phoneNumber(): String = this.phoneNumber
    fun email(): String = this.email
    fun specialism(): Enum<Specialism> = this.specialism
}


