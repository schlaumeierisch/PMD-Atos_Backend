package nl.hva.backend.application

import nl.hva.backend.application.api.AccountService
import nl.hva.backend.domain.api.AccountRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest
class AccountServiceTests {

    @Autowired
    private lateinit var accountService: AccountService

    @MockBean
    private lateinit var accountRepository: AccountRepository


    @Test
    fun given_emptyRepository_when_getAllGeneralPractitioners_then_returnsEmpty() {
        // when
        Mockito.`when`(this.accountRepository.getAllGeneralPractitioners()).thenReturn(emptyList())

        // then
        Assertions.assertNotEquals(null, this.accountService.getAllGeneralPractitioners())
        Assertions.assertEquals(0, this.accountService.getAllGeneralPractitioners().size)
    }

    @Test
    fun given_emptyRepository_when_getAllPatients_then_returnsEmpty() {
        // when
        Mockito.`when`(this.accountRepository.getAllPatients()).thenReturn(emptyList())

        // then
        Assertions.assertNotEquals(null, this.accountService.getAllPatients())
        Assertions.assertEquals(0, this.accountService.getAllPatients().size)
    }

    @Test
    fun given_emptyRepository_when_getAllCareProviders_then_returnsEmpty() {
        // when
        Mockito.`when`(this.accountRepository.getAllCareProviders()).thenReturn(emptyList())

        // then
        Assertions.assertNotEquals(null, this.accountService.getAllCareProviders())
        Assertions.assertEquals(0, this.accountService.getAllCareProviders().size)
    }

}