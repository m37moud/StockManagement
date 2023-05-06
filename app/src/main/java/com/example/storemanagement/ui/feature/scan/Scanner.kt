package com.example.storemanagement.ui.feature.scan

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.util.Size
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.journeyapps.barcodescanner.CaptureManager
import com.journeyapps.barcodescanner.CompoundBarcodeView
import com.journeyapps.barcodescanner.DecoratedBarcodeView

@Composable
fun Scanner(navController: NavController) {
    var code by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = remember {
        ProcessCameraProvider.getInstance(context)
    }
    var hasCamPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            hasCamPermission = granted
        }
    )
    LaunchedEffect(key1 = true) {
        launcher.launch(Manifest.permission.CAMERA)
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if (hasCamPermission) {
//            AndroidView(
//                factory = { context ->
//                    val previewView = PreviewView(context)
//                    val preview = Preview.Builder().build()
//                    val selector = CameraSelector.Builder()
//                        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
//                        .build()
//                    preview.setSurfaceProvider(previewView.surfaceProvider)
//                    val imageAnalysis = ImageAnalysis.Builder()
//                        .setTargetResolution(
//                            Size(
//                                previewView.width,
//                                previewView.height
//                            )
//                        )
//                        .setBackpressureStrategy(STRATEGY_KEEP_ONLY_LATEST)
//                        .build()
//                    imageAnalysis.setAnalyzer(
//                        ContextCompat.getMainExecutor(context),
//                        QrCodeAnalyzer { result ->
//                            code = result
//                        }
//                    )
//                    try {
//                        cameraProviderFuture.get().bindToLifecycle(
//                            lifecycleOwner,
//                            selector,
//                            preview,
//                            imageAnalysis
//                        )
//                    } catch (e: Exception) {
//                        e.printStackTrace()
//                    }
//                    previewView
//                },
//                modifier = Modifier.weight(.25f)
//            )
//

            BarcodeScanner(modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(10.dp),
                onBarcodeScanned = {barCode->
                code = barCode
            }, onError = { err->
                code = err
            }
            )


            Text(
                text = code,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
            )
        }
    }
}

@Composable
fun BarcodeScanner(
    modifier: Modifier = Modifier,
    onBarcodeScanned: (String) -> Unit,
    onError: (String) -> Unit
) {
    var scanFlag by remember {
        mutableStateOf(false)
    }

    AndroidView(
        factory = { context ->
            CompoundBarcodeView(context).apply {

                val capture = CaptureManager(context as Activity, this)

                capture.initializeFromIntent(context.intent, null)
                this.setStatusText("")

                capture.decode()
                this.decodeContinuous { result ->
                    if (scanFlag) {
                        return@decodeContinuous
                    }
                    println("scanFlag true")
                    scanFlag = true
                    result.text?.let { barCodeOrQr ->
                        onBarcodeScanned(barCodeOrQr)
                        println("$barCodeOrQr")
                        //Do something and when you finish this something
                        //put scanFlag = false to scan another item
                        scanFlag = false
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
