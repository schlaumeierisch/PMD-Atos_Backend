package nl.hva.backend.application.dto.value_objects

import nl.hva.backend.domain.value_objects.Address
import java.util.*

class AddressDTO {
    private lateinit var street: String
    private lateinit var zip: String
    private lateinit var city: String
    private lateinit var country: String

    companion object {
        fun fromAddress(address: Address): AddressDTO {
            val addressDTO = AddressDTO()

            addressDTO.street = address.street()
            addressDTO.zip = address.zip()
            addressDTO.city = address.city()
            addressDTO.country = address.country()

            return addressDTO
        }
    }

    // getter
    fun street(): String = this.street
    fun zip(): String = this.zip
    fun city(): String = this.city
    fun country(): String = this.country

    fun builder(): Builder {
        return Builder()
    }

    class Builder {
        private var instance: AddressDTO = AddressDTO()

        fun withStreet(street: String): Builder {
            instance.street = street
            return this
        }

        fun withZip(zip: String): Builder {
            instance.zip = zip
            return this
        }

        fun withCity(city: String): Builder {
            instance.city = city
            return this
        }

        fun withCountry(country: String): Builder {
            instance.country = country
            return this
        }

        fun build(): AddressDTO {
            Objects.requireNonNull(instance.street, "street must be set in AddressDTO")
            Objects.requireNonNull(instance.zip, "zip must be set in AddressDTO")
            Objects.requireNonNull(instance.city, "city must be set in AddressDTO")
            Objects.requireNonNull(instance.country, "country must be set in AddressDTO")

            return instance
        }
    }
}