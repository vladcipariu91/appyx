package com.bumble.appyx.core.node

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf

val LocalNode = compositionLocalOf<Node?> { null }

@OptIn(ExperimentalSharedTransitionApi::class)
val LocalSharedElementScope = compositionLocalOf<SharedTransitionScope?> { null }

/**
 * Represents the target visibility of this Node. For instance, if the underlying
 * NavModel is a BackStack and the NavKey's target state is BackStack.ACTIVE this will return true
 * as they ACTIVE state is visible on the screen. In the target state is STASHED or DESTROYED it will
 * return false.
 */
val LocalNodeTargetVisibility = compositionLocalOf { false }

val LocalParentNodeMovableContent =
    compositionLocalOf<MutableMap<Any, @Composable () -> Unit>> { mutableMapOf() }
