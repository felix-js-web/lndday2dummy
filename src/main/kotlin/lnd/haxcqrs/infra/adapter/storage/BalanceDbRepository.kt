package lnd.haxcqrs.infra.adapter.storage

import lnd.haxcqrs.domain.model.Balance
import lnd.haxcqrs.domain.repository.BalanceRepository
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Repository

@Repository
@Profile("default")
class BalanceDbRepository : BalanceRepository {

    override fun returnBalanceById(balanceId: String): Balance? {
//        return null
        return Balance(balanceId, "1", 100, 1, 100)
    }

    override fun saveBalance(balance: Balance): Balance {
        return balance
    }

}