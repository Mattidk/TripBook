package dk.mathiaspedersen.tripbook.domain

import android.os.Handler
import android.os.Looper
import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import org.greenrobot.eventbus.EventBus

class BusImpl : EventBus(), Bus {

    val mainThread = Handler(Looper.getMainLooper())

    override fun post(event: Any) {
        mainThread.post({ super.post(event) })
    }
}