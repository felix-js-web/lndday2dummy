package lnd.haxcqrs.infra.adapter.web.exception

class WalletNotFoundException(
        exceptionString: String
) : RuntimeException(exceptionString)