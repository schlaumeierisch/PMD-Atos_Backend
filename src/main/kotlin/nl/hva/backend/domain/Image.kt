package nl.hva.backend.domain

import nl.hva.backend.domain.ids.PatientId

open class Image {
    private val id: Long = 0
    // one-to-one
    private var patientId: PatientId = PatientId("")

    private lateinit var name: String
    private lateinit var type: String
    private lateinit var image: ByteArray


    // required by hibernate
    protected constructor()

    constructor(
        patientId: PatientId,
        name: String,
        type: String,
        image: ByteArray,
    ) {
        this.patientId = patientId
        this.name = name
        this.type = type
        this.image = image
    }

    // getter
    fun id(): Long = this.id
    fun patientId(): PatientId = this.patientId
    fun name(): String = this.name
    fun type(): String = this.type
    fun image(): ByteArray = this.image
}