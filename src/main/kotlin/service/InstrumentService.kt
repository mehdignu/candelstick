package service

import model.Instrument
import model.InstrumentEvent
import model.Instruments
import model.Quotes
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class InstrumentService {
    fun handleInstrumentUpdate(event: InstrumentEvent) {
        if (event.type == InstrumentEvent.Type.ADD) {
            create(event.data)
        }

        if (event.type == InstrumentEvent.Type.DELETE) {
            remove(event.data)
        }
    }

    private fun create(instrument: Instrument) {
        transaction {
            addLogger(StdOutSqlLogger)
            Instruments.insert {
                it[isin] = instrument.isin
                it[description] = instrument.description
            }
        }
    }

    private fun remove(instrument: Instrument) {
        transaction {
            addLogger(StdOutSqlLogger)
            Quotes.deleteWhere { Quotes.isin eq instrument.isin }
            Instruments.deleteWhere { Instruments.isin eq instrument.isin }
        }
    }
}