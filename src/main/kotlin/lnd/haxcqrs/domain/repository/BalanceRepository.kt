package lnd.haxcqrs.domain.repository

import lnd.haxcqrs.domain.model.Balance

interface BalanceRepository {

    fun returnBalanceById(balanceId: String): Balance?

    fun saveBalance(balance: Balance): Balance

}