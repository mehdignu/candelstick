package service

import dao.DatabaseFactory
import model.Instrument
import model.InstrumentEvent
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import io.mockk.spyk
import io.mockk.verify
import io.mockk.every

internal class InstrumentServiceTest {

    @BeforeEach
    fun createDB(){
        DatabaseFactory.init()
    }

    @AfterEach
    fun cleanDB(){
        transaction {
            TransactionManager.current().exec("truncate table quotes")
            TransactionManager.current().exec("truncate table instruments")
        }
    }

    @Test
    fun createInstrumentTest() {
        val instrumentEvent = InstrumentEvent(
            type = InstrumentEvent.Type.ADD,
            data = Instrument(isin = "test", description = "description test" )
        )
        val mock = spyk<InstrumentService>(recordPrivateCalls = true)
        every { mock["create"](instrumentEvent.data) } returns Unit
        mock.handleInstrumentUpdate(instrumentEvent)
        verify(exactly = 1) { mock["create"](instrumentEvent.data)}
    }

    @Test
    fun removeInstrumentTest() {
        val instrumentEvent = InstrumentEvent(
            type = InstrumentEvent.Type.DELETE,
            data = Instrument(isin = "test", description = "description test" )
        )
        val mock = spyk<InstrumentService>(recordPrivateCalls = true)
        every { mock["remove"](instrumentEvent.data) } returns Unit
        mock.handleInstrumentUpdate(instrumentEvent)
        verify(exactly = 1) { mock["remove"](instrumentEvent.data)}
    }


}