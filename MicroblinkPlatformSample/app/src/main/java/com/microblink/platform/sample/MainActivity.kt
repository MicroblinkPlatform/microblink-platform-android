package com.microblink.platform.sample

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.microblink.platform.MicroblinkPlatform
import com.microblink.platform.MicroblinkPlatformConfig
import com.microblink.platform.MicroblinkPlatformConsent
import com.microblink.platform.MicroblinkPlatformResult
import com.microblink.platform.MicroblinkPlatformResultListener
import com.microblink.platform.MicroblinkPlatformServiceSettings
import com.microblink.platform.MicroblinkPlatformUiSettings
import com.microblink.platform.sample.ui.theme.MicroblinkPlatformTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MicroblinkPlatformTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(
                            onClick = {
                                startIdentityVerification()
                            }) {
                            Text(text = "Start Identity Verification")
                        }
                    }
                }
            }
        }
    }

    fun startIdentityVerification() {
        // IMPORTANT: Replace the following values with your own
        val workflowId = "your_workflow_id"
        val hostUrl = "your_host_url"
        val userId = "your_user_id"

        MicroblinkPlatform.startVerification(
            activity = this@MainActivity, MicroblinkPlatformConfig(
                mbpResultListener = object : MicroblinkPlatformResultListener {
                    override fun onVerificationFinished(result: MicroblinkPlatformResult) {
                        Toast.makeText(
                            this@MainActivity, "Verification " + when (result.state) {
                                MicroblinkPlatformResult.FinishedState.Accept -> "successful"
                                MicroblinkPlatformResult.FinishedState.Reject -> "failed"
                                MicroblinkPlatformResult.FinishedState.Review -> "requires manual review"
                            }, Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun onVerificationCanceled() {
                        Toast.makeText(this@MainActivity, "Verification canceled", Toast.LENGTH_SHORT).show()
                    }

                },
                mbpServiceSettings = MicroblinkPlatformServiceSettings(
                    workflowId = workflowId,
                    hostUrl = hostUrl,
                    // add your additional request headers if needed, e.g. for authorization
                    // additionalRequestHeaders = mapOf("Authorization" to "Bearer your_token"),
                    consent = MicroblinkPlatformConsent(
                        userId = userId,
                        isTrainingAllowed = true,
                        isProcessingStoringAllowed = true
                    )
                ),
                mbpUiSettings = MicroblinkPlatformUiSettings(
                    // customize the UI if needed
                ),
            )
        )
    }
}


