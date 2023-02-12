package service

import model.QuoteEvent
import model.Quotes
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.insertAndGetId

class QuoteService {
    fun handleQuoteUpdate(event: QuoteEvent) {
        transaction {
            Quotes.insertAndGetId {
                it[isin] = event.data.isin
                it[price] = event.data.price.toFloat()
                it[createdAt] = event.data.createdAt
            }
        }
    }
}