package lnd.haxcqrs.domain.port.cqrs

import lnd.haxcqrs.domain.exception.InsufficientFundsException
import lnd.haxcqrs.domain.exception.WalletDoesNotExistException
import lnd.haxcqrs.domain.repository.BalanceRepository
import lnd.haxcqrs.domain.model.Balance
import io.github.oshai.kotlinlogging.KotlinLogging
import lnd.haxcqrs.domain.exception.DuplicatedTransactionException
import org.springframework.stereotype.Component

@Component
class BalanceCommandHandler(private val balanceRepository: BalanceRepository) {
    private val LOGGER = KotlinLogging.logger {};

    fun addTransactionAndReturnBalance(transaction: Transaction): Balance {
        LOGGER.info { "Initiating balance transactions for wallet id ${transaction.id} and transaction id ${transaction.transactionId} and amount ${transaction.amount}" }
        val getCurrentBalance = balanceRepository.returnBalanceById(transaction.id);
        return if (getCurrentBalance != null) {
            existingWalletValidationAndSaving(getCurrentBalance, transaction)
        } else {
            newWalletValidationAndCreation(transaction)
        }
    }

    private fun newWalletValidationAndCreation(transaction: Transaction): Balance {
        if (transaction.amount < 0) {
            LOGGER.error(String.format("You can not debit the nonexisting wallet please credit it first for wallet id %s and transaction id %s where transaction amount id %d", transaction.id, transaction.transactionId, transaction.amount))
            throw WalletDoesNotExistException(String.format("You can not debit the unexisting wallet please credit it first for wallet id %s and transaction id %s where transaction amount id %d", transaction.id, transaction.transactionId, transaction.amount))
        }
        return balanceRepository.saveBalance(Balance(transaction.id, transaction.transactionId, transaction.amount, 1, transaction.amount))
    }

    private fun existingWalletValidationAndSaving(getCurrentBalance: Balance, transaction: Transaction): Balance {
        if (getCurrentBalance.transactionId == transaction.transactionId) {
            LOGGER.error(String.format("Transaction id %s already exists for wallet id %s", transaction.transactionId, transaction.id))
            throw DuplicatedTransactionException(String.format("Transaction id %s already exists for wallet id %s", transaction.transactionId, transaction.id), getCurrentBalance)
        }
        if (getCurrentBalance.balance + transaction.amount < 0) {
            LOGGER.error(String.format("Balance cannot be negative for wallet id %s and transaction id %s where current amount is %d and transaction amount id %d", transaction.id, transaction.transactionId, getCurrentBalance.balance, transaction.amount))
            throw InsufficientFundsException(String.format("Balance cannot be negative for wallet id %s and transaction id %s where current amount is %d and transaction amount id %d", transaction.id, transaction.transactionId, getCurrentBalance.balance, transaction.amount))
        }
        return balanceRepository.saveBalance(Balance(transaction.id, transaction.transactionId, transaction.amount, getCurrentBalance.version + 1, getCurrentBalance.balance + transaction.amount))
    }

}