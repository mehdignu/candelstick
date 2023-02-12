package model

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.javatime.datetime
import org.jetbrains.exposed.sql.select
import java.time.LocalDateTime

data class QuoteEvent(val data: Quote)
data class Quote(val id: Int? = null, val isin: ISIN, val price: Price, val createdAt: LocalDateTime = LocalDateTime.now())

object Quotes: IntIdTable() {
    val isin = varchar("isin", 100)
    val price = float("price")
    val createdAt = datetime("created_at").default(LocalDateTime.now())

    private fun toQuote(row: ResultRow): Quote =
        Quote(
            id = row[Quotes.id].value,
            isin = row[Quotes.isin],
            price = row[Quotes.price].toDouble(),
            createdAt = row[Quotes.createdAt]
        )
    fun getById(id: Int?): Quote {
        return select{ Quotes.id eq id }.map {
                r -> Quotes.toQuote(r)
        }.get(0)
    }
}



