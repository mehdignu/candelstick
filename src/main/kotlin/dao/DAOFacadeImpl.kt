package dao

import model.Candlestick


class DAOFacadeImpl : DAOFacade {
    override suspend fun getCandlesticks(isin: String): List<Candlestick> {
        TODO("Not yet implemented")
    }

    override suspend fun getLast30MinutesCandleSticks(isin: String): List<Candlestick> {
        TODO("Not yet implemented")
    }

}