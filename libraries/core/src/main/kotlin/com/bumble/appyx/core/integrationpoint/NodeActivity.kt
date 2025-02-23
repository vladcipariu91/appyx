package com.bumble.appyx.core.integrationpoint

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Helper class for root [Node] integration into projects using [AppCompatActivity].
 *
 * See [NodeComponentActivity] for building upon [ComponentActivity].
 *
 * Also offers base functionality to satisfy dependencies of Android-related functionality
 * down the tree via [appyxV1IntegrationPoint]:
 * - [ActivityStarter]
 * - [PermissionRequester]
 *
 * Feel free to not extend this and use your own integration point - in this case,
 * don't forget to take a look here what methods needs to be forwarded to the root Node.
 */
open class NodeActivity : AppCompatActivity(), IntegrationPointProvider {

    override lateinit var appyxV1IntegrationPoint: ActivityIntegrationPoint
        protected set

    protected open fun createIntegrationPoint(savedInstanceState: Bundle?) =
        ActivityIntegrationPoint(
            activity = this,
            savedInstanceState = savedInstanceState
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appyxV1IntegrationPoint = createIntegrationPoint(savedInstanceState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        appyxV1IntegrationPoint.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        appyxV1IntegrationPoint.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        appyxV1IntegrationPoint.onSaveInstanceState(outState)
    }

}
