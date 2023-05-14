package com.example.storemanagement.ui.feature.master.categories

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storemanagement.data.database.entity.CategoryEntity
import com.example.storemanagement.repository.Repository
import com.example.storemanagement.util.Lce
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(val repository: Repository) : ViewModel() {
    //local database
    private val _ReadAllCategories: MutableStateFlow<Lce<List<CategoryEntity>>> =
        MutableStateFlow(Lce.Loading)
    val readAllCategories: StateFlow<Lce<List<CategoryEntity>>> =
        _ReadAllCategories.asStateFlow()

    init {
        getAllCategories()
    }

    fun getAllCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            _ReadAllCategories.value = Lce.Loading
            repository.local.readAllCategories()
                .collect() { content ->

                    if (content.isNotEmpty()) {
                        _ReadAllCategories.value = Lce.Content(content)
                    } else {
                        _ReadAllCategories.value = Lce.Error("No Category is found")
                    }
                }


        }
    }

    fun deleteCategory(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.deleteCategory(id)
        }

    }
}