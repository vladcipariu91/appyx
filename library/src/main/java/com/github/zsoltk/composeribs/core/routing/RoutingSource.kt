package com.github.zsoltk.composeribs.core.routing

import kotlinx.coroutines.flow.StateFlow

interface RoutingSource<Key, State> {

    val all: StateFlow<List<RoutingElement<Key, State>>>

    val onScreen: StateFlow<List<RoutingElement<Key, State>>>

    val offScreen: StateFlow<List<RoutingElement<Key, State>>>

    val canHandleBackPress: StateFlow<Boolean>

    fun onBackPressed()

    fun onTransitionFinished(key: RoutingKey<Key>)

    /**
     * Bundle for future state restoration.
     * Result should be supported by [androidx.compose.runtime.saveable.SaverScope.canBeSaved].
     */
    fun saveInstanceState(savedStateMap: MutableMap<String, Any>) {}

    /**
     * @return [key] should be rendered on the screen based on its [State].
     */
    fun isOnScreen(key: RoutingKey<Key>): Boolean = true

}
