import junit.framework.Assert.assertEquals
import org.junit.Test

class MovieTicketPurchaseAcceptanceTest {
    private var purchase = MovieTicketPurchase()
    private var receipt: Double = 0.0

    @Test
    fun `0 tickets`() {
        receipt = purchase.receipt()

        assertEquals(0.0, receipt)
    }

    @Test
    fun `4 x 35 year-olds, 2D, 90 minute duration, Tuesday, normal seating == $44`() {
        purchase = MovieTicketPurchase(
                is3d = false,
                movieDurationMinutes = 90,
                dayOfWeek = DayOfWeek.TUESDAY,
                isBalconySeat = false
        )

        repeat(4) {
            purchase.addTicket(viewerAge = 35)
        }

        receipt = purchase.receipt()

        assertEquals(44.00, receipt)
    }

    @Test
    fun `4 x 35 year-olds, 3D, 90 minute duration, Tuesday, normal seating == $56`() {
        purchase = MovieTicketPurchase(
                is3d = true,
                movieDurationMinutes = 90,
                dayOfWeek = DayOfWeek.TUESDAY,
                isBalconySeat = false
        )

        repeat(4) {
            purchase.addTicket(viewerAge = 35)
        }

        receipt = purchase.receipt()

        assertEquals(56.00, receipt)
    }

    @Test
    fun `21 x 35 year-old, 2D, 90 minute duration, Tuesday, normal seating == $126`() {
        purchase = MovieTicketPurchase(
                is3d = false,
                movieDurationMinutes = 90,
                dayOfWeek = DayOfWeek.TUESDAY,
                isBalconySeat = false
        )

        repeat(21) {
            purchase.addTicket(viewerAge = 35)
        }

        receipt = purchase.receipt()

        assertEquals(126.00, receipt)
    }

    @Test
    fun `4 x 35 year-olds, 3D, 90 minute duration, Tuesday, balcony seating == $64`() {
        purchase = MovieTicketPurchase(
                is3d = true,
                movieDurationMinutes = 90,
                dayOfWeek = DayOfWeek.TUESDAY,
                isBalconySeat = true
        )

        repeat(4) {
            purchase.addTicket(viewerAge = 35)
        }

        receipt = purchase.receipt()

        assertEquals(64.00, receipt)
    }

    @Test
    fun `4 x 35 year-olds, 3D, 90 minute duration, Thursday, balcony seating == $56`() {
        purchase = MovieTicketPurchase(
                is3d = true,
                movieDurationMinutes = 90,
                dayOfWeek = DayOfWeek.THURSDAY,
                isBalconySeat = true
        )

        repeat(4) {
            purchase.addTicket(viewerAge = 35)
        }

        receipt = purchase.receipt()

        assertEquals(56.00, receipt)
    }

    @Test
    fun `4 x 35 year-olds, 2D, 240 minute duration, Monday, normal seating == $50`() {
        purchase = MovieTicketPurchase(
                is3d = false,
                movieDurationMinutes = 240,
                dayOfWeek = DayOfWeek.MONDAY,
                isBalconySeat = false
        )

        repeat(4) {
            purchase.addTicket(viewerAge = 35)
        }

        receipt = purchase.receipt()

        assertEquals(50.00, receipt)
    }

    @Test
    fun `4 x 35 year-olds, 3D, 90 minute duration, Saturday, balcony seating == $70`() {
        purchase = MovieTicketPurchase(
                is3d = true,
                movieDurationMinutes = 90,
                dayOfWeek = DayOfWeek.SATURDAY,
                isBalconySeat = true
        )

        repeat(4) {
            purchase.addTicket(viewerAge = 35)
        }

        receipt = purchase.receipt()

        assertEquals(70.00, receipt)
    }

    @Test
    fun `4 x 9 year-olds, 2D, 90 minute duration, Monday, normal seating == $22`() {
        purchase = MovieTicketPurchase(
                is3d = false,
                movieDurationMinutes = 90,
                dayOfWeek = DayOfWeek.MONDAY,
                isBalconySeat = false
        )

        repeat(4) {
            purchase.addTicket(viewerAge = 9)
        }

        receipt = purchase.receipt()

        assertEquals(22.00, receipt)
    }

