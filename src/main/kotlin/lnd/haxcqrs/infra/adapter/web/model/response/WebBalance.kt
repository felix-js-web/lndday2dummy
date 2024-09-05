package lnd.haxcqrs.infra.adapter.web.model.response

import jakarta.validation.constraints.*
import java.time.LocalDateTime

// I dont think I even need this object the balance from Domain is so much a
// match and just one on one copy of this is a bit overhead in this case
data class WebBalance(

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