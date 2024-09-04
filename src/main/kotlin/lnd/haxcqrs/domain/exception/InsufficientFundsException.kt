package lnd.haxcqrs.domain.exception

class InsufficientFundsException(
        exceptionString: String
) : RuntimeException(exceptionString)
