package nl.hva.backend.domain

import nl.hva.backend.domain.ids.IntakeId
import nl.hva.backend.domain.ids.MedicationId
import nl.hva.backend.domain.value_objects.Unit
import java.sql.Time

open class Intake {
    private val id: Long = 0
    private lateinit var domainId: IntakeId

    private lateinit var time: Time
    private var amount: Int = 0
    private lateinit var unit: Enum<Unit>

    // many-to-one
    private var medicationDomainId: MedicationId = MedicationId("")

    // required by hibernate
    protected constructor()

    constructor(
        domainId: IntakeId,
        time: Time,
        amount: Int,
        medicationDomainId: MedicationId,
        unit: Enum<Unit>
    ) {
        this.domainId = domainId
        this.time = time
        this.amount = amount
        this.medicationDomainId = medicationDomainId
        this.unit = unit
    }

    // getter
    fun id(): Long = this.id
    fun domainId(): IntakeId = this.domainId
    fun time(): Time = this.time
    fun amount(): Int = this.amount
    fun unit(): Enum<Unit> = this.unit
    fun medicationDomainId(): MedicationId = this.medicationDomainId
}