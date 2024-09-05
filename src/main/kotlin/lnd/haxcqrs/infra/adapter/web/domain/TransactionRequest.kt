package lnd.haxcqrs.infra.adapter.web.domain

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class TransactionRequest(

        @field:NotNull
        @field:Size(min = 1, max = 50)
        @field:NotBlank
        val transactionId: String,

        @field:NotNull
        val amount: Long
)
