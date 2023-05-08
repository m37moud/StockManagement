package com.example.storemanagement.ui.component

import android.app.Activity
import android.content.Context
import android.util.Size
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.example.storemanagement.ui.feature.scan.QrCodeAnalyzer
import com.journeyapps.barcodescanner.CaptureManager
import com.journeyapps.barcodescanner.CompoundBarcodeView


@Composable
fun BarcodeScanner(
    modifier: Modifier = Modifier,
    scanFlag:MutableState<Boolean>,
    onBarcodeScanned: (String) -> Unit,
    onError: (String) -> Unit
) {
//    var scanFlag = remember {
//        mutableStateOf(false)
//    }

    AndroidView(
        factory = { context ->
            CompoundBarcodeView(context).apply {
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
                        onBarcodeScanned(barCodeOrQr)
                        println("$barCodeOrQr")
                        //Do something and when you finish this something
                        //put scanFlag = false to scan another item
                        scanFlag.value = false
                    }
                    //If you don't put this scanFlag = false, it will never work again.
                    //you can put a delay over 2 seconds and then scanFlag = false to prevent multiple scanning
                }
                this.resume()
            }
        },
        modifier = modifier
    )
}





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
fun BarcodeScannerOriginal(context:Context){
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
