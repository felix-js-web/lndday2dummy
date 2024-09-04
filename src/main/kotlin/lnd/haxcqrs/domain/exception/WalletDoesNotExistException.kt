package lnd.haxcqrs.domain.exception

class WalletDoesNotExistException(
        exceptionString: String
) : RuntimeException(exceptionString)
