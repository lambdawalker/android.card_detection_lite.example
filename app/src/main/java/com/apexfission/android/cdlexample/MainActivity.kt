package com.apexfission.android.cdlexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.apexfission.android.carddetectionlite.domain.ModelCatalog
import com.apexfission.android.carddetectionlite.tfmodel.cardClasses
import com.apexfission.android.carddetectionlite.tfmodel.classes
import com.apexfission.android.carddetectionlite.tfmodel.modelPath
import com.apexfission.android.carddetectionlite.ui.CardDetectorLite
import com.apexfission.android.cdlexample.ui.theme.CdlExampleTheme
import com.apexfission.android.permissionscompose.HandleCameraPermission


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val mainViewModel: MainViewModel by viewModels()

        setContent {
            CdlExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HandleCameraPermission(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        onBack = { finish() },
                        onNotNow = { finish() }
                    ) {
                        val isDetectionEnabled by mainViewModel.isDetectionEnabled.collectAsStateWithLifecycle()

                        CardDetectorLite(
                            modifier = Modifier
                                .padding(innerPadding),
                            modelPath = ModelCatalog.TfLite.modelPath,
                            classLabels = ModelCatalog.TfLite.classes,
                            cardClasses = ModelCatalog.TfLite.cardClasses,
                            useGpu = true,
                            scoreThreshold = 0.50f,
                            showBoundingBoxes = true,
                            showClassNames = true,
                            showFlashlightSwitch = true,
                            isDetectionEnabled = isDetectionEnabled,
                            onCardDetection = mainViewModel::onDetection
                        )
                    }
                }
            }
        }
    }
}

