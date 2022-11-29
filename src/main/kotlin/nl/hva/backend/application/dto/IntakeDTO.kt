package nl.hva.backend.application.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import nl.hva.backend.domain.Intake
import nl.hva.backend.domain.value_objects.Unit
import java.sql.Time
import java.util.*

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class IntakeDTO {
    private lateinit var id: String
    private lateinit var time: Time
    private var amount: Int = 0
    private lateinit var unit: Enum<Unit>

    // many-to-one
    private var medicationId: String = ""

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
    }

    // getter
    fun id(): String = this.id
    fun time(): Time = this.time
    fun amount(): Int = this.amount
    fun unit(): Enum<Unit> = this.unit
    fun medicationid(): String = this.medicationid()

    fun builder(): Builder {
        return Builder()
    }

    class Builder {
        private var instance: IntakeDTO = IntakeDTO()

        fun withId(id: String): Builder {
            instance.id = id
            return this
        }

        fun withTime(time: Time): Builder {
            instance.time = time
            return this
        }

        fun withAmount(amount: Int): Builder {
            instance.amount = amount
            return this
        }

        fun withUnit(unit: Enum<Unit>): Builder {
            instance.unit = unit
            return this
        }

        fun withMedicationId(medicationId: String): Builder {
            instance.medicationId = medicationId
            return this
        }

        fun build(): IntakeDTO {
            Objects.requireNonNull(instance.time, "time must be set in IntakeDTO")
            Objects.requireNonNull(instance.amount, "amount must be set in IntakeDTO")
            Objects.requireNonNull(instance.unit, "unit must be set in IntakeDTO")
            Objects.requireNonNull(instance.medicationId, "medicationId must be set in IntakeDTO")

            return instance
        }
    }
}