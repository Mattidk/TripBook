package dk.mathiaspedersen.tripbook.domain.interactor.base.standard

import com.path.android.jobqueue.Job
import com.path.android.jobqueue.Params
import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus

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