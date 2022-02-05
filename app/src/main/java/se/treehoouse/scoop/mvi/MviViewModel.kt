package se.treehoouse.scoop.mvi

import androidx.lifecycle.*
import org.orbitmvi.orbit.ContainerHost
import timber.log.Timber

abstract class MviViewModel<S : Any, E : Any, A>(private val tag: String) : ContainerHost<S, E>,
    ViewModel() {

    fun postAction(action: A) {
        Timber.tag(tag).d("MVI Action --- $action")
        onAction(action)
    }

    protected abstract fun onAction(action: A)
}