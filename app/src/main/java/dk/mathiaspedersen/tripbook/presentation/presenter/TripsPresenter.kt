package dk.mathiaspedersen.tripbook.presentation.presenter

import android.animation.TypeEvaluator
import android.animation.ValueAnimator
import android.content.Context
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.domain.entity.Trip
import dk.mathiaspedersen.tripbook.domain.interactor.GetTrips
import dk.mathiaspedersen.tripbook.domain.interactor.base.DefaultObserver
import dk.mathiaspedersen.tripbook.domain.interactor.base.Params
import dk.mathiaspedersen.tripbook.presentation.entity.TripDetail
import dk.mathiaspedersen.tripbook.presentation.entity.TripStackDetal
import dk.mathiaspedersen.tripbook.presentation.entity.mapper.TripDetailDataMapper
import dk.mathiaspedersen.tripbook.presentation.view.TripsView
import java.util.*

class TripsPresenter(override val view: TripsView, val context: Context, val getTrips: GetTrips,
                     val tripStack: Deque<TripStackDetal>, val mapper: TripDetailDataMapper) : BasePresenter<TripsView> {

    fun calculateCombinedDistance(trips: ArrayList<TripDetail>): Double {
        return trips.sumBy { it.distance.toInt() }.toLong() * 0.000621371192
    }

    fun calculateCombinedValue(trips: ArrayList<TripDetail>): Double {
        return (trips.sumBy { it.distance.toInt() }.toLong() * 0.000621371192) * 0.54
    }

    fun pushTrip(trip: TripStackDetal) {
        tripStack.push(trip)
    }

    fun popTrip() {
        if (tripStack.size != 0) {
            view.popTrip(tripStack.pop())
        }
    }

    fun clearStack() {
        tripStack.clear()
    }

    fun runTripCountUpAnimation(trips: ArrayList<TripDetail>) {
        val animator = ValueAnimator()
        animator.setObjectValues(0, trips.size)
        animator.addUpdateListener({ view.setTripText(it.animatedValue.toString()) })
        animator.setEvaluator(object : TypeEvaluator<Int> {
            override fun evaluate(fraction: Float, startValue: Int, endValue: Int): Int {
                return Math.round(startValue + (endValue - startValue) * fraction)
            }
        })
        animator.duration = 1000
        animator.start()
    }

    fun runMilesCountUpAnimation(trips: ArrayList<TripDetail>) {
        val miles = calculateCombinedDistance(trips)
        val animator = ValueAnimator()
        animator.setObjectValues(0, Math.round(miles))
        animator.addUpdateListener({ view.setMilesText(it.animatedValue.toString()) })
        animator.setEvaluator(object : TypeEvaluator<Int> {
            override fun evaluate(fraction: Float, startValue: Int, endValue: Int): Int {
                return Math.round(startValue + (endValue - startValue) * fraction)
            }
        })
        animator.duration = 1000
        animator.start()
    }

    fun runValueCountUpAnimation(trips: ArrayList<TripDetail>) {
        val value = calculateCombinedValue(trips)
        val animatorValue = ValueAnimator()
        animatorValue.setObjectValues(0, Math.round(value))
        animatorValue.addUpdateListener({
            view.setValueText(String.format(context.getString(R.string.toolbar_value), it.animatedValue))
        })
        animatorValue.setEvaluator(object : TypeEvaluator<Int> {
            override fun evaluate(fraction: Float, startValue: Int, endValue: Int): Int {
                return Math.round(startValue + (endValue - startValue) * fraction)
            }
        })
        animatorValue.duration = 1000
        animatorValue.start()
    }

    fun showTrips(trips: ArrayList<TripDetail>) {
        runTripCountUpAnimation(trips)
        runMilesCountUpAnimation(trips)
        runValueCountUpAnimation(trips)
        view.populateRecyclerView(trips)
    }

    fun resetCounters() {
        view.resetCounters(context.getString(R.string.toolbar_default_value))
    }

    fun getTrips() {
        getTrips.execute(GetTripsObserver(), Params.EMPTY)
    }

    override fun dispose() {
        getTrips.dispose()
    }

    inner class GetTripsObserver : DefaultObserver<List<Trip>>() {
        override fun onNext(t: List<Trip>) {
            super.onNext(t)
            showTrips(mapper.transform(t) as ArrayList<TripDetail>)
        }

        override fun onError(exception: Throwable) {
            super.onError(exception)
            view.unableToFetchTrips("Unable to fetch trips")
        }
    }
}