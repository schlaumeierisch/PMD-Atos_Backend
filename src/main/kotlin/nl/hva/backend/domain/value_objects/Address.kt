package nl.hva.backend.domain.value_objects

open class Address {
    private lateinit var street: String
    private lateinit var zip: String
    private lateinit var city: String
    private lateinit var country: String

    // required by hibernate
    protected constructor()

    constructor(
        street: String,
        zip: String,
        city: String,
        country: String
    ) {
        this.street = street
        this.zip = zip
        this.city = city
        this.country = country
    }

    fun street(): String {
        return street
    }

    fun zip(): String {
        return zip
    }

    fun city(): String {
        return city
    }

    fun country(): String {
        return country
    }

}