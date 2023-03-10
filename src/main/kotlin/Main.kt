import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.slf4j.LoggerFactory
import dao.DatabaseFactory
import service.InstrumentService
import service.QuoteService

private val logger = LoggerFactory.getLogger("MainKt")

fun main() {
  logger.info("starting up")
  val instrumentService = InstrumentService()
  val quoteService = QuoteService()

  DatabaseFactory.init()

  val server = Server()
  val instrumentStream = InstrumentStream()
  val quoteStream = QuoteStream()


  instrumentStream.connect { event ->
    instrumentService.handleInstrumentUpdate(event)
    logger.info("Instrument: {}", event)
  }

  quoteStream.connect { event ->
    // TODO - implement
    quoteService.handleQuoteUpdate(event)
    logger.info("Quote: {}", event)
  }


  server.start()
}

val jackson: ObjectMapper =
  jacksonObjectMapper()
    .registerModule(JavaTimeModule())
    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
