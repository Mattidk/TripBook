package dk.mathiaspedersen.tripbook.domain.interactor.base

import android.os.Handler
import android.os.Looper
import org.greenrobot.eventbus.EventBus

class BusImpl : EventBus(), Bus {

    val mainThread = Handler(Looper.getMainLooper())

    override fun post(event: Any) {
        mainThread.post({ super.post(event) })
    }
}