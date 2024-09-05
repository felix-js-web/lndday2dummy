package lnd.haxcqrs.infra.adapter.web

import io.github.oshai.kotlinlogging.KotlinLogging
import lnd.haxcqrs.domain.exception.WalletDoesNotExistException
import lnd.haxcqrs.domain.port.cqrs.BalanceCommandHandler
import lnd.haxcqrs.domain.port.cqrs.BalanceQueryHandler
import lnd.haxcqrs.domain.port.cqrs.Transaction
import lnd.haxcqrs.infra.adapter.web.domain.TransactionRequest
import lnd.haxcqrs.infra.adapter.web.model.response.WebBalance
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/balances")
internal class BalanceController(private val balanceQueryHandler: BalanceQueryHandler,
                                 private val balanceCommandHandler: BalanceCommandHandler) {

    private val LOGGER = KotlinLogging.logger {};

    @GetMapping
    fun getAllUsers(): String {
        LOGGER.info { "Initiating balance transactions for wallet id" }
        return "Basic String Response"
    }

    @GetMapping("/{id}")
    fun getBalanceById(@PathVariable id: String): ResponseEntity<WebBalance> {
        LOGGER.info { " Initiating balance retrieval in web layer for wallet id $id " }
        val domainBalance = balanceQueryHandler.getBalanceById(id)
        if (domainBalance != null) {
            return ResponseEntity.ok(WebBalance(
                    id = domainBalance.id,
                    transactionId = domainBalance.transactionId,
                    amount = domainBalance.amount,
                    version = domainBalance.version,
                    balance = domainBalance.balance
            ))
        } else {
            throw WalletDoesNotExistException(String.format("You can not retrieve the balance for nonexistent wallet id %s", id))
        }
    }

    @PostMapping("/{id}/credit")
    fun creditBalanceById(
            @RequestBody transaction: TransactionRequest,
            @PathVariable id: String): ResponseEntity<WebBalance> {
        LOGGER.info { " Initiating balance crediting in web layer for wallet id $id " }
        val domainBalance = balanceCommandHandler.addTransactionAndReturnBalance(Transaction(id, transaction.transactionId, transaction.amount))
        return ResponseEntity.status(HttpStatus.CREATED).body(WebBalance(
                id = domainBalance.id,
                transactionId = domainBalance.transactionId,
                amount = domainBalance.amount,
                version = domainBalance.version,
                balance = domainBalance.balance
        ))
    }

    @PostMapping("/{id}/debit")
    fun debitBalanceById(
            @RequestBody transaction: TransactionRequest,
            @PathVariable id: String): ResponseEntity<WebBalance> {
        LOGGER.info { " Initiating balance crediting in web layer for wallet id $id " }
        val domainBalance = balanceCommandHandler.addTransactionAndReturnBalance(Transaction(id, transaction.transactionId, -transaction.amount))
        return ResponseEntity.status(HttpStatus.CREATED).body(WebBalance(
                id = domainBalance.id,
                transactionId = domainBalance.transactionId,
                amount = domainBalance.amount,
                version = domainBalance.version,
                balance = domainBalance.balance
        ))
    }

}

