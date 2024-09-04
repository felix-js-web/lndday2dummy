package lnd.haxcqrs.domain.port.cqrs

import lnd.haxcqrs.domain.repository.BalanceRepository
import lnd.haxcqrs.domain.model.Balance
import io.github.oshai.kotlinlogging.KotlinLogging
import lnd.haxcqrs.domain.exception.WalletDoesNotExistException
import org.springframework.stereotype.Component

@Component
class BalanceQueryHandler(private val balanceRepository: BalanceRepository) {
    private val LOGGER = KotlinLogging.logger {};

    fun getBalanceById(balanceId: String): Balance? {
        LOGGER.info { "Initiating balance querying for wallet id ${balanceId}" }
        return balanceRepository.returnBalanceById(balanceId)
    }

}