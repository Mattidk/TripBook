package dk.mathiaspedersen.tripbook.domain.interactor.base.standard

import com.path.android.jobqueue.JobManager
import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus

class InteractorExecutorImpl(val jobManager: JobManager, val bus: Bus) : InteractorExecutor {

    override fun execute(interactor: Interactor, priority: InteractorPriority) {
        jobManager.addJob(InteractorWrapper(interactor, priority,  bus))
    }
}