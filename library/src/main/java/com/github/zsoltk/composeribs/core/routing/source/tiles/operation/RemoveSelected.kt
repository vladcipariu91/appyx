package com.github.zsoltk.composeribs.core.routing.source.tiles.operation

import com.github.zsoltk.composeribs.core.routing.RoutingElements
import com.github.zsoltk.composeribs.core.routing.source.tiles.Tiles
import com.github.zsoltk.composeribs.core.routing.source.tiles.TilesElements
import com.github.zsoltk.composeribs.core.routing.source.tiles.TilesOperation

class RemoveSelected<T : Any> : TilesOperation<T> {

    override fun isApplicable(elements: TilesElements<T>): Boolean = true

    override fun invoke(
        elements: TilesElements<T>
    ): RoutingElements<T, Tiles.TransitionState> =
        elements.map {
            if (it.targetState == Tiles.TransitionState.SELECTED) {
                it.copy(
                    targetState = Tiles.TransitionState.DESTROYED,
                    operation = this
                )
            } else {
                it
            }
        }

    override fun equals(other: Any?): Boolean = this.javaClass == other?.javaClass

    override fun hashCode(): Int = this.javaClass.hashCode()
}

fun <T : Any> Tiles<T>.removeSelected() {
    perform(RemoveSelected())
}
