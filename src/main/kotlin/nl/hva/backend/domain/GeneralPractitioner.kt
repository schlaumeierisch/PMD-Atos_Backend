package nl.hva.backend.domain

import nl.hva.backend.domain.ids.GeneralPractitionerId
import nl.hva.backend.domain.value_objects.Address
import java.time.LocalTime

open class GeneralPractitioner {
    private val id: Long = 0
    private lateinit var domainId: GeneralPractitionerId

    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var address: Address
    private lateinit var phoneNumber: String
    private lateinit var startTimeShift: LocalTime
    private lateinit var endTimeShift: LocalTime
    // Need multiple epoch/time of day, because maybe the gp/careprovider won't have only one break.
    // And it'd be annoying to have different columns for different times of their breaks.
    // And a list of LocalTime is sadly not possible in SQL.
    private lateinit var breakTimes: String
    // Getting the input as minutes. Primitive types cannot be lateinit and delegates are not supported by hibernate.
    private var breakDuration: Long = 0L
    private var appointmentDuration: Long = 0L
    private lateinit var email: String

    // required by hibernate
    protected constructor()

    constructor(
        generalPractitionerId: GeneralPractitionerId,
        firstName: String,
        lastName: String,
        address: Address,
        phoneNumber: String,
        startTimeShift: LocalTime,
        endTimeShift: LocalTime,
        breakTimes: String,
        breakDuration: Long,
        appointmentDuration: Long
        phoneNumber: String,
        email: String
    ) {
        this.domainId = generalPractitionerId
        this.firstName = firstName
        this.lastName = lastName
        this.address = address
        this.phoneNumber = phoneNumber
        this.email = email
        this.startTimeShift = startTimeShift
        this.endTimeShift = endTimeShift
        this.breakTimes = breakTimes
        this.breakDuration = breakDuration
        this.appointmentDuration = appointmentDuration
    }

    // getter
    fun id(): Long = this.id
    fun domainId(): GeneralPractitionerId = this.domainId
    fun firstName(): String = this.firstName
    fun lastName(): String = this.lastName
    fun address(): Address = this.address
    fun phoneNumber(): String = this.phoneNumber
    fun startTimeShift(): LocalTime = this.startTimeShift
    fun endTimeShift(): LocalTime = this.endTimeShift
    fun breakTimes(): String = this.breakTimes
    fun breakDuration(): Long = this.breakDuration
    fun appointmentDuration(): Long = this.appointmentDuration
    fun email(): String = this.email
}