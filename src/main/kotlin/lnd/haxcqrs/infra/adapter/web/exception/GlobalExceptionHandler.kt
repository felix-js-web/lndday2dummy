package lnd.haxcqrs.infra.adapter.web.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import lnd.haxcqrs.domain.exception.WalletDoesNotExistException
import lnd.haxcqrs.domain.exception.DuplicatedTransactionException
import lnd.haxcqrs.domain.exception.InsufficientFundsException
import lnd.haxcqrs.domain.model.Balance

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(WalletDoesNotExistException::class)
    fun handleWalletDoesNotExistException(ex: WalletDoesNotExistException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(DuplicatedTransactionException::class)
    fun handleDuplicatedTransactionException(ex: DuplicatedTransactionException): ResponseEntity<Balance> {
        return ResponseEntity(ex.duplicatedBalance, HttpStatus.ACCEPTED)
    }

    @ExceptionHandler(InsufficientFundsException::class)
    fun handleInsufficientFundsException(ex: InsufficientFundsException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<String> {
        if (ex.message != null && ex.message!!.contains("unique_transaction_id")) {
            return ResponseEntity("Transaction with the same id already exists", HttpStatus.CONFLICT)
        }
        return ResponseEntity("An unexpected error occurred: ${ex.message}", HttpStatus.INTERNAL_SERVER_ERROR)
    }
}