package lnd.haxcqrs.domain.exception

import lnd.haxcqrs.domain.model.Balance

class DuplicatedTransactionException(
        message: String,
        val duplicatedBalance: Balance
) : RuntimeException(message)