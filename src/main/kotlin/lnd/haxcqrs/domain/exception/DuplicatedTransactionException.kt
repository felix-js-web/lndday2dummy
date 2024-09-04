package lnd.haxcqrs.domain.exception

class DuplicatedTransactionException(
        exceptionString: String
) : RuntimeException(exceptionString)