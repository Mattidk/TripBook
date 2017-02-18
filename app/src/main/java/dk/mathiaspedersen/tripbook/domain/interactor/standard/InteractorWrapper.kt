package dk.mathiaspedersen.tripbook.domain.interactor.standard

import com.path.android.jobqueue.Job
import com.path.android.jobqueue.Params
import dk.mathiaspedersen.tripbook.domain.interactor.event.bus.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.standard.Interactor
import dk.mathiaspedersen.tripbook.domain.interactor.standard.InteractorPriority

class InteractorWrapper(val interactor: Interactor, priority: InteractorPriority, val bus: Bus) :
        Job(Params(priority.value).requireNetwork()) {

    override fun onRun() {
        val event = interactor.invoke()
        bus.post(event)
    }

    override fun onAdded() = Unit
    override fun onCancel() = Unit
    override fun shouldReRunOnThrowable(throwable: Throwable?) = false
}