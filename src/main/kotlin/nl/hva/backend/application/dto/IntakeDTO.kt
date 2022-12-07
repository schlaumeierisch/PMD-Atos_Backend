package nl.hva.backend.application.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import nl.hva.backend.domain.Intake
import nl.hva.backend.domain.value_objects.Unit
import java.sql.Time

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class IntakeDTO {
    private lateinit var id: String
    private lateinit var time: Time
    private var amount: Int = 0
    private lateinit var unit: Enum<Unit>

    // many-to-one
    private lateinit var medicationId: String

    companion object {
        fun fromIntake(intake: Intake): IntakeDTO {
            val intakeDTO = IntakeDTO()

            intakeDTO.id = intake.domainId().id()
            intakeDTO.time = intake.time()
            intakeDTO.amount = intake.amount()
            intakeDTO.unit = intake.unit()

            intakeDTO.medicationId = intake.medicationDomainId().id()

            return intakeDTO
        }

        fun fromIntakes(intakes: List<Intake>): List<IntakeDTO> {
            val intakeDTOs: ArrayList<IntakeDTO> = arrayListOf()

            for (intake in intakes) {
                intakeDTOs.add(fromIntake(intake))
            }

            return intakeDTOs
        }
    }

    // getter
    fun id(): String = this.id
    fun time(): Time = this.time
    fun amount(): Int = this.amount
    fun unit(): Enum<Unit> = this.unit
    fun medicationId(): String = this.medicationId()
}