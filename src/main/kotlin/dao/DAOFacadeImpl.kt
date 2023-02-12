package dao

import model.Candlestick


class DAOFacadeImpl : DAOFacade {
    override fun getCandlesticks(isin: String): List<Candlestick> {
        TODO("Not yet implemented")
    }

    override fun getLast30MinutesCandleSticks(isin: String): List<Candlestick> {
        TODO("Not yet implemented")
    }


}