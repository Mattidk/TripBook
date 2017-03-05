package dk.mathiaspedersen.tripbook.domain.interactor.base.firebase

import com.path.android.jobqueue.Job
import com.path.android.jobqueue.Params
import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus

class FirebaseInteractorWrapper(val interactor: FirebaseInteractor, priority: FirebaseInteractorPriority, val bus: Bus) :
        Job(Params(priority.value).requireNetwork()) {

    override fun onRun() {
        interactor.invoke()
    }

    override fun onAdded() = Unit
    override fun onCancel() = Unit
    override fun shouldReRunOnThrowable(throwable: Throwable?) = false
}