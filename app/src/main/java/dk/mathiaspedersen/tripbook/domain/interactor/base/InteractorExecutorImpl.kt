package dk.mathiaspedersen.tripbook.domain.interactor.base

import com.path.android.jobqueue.JobManager

class InteractorExecutorImpl(val jobManager: JobManager, val bus: Bus) : InteractorExecutor {

    override fun execute(interactor: Interactor, priority: InteractorPriority) {
        jobManager.addJob(InteractorWrapper(interactor, priority,  bus))
    }
}