    @Test
    fun `4 x 67 year-olds, 2D, 90 minute duration, Monday, normal seating == $24`() {
        purchase = MovieTicketPurchase(
                is3d = false,
                movieDurationMinutes = 90,
                dayOfWeek = DayOfWeek.MONDAY,
                isBalconySeat = false
        )

        repeat(4) {
            purchase.addTicket(viewerAge = 67)
        }

        receipt = purchase.receipt()

        assertEquals(24.00, receipt)
    }

    @Test
    fun `4 x 14 year-old students, 2D, 90 minute duration, Monday, normal seating == $32`() {
        purchase = MovieTicketPurchase(
                is3d = false,
                movieDurationMinutes = 90,
                dayOfWeek = DayOfWeek.MONDAY,
                isBalconySeat = false
        )

        repeat(4) {
            purchase.addTicket(viewerAge = 14, viewerIsStudent = true)
        }

        receipt = purchase.receipt()

        assertEquals(32.00, receipt)
    }

    @Test
    fun `1 x each type, 2D, 90 minute duration, Monday, normal seating == $30_50`() {
        purchase = MovieTicketPurchase(
                is3d = false,
                movieDurationMinutes = 90,
                dayOfWeek = DayOfWeek.MONDAY,
                isBalconySeat = false
        )

        // One of each
        purchase.addTicket() // General
        purchase.addTicket(viewerAge = 14, viewerIsStudent = true) // Student
        purchase.addTicket(viewerAge = 65, viewerIsStudent = false) // Senior
        purchase.addTicket(viewerAge = 4, viewerIsStudent = false) // Child

        receipt = purchase.receipt()

        assertEquals(30.50, receipt)
    }

    @Test
    fun `21 x 9 year-olds, 2D, 90 minute duration, Monday, normal seating == $115_50`() {
        purchase = MovieTicketPurchase(
                is3d = false,
                movieDurationMinutes = 90,
                dayOfWeek = DayOfWeek.MONDAY,
                isBalconySeat = false
        )

        repeat(21) {
            purchase.addTicket(viewerAge = 9)
        }

        receipt = purchase.receipt()

        assertEquals(115.50, receipt)
    }

    @Test
    fun `21 x 35 year-old, 2D, 90 minute duration, Thursday, normal seating == $126`() {
        purchase = MovieTicketPurchase(
                is3d = false,
                movieDurationMinutes = 90,
                dayOfWeek = DayOfWeek.THURSDAY,
                isBalconySeat = false
        )

        repeat(21) {
            purchase.addTicket(viewerAge = 35)
        }

        receipt = purchase.receipt()

        assertEquals(126.00, receipt)
    }

    @Test
    fun `10 x 14 year-old students + 11 x 9 year-olds, 2D, 90 minute duration, Monday, normal seating == $120_50`() {
        purchase = MovieTicketPurchase(
                is3d = false,
                movieDurationMinutes = 90,
                dayOfWeek = DayOfWeek.MONDAY,
                isBalconySeat = false
        )

        repeat(10) {
            purchase.addTicket(viewerAge = 14, viewerIsStudent = true)
        }

        repeat(11) {
            purchase.addTicket(viewerAge = 9)
        }

        receipt = purchase.receipt()

        assertEquals(120.50, receipt)
    }

    @Test
    fun `7 x each type (Child, Student, Regular, Senior), 3D, 240 minute duration, Thursday, balcony seating == $346_50`() {
        purchase = MovieTicketPurchase(
                is3d = true,
                movieDurationMinutes = 240,
                dayOfWeek = DayOfWeek.THURSDAY,
                isBalconySeat = true
        )

        repeat(7) {
            purchase.addTicket(viewerAge = 4, viewerIsStudent = false) // Child
            purchase.addTicket(viewerAge = 14, viewerIsStudent = true) // Student
            purchase.addTicket() // Regular
            purchase.addTicket(viewerAge = 65, viewerIsStudent = false) // Senior
        }

        receipt = purchase.receipt()

        assertEquals(346.50,
                receipt)
    }
}

