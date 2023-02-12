package model

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

data class QuoteEvent(val data: Quote)
data class Quote(val isin: ISIN, val price: Price)

object Quotes: IntIdTable() {
    val isin = varchar("isin", 100)
    val price = float("price")
    val createdAt = datetime("created_at").default(LocalDateTime.now())
}


