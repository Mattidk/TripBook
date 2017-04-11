package dk.mathiaspedersen.tripbook.presentation.view

import dk.mathiaspedersen.tripbook.domain.entity.Trip

interface RecentView : BaseView {
    fun example(response: List<Trip>)
}