package lnd.haxcqrs.domain.model

import jakarta.validation.constraints.*
import java.time.LocalDateTime

data class Balance(

        @field:NotNull
        @field:Size(min = 1, max = 50)
        @field:NotBlank
        val id: String,

        @field:NotNull
        @field:Size(min = 1, max = 50)
        @field:NotBlank
        val transactionId: String,

        @field:NotNull
        val amount: Long,

        @field:NotNull
        @field:Positive
        val version: Int,

        @field:NotNull
        @field:PositiveOrZero
        val balance: Long,

        @field:NotNull
        val createdDate: LocalDateTime = LocalDateTime.now()
)