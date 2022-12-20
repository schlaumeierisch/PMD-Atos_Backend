package nl.hva.backend.application.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import nl.hva.backend.application.dto.value_objects.AddressDTO
import nl.hva.backend.domain.GeneralPractitioner
import java.time.LocalTime

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class GeneralPractitionerDTO {
    private lateinit var id: String
    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var addressDTO: AddressDTO
    private lateinit var phoneNumber: String
    private lateinit var startTimeShift: LocalTime
    private lateinit var endTimeShift: LocalTime
    // Need multiple epoch/time of day, because ofcourse, the gp/careprovider won't have only one break.
    // And it'd be annoying to have different columns for different times of their breaks.
    // And a list of LocalTime is sadly not possible in SQL.
    private lateinit var breakTimes: String
    // Getting the input as minutes. Primitive types cannot be lateinit and delegates are not supported by hibernate.
    private var breakDuration: Long = 0L
    private var appointmentDuration: Long = 0L

    companion object {
        fun fromGeneralPractitioner(generalPractitioner: GeneralPractitioner): GeneralPractitionerDTO {
            val generalPractitionerDTO = GeneralPractitionerDTO()

            generalPractitionerDTO.id = generalPractitioner.domainId().id()
            generalPractitionerDTO.firstName = generalPractitioner.firstName()
            generalPractitionerDTO.lastName = generalPractitioner.lastName()
            generalPractitionerDTO.addressDTO = AddressDTO.fromAddress(generalPractitioner.address())
            generalPractitionerDTO.phoneNumber = generalPractitioner.phoneNumber()
            generalPractitionerDTO.startTimeShift = generalPractitioner.startTimeShift()
            generalPractitionerDTO.endTimeShift = generalPractitioner.endTimeShift()
            generalPractitionerDTO.breakTimes = generalPractitioner.breakTimes()
            generalPractitionerDTO.breakDuration = generalPractitioner.breakDuration()
            generalPractitionerDTO.appointmentDuration = generalPractitioner.appointmentDuration()

            return generalPractitionerDTO
        }

        fun fromGeneralPractitioners(generalPractitioners: List<GeneralPractitioner>): List<GeneralPractitionerDTO> {
            val generalPractitionerDTOs: ArrayList<GeneralPractitionerDTO> = arrayListOf()

            for (generalPractitioner in generalPractitioners) {
                generalPractitionerDTOs.add(fromGeneralPractitioner(generalPractitioner))
            }

            return generalPractitionerDTOs
        }
    }

    // getter
    fun id(): String = this.id
    fun firstName(): String = this.firstName
    fun lastName(): String = this.lastName
    fun address(): AddressDTO = this.addressDTO
    fun phoneNumber(): String = this.phoneNumber
    fun startTimeShift(): LocalTime = this.startTimeShift
    fun endTimeShift(): LocalTime = this.endTimeShift
    fun breakTimes(): String = this.breakTimes
    fun breakDuration(): Long = this.breakDuration
    fun appointmentDuration(): Long = this.appointmentDuration
}