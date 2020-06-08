class MovieTicketPurchase(
        private val is3d: Boolean = false,
        private val movieDurationMinutes: Int = 120,
        private val dayOfWeek: DayOfWeek = DayOfWeek.MONDAY,
        private val isBalconySeat: Boolean = false) {

    private var receipt: MutableList<Double> = ArrayList()
    private var rateModification: Double = 0.0

    fun receipt(): Double {

        if (shouldApplyGroupDiscount()) {
            applyGroupDiscount()
        } else if (isSpecialMovieDay()) {
            applySpecialMovieDayDiscount()
        }

        return receipt.sum() + rateModification
    }

    fun addTicket(viewerAge: Int = 35, viewerIsStudent: Boolean = false) {
        addTicketPriceToReceipt(viewerAge, viewerIsStudent)
        addDiscountsAndPremiumsToReceipt()
    }

    private fun addDiscountsAndPremiumsToReceipt() {
        add3dPremiumIfNeeded()
        addMovieDurationPremiumIfNeeded()
        addWeekendPremiumIfNeeded()
        addBalconyPremiumIfNeeded()
    }

    private fun addTicketPriceToReceipt(age: Int, isStudent: Boolean) {
        when {
            shouldUseStudentPricing(age, isStudent) -> {
                receipt.add(8.0)
            }
            shouldUseSeniorPricing(age) -> {
                receipt.add(6.0)
            }
            shouldUseChildPricing(age) -> {
                receipt.add(5.50)
            }
            else -> {
                receipt.add(11.0)
            }
        }
    }

    private fun shouldApplyGroupDiscount() = receipt.size >= 20

    private fun applyGroupDiscount() {
        for ((index, value) in receipt.withIndex()) {
            if (value > 6.0) {
                receipt[index] = 6.0
            }
        }
    }

    private fun isSpecialMovieDay() = dayOfWeek == DayOfWeek.THURSDAY

    private fun applySpecialMovieDayDiscount() {
        rateModification -= (receipt.size * 2.00)
    }

    private fun add3dPremiumIfNeeded() {
        if (is3d) {
            rateModification += 3.0
        }
    }

    private fun addMovieDurationPremiumIfNeeded() {
        if (movieDurationMinutes > 120) {
            rateModification += 1.50
        }
    }

    private fun addWeekendPremiumIfNeeded() {
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
           rateModification += 1.50
        }
    }

    private fun addBalconyPremiumIfNeeded() {
        if (isBalconySeat) {
            rateModification += 2.00
        }
    }

    private fun shouldUseStudentPricing(age: Int, isStudent: Boolean) = age in 13..63 && isStudent

    private fun shouldUseSeniorPricing(age: Int) = age >= 65

    private fun shouldUseChildPricing(age: Int) = age < 13
}

enum class DayOfWeek {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
}
