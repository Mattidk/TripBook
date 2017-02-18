package dk.mathiaspedersen.tripbook.domain.interactor.standard

import com.path.android.jobqueue.JobManager
import dk.mathiaspedersen.tripbook.domain.interactor.event.bus.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.standard.InteractorPriority
import dk.mathiaspedersen.tripbook.domain.interactor.standard.InteractorWrapper
import dk.mathiaspedersen.tripbook.domain.interactor.standard.Interactor
import dk.mathiaspedersen.tripbook.domain.interactor.standard.InteractorExecutor

class InteractorExecutorImpl(val jobManager: JobManager, val bus: Bus) : InteractorExecutor {

    override fun execute(interactor: Interactor, priority: InteractorPriority) {
        jobManager.addJob(InteractorWrapper(interactor, priority,  bus))
    }
}