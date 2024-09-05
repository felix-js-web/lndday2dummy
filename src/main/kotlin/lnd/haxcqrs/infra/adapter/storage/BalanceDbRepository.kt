package lnd.haxcqrs.infra.adapter.storage

import lnd.haxcqrs.demo.User
import lnd.haxcqrs.demo.UserRepository
import lnd.haxcqrs.domain.model.Balance
import lnd.haxcqrs.domain.repository.BalanceRepository
import org.springframework.context.annotation.Profile
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository

@Repository
@Profile("default")
class BalanceDbRepository(private val jdbcTemplate: JdbcTemplate) : BalanceRepository {

    private val rowMapper = RowMapper<Balance> { rs, _ ->
        Balance(
                id = rs.getString("id") ?: "",
                transactionId = rs.getString("transactionId") ?: "",
                amount = rs.getLong("amount"),
                version = rs.getInt("version"),
                balance = rs.getLong("balance"),
                createdDate = rs.getTimestamp("createdDate").toLocalDateTime()
        )
    }

    override fun returnBalanceById(balanceId: String): Balance? {
        return try {
            jdbcTemplate.queryForObject(
                    "SELECT * FROM balance WHERE id = ? ORDER BY version DESC LIMIT 1",
                    rowMapper,
                    balanceId
            )
        } catch (e: EmptyResultDataAccessException) {
            null
        }
    }

    override fun saveBalance(balance: Balance): Balance {
        val sql = """
        INSERT INTO balance (id, transactionId, amount, version, balance, createdDate)
        VALUES (?, ?, ?, ?, ?, ?)
    """
        jdbcTemplate.update(sql,
                balance.id,
                balance.transactionId,
                balance.amount,
                balance.version,
                balance.balance,
                balance.createdDate
        )
        return balance
    }

}