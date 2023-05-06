package com.example.storemanagement.ui.feature.scanner
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.storemanagement.repository.ScannerRepoImpl
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.asStateFlow
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//
//@HiltViewModel
//class ScannerViewModel @Inject constructor(
//    private val repo: ScannerRepoImpl
//): ViewModel() {
//
//    private val _state = MutableStateFlow(ScannerScreenState())
//    val state = _state.asStateFlow()
//
//
//    fun startScanning(){
//        viewModelScope.launch {
//            repo.startScanning().collect{
//                if (!it.isNullOrBlank()){
//                    _state.value = state.value.copy(
//                        details = it
//                    )
//                }
//            }
//        }
//    }
//}