package dk.mathiaspedersen.tripbook.domain.interactor.base.firebase

import com.path.android.jobqueue.JobManager
import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus

class FirebaseInteractorExecutorImpl(val jobManager: JobManager, val bus: Bus) : FirebaseInteractorExecutor {

    override fun execute(interactor: FirebaseInteractor, priority: FirebaseInteractorPriority) {
        jobManager.addJob(FirebaseInteractorWrapper(interactor, priority,  bus))
    }
}