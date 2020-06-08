import junit.framework.Assert.assertEquals
import org.junit.Test

class MovieTicketPurchaseTest {
    private var purchase = MovieTicketPurchase()
    private var receipt: Double = 0.0

    @Test
    fun `Given 0 Tickets`() {
        receipt = purchase.receipt()

        assertEquals(0.0, receipt)
    }

    @Test
    fun `Given 1 General Ticket`() {
        purchase.addTicket()

        receipt = purchase.receipt()

        assertEquals(11.0, receipt)
    }

    @Test
    fun `Given 1 Student Ticket`() {
        purchase.addTicket(13, true)

        receipt = purchase.receipt()

        assertEquals(8.0, receipt)
    }

    @Test
    fun `Given 1 Senior Ticket`() {
        purchase.addTicket(65, false)

        receipt = purchase.receipt()

        assertEquals(6.0, receipt)
    }

    @Test
    fun `Given 1 Child Ticket`() {
        purchase.addTicket(12, true)

        receipt = purchase.receipt()

        assertEquals(5.50, receipt)
    }

    @Test
    fun `Given Child Group`() {
        repeat(20) {
            purchase.addTicket(12, true)
        }

        receipt = purchase.receipt()

        assertEquals(20 * 5.50, receipt)
    }

    @Test
    fun `Given Adult Group`() {
        repeat(20) {
            purchase.addTicket()
        }

        receipt = purchase.receipt()

        assertEquals(20 * 6.00, receipt)
    }

    @Test
    fun `Given 3D Movie`() {
        purchase = MovieTicketPurchase(is3d = true)
        purchase.addTicket()

        receipt = purchase.receipt()

        assertEquals(11.00 + 3.00, receipt)
    }

    @Test
    fun `Given Long Movie`() {
        purchase = MovieTicketPurchase(movieDurationMinutes = 121)
        purchase.addTicket()

        receipt = purchase.receipt()

        assertEquals(11.00 + 1.50, receipt)
    }

    @Test
    fun `Given Special Day`() {
        purchase = MovieTicketPurchase(dayOfWeek = DayOfWeek.THURSDAY)
        purchase.addTicket()

        receipt = purchase.receipt()

        assertEquals(11.00 - 2.00, receipt)
    }

    @Test
    fun `Given Weekend Day`() {
        purchase = MovieTicketPurchase(dayOfWeek = DayOfWeek.SATURDAY)
        purchase.addTicket()

        receipt = purchase.receipt()

        assertEquals(11.00 + 1.50, receipt)
    }

    @Test
    fun `Given Balcony Seating`() {
        purchase = MovieTicketPurchase(isBalconySeat = true)
        purchase.addTicket()

        receipt = purchase.receipt()

        assertEquals(11.00 + 2.00, receipt)
    }
}

