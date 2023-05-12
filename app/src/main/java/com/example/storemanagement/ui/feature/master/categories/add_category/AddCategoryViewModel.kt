package com.example.storemanagement.ui.feature.master.categories.add_category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storemanagement.data.database.entity.CategoryEntity
import com.example.storemanagement.repository.Repository
import com.example.storemanagement.util.Lce
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCategoryViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private var _message = MutableStateFlow<String>("")
    val message: StateFlow<String> = _message

    fun insertCategory(categoryEntity: CategoryEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (categoryEntity.categoryName.isNotBlank()) {

                    val isCategory =
                        repository.local.getCategoryByName(categoryName = categoryEntity.categoryName)

                    if (isCategory == null) {
                        repository.local.insertCategory(categoryEntity)
                        _message.value = "Insert Successful"

                    } else
                        _message.value = "this category already added"
                } else
                    _message.value = "please enter category name"

            } catch (exc: Exception) {
                _message.value = "error ${exc.stackTraceToString()}"

            }
        }
    }

}