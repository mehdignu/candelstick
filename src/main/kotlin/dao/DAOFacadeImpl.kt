package dao

import model.Candlestick
import model.Quotes
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.ResultSet

class DAOFacadeImpl : DAOFacade {

    override fun getCandlesticks(isin: String): List<Candlestick> {
        return getLast30MinutesCandleSticks(isin)
    }

    override fun getLast30MinutesCandleSticks(isin: String):List<Candlestick> {
        var candleSticks = listOf<Candlestick>()
        transaction {
            val query = """
                            select min(q.created_at) as openTimestamp, max(q.created_at) as closeTimestamp
                                 , min(q.id) as openPrice, max(q.id) as closePrice
                                 , min(q.price) as lowPrice, max(q.price) as highPrice
                            from quotes q where q.isin = '$isin' and q.created_at >= now() - interval 30 minute
                            group by hour(q.created_at), minute(q.created_at)
                        """
            TransactionManager.current().exec(query) { result ->
                while (result.next()) { candleSticks += (formatCandlestick(result)) }
            }
        }
        return candleSticks
    }

    private fun formatCandlestick(rs: ResultSet): Candlestick {
        return Candlestick(
            openTimestamp = rs.getTimestamp("openTimestamp").toLocalDateTime(),
            openPrice = Quotes.getById(rs.getInt("openPrice")).price,
            highPrice = rs.getDouble("highPrice"),
            lowPrice = rs.getDouble("lowPrice"),
            closingPrice = Quotes.getById(rs.getInt("closePrice")).price,
            closeTimestamp = rs.getTimestamp("closeTimestamp").toLocalDateTime()
        )
    }
}