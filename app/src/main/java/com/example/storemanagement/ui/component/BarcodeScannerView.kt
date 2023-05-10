package com.example.storemanagement.ui.component

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Size
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.example.storemanagement.R
import com.example.storemanagement.ui.feature.scan.QrCodeAnalyzer
import com.google.zxing.ResultPoint
import com.google.zxing.client.android.BeepManager
import com.journeyapps.barcodescanner.*


@Composable
fun BarcodeScanner(
    modifier: Modifier = Modifier,
    scanFlag: MutableState<Boolean>,
    onBarcodeScanned: (String) -> Unit,
    onError: (String) -> Unit
) {
//    var scanFlag = remember {
//        mutableStateOf(false)
//    }
    val context = LocalContext.current
    var isFlash by remember {
        mutableStateOf(
            context.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)
        )
    }

    val compoundBarcodeView = remember {
        CompoundBarcodeView(context)
    }
    Box(modifier = modifier) {


        AndroidView(
            factory = {
                compoundBarcodeView.apply {
                    val beepManager = BeepManager(context as Activity)
                    val capture = CaptureManager(context as Activity, this)
                    capture.initializeFromIntent(context.intent, null)
                    this.setStatusText("")

                    capture.decode()
                    this.decodeContinuous { result ->
                        if (scanFlag.value) {
                            return@decodeContinuous
                        }
                        println("scanFlag true")
//                    scanFlag.value = true
                        result.text?.let { barCodeOrQr ->
                            beepManager.playBeepSound()

                            onBarcodeScanned(barCodeOrQr)
                            println("$barCodeOrQr")
                            //Do something and when you finish this something
                            //put scanFlag = false to scan another item
                            scanFlag.value = false
                        }
                        //If you don't put this scanFlag = false, it will never work again.
                        //you can put a delay over 2 seconds and then scanFlag = false to prevent multiple scanning
                    }
//                    this.resume()
                }
            },
            modifier = Modifier.matchParentSize()
        )
        Box(modifier = modifier.align(Alignment.TopStart)) {
            if (isFlash) {
                Button(onClick = {
                    compoundBarcodeView.setTorchOn()

                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_flash_on),
                        contentDescription = null
                    )
                }
            } else {
                Button(onClick = {
                    compoundBarcodeView.setTorchOff()

                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_flash_off),
                        contentDescription = null
                    )
                }
            }
        }
    }
    DisposableEffect(key1 = "someKey" ){
        compoundBarcodeView.resume()
        onDispose {
            compoundBarcodeView.pause()
        }
    }
}

@Composable
fun BarcodeScannerView(
//    barcodeDetector: DecoratedBarcode,
    onBarcodeScanned: (BarcodeResult) -> Unit
) {
//    var barcodeView: BarcodeView? = null
    AndroidView(
        factory = { context ->
            DecoratedBarcodeView(context).apply {
//                barcodeView = this
//                this.
//                this.decoderFactory = DefaultDecoderFactory(BarcodeFormat.)
                initializeFromIntent(Intent())
                decodeContinuous(object : BarcodeCallback {
                    override fun barcodeResult(result: BarcodeResult?) {
                        result?.let { onBarcodeScanned(it) }
                    }

                    override fun possibleResultPoints(resultPoints: MutableList<ResultPoint>?) {}
                })
            }
        }
    )
}



/*
   <com.journeyapps.barcodescanner.ViewfinderView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/zxing_viewfinder_view"
        app:zxing_possible_result_points="@color/zxing_custom_possible_result_points"
        app:zxing_result_view="@color/zxing_custom_result_view"
        app:zxing_viewfinder_laser="@color/zxing_custom_viewfinder_laser"
        app:zxing_viewfinder_laser_visibility="true"
        app:zxing_viewfinder_mask="@color/zxing_custom_viewfinder_mask"/>
 */


//@Composable
//fun ViewFinder(modifier: Modifier = Modifier){
//AndroidView(factory = {context ->
//    ViewfinderView(context).apply {
//
//    }
//
//} , modifier = modifier)
//}
@Composable
fun BarcodeScanner3(
    modifier: Modifier = Modifier,
    onBarcodeScanned: (String) -> Unit,
    onError: (String) -> Unit
) {
    val context = LocalContext.current
    val scannerView = remember {
        CodeScannerView(context)
    }
    var codeScanner: CodeScanner? by remember {
        mutableStateOf(null)
    }

    AndroidView(
        modifier = modifier,
        factory = { scannerView },
        update = {
            codeScanner = CodeScanner(context, scannerView).apply {
                camera = CodeScanner.CAMERA_BACK
                formats = CodeScanner.ALL_FORMATS
                autoFocusMode = AutoFocusMode.SAFE
                scanMode = ScanMode.SINGLE
                isAutoFocusEnabled = true
                isFlashEnabled = false


                decodeCallback = DecodeCallback {
                    onBarcodeScanned(it.text)
                }
                errorCallback = ErrorCallback {
                    onError(it.message.toString())
                }
                startPreview()
            }
        },
//        disposeContent = {
//            codeScanner?.releaseResources()
//        }
    )
}


@Composable
fun BarcodeScannerOriginal(context: Context) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = remember {
        ProcessCameraProvider.getInstance(context)
    }
    AndroidView(
        factory = { context ->
            val previewView = PreviewView(context)
            val preview = Preview.Builder().build()
            val selector = CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build()
            preview.setSurfaceProvider(previewView.surfaceProvider)
            val imageAnalysis = ImageAnalysis.Builder()
                .setTargetResolution(
                    Size(
                        previewView.width,
                        previewView.height
                    )
                )
                .setBackpressureStrategy(STRATEGY_KEEP_ONLY_LATEST)
                .build()
            imageAnalysis.setAnalyzer(
                ContextCompat.getMainExecutor(context),
                QrCodeAnalyzer { result ->
//                            code = result
                }
            )
            try {
                cameraProviderFuture.get().bindToLifecycle(
                    lifecycleOwner,
                    selector,
                    preview,
                    imageAnalysis
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
            previewView
        },
//                modifier = Modifier.weight(.25f)
    )
}
