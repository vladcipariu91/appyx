package com.bumble.appyx.core.navigation.transition

import androidx.compose.runtime.Composable
import androidx.compose.runtime.movableContentOf
import com.bumble.appyx.core.node.LocalNodeTargetVisibility
import com.bumble.appyx.core.node.LocalParentNodeMovableContent


/**
 * Returns movable content for the given key. To reuse composable across Nodes movable content
 * must to be invoked only once at any time, therefore we should return null for if the Node is
 * transitioning to an invisible target and return movable content only if the targetState is visible.
 *
 * Example: ParentNode (P) has BackStack with one Active Node (A). We push Node (B) to the BackStack,
 * and we want to move content from Node (A) to Node (B). Node (A) is transitioning from Active to
 * Stashed (invisible) state and Node (B) is transitioning from Created to Active (visible) state.
 * When this transition starts this function will return null for Node (A) and movable content for
 * Node (B)so that this content will be moved from Node (A) to Node (B).
 *
 * If you have a custom NavModel keep in mind that you can only move content from a visible Node
 * that becomes invisible to a Node that is becoming visible.
 */
@Composable
fun localMovableContentWithTargetVisibility(
    key: Any,
    defaultValue: @Composable () -> Unit
): (@Composable () -> Unit)? {
    if (!LocalNodeTargetVisibility.current) return null
    val movableContentMap = LocalParentNodeMovableContent.current
    return movableContentMap.getOrPut(key) {
        movableContentOf {
            defaultValue()
        }
    }
}

