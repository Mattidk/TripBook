package dk.mathiaspedersen.tripbook.domain.interactor.standard

import com.path.android.jobqueue.JobManager
import dk.mathiaspedersen.tripbook.domain.interactor.event.bus.Bus

class JobInteractorExecutorImpl(val jobManager: JobManager, val bus: Bus) : JobInteractorExecutor {

    override fun execute(interactor: JobInteractor, priority: JobInteractorPriority) {
        jobManager.addJob(JobInteractorWrapper(interactor, priority,  bus))
    }
}