package com.swapnesh.cgpoc.presentation.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swapnesh.cgpoc.data.model.DeleteResponse
import com.swapnesh.cgpoc.data.model.ProductResponse
import com.swapnesh.cgpoc.data.network.Status
import com.swapnesh.cgpoc.doamin.usecase.ProductListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductViewModel @Inject constructor(
    private val  productListUseCase: ProductListUseCase
) : ViewModel() {
    val productlistDataFlow = MutableSharedFlow<ProductResponse>()
    val productdelete = MutableSharedFlow<DeleteResponse>()

    private val _apiErrorToast = MutableLiveData<String>()
    val apiErrorToast: LiveData<String> = _apiErrorToast

    val _wating = MutableLiveData<Boolean>()
    val waitForServer: LiveData<Boolean> = _wating

    fun getProductLists() {
        viewModelScope.launch(Dispatchers.IO) {
            productListUseCase().collect { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        if (resource.data != null) {
                            resource.data.let {
                                productlistDataFlow.emit(it)
                                _wating.postValue(false)
                            }
                        } else {
                          //  _apiErrorToast.postValue(context.getString(R.string.something_went_wrong))
                            _wating.postValue(false)
                        }
                    }

                    Status.LOADING -> {
                        _wating.postValue(true)
                    }

                    Status.ERROR -> {
                        if (resource.code?.equals(401) == true) {
                          //  _apiErrorToast.postValue(context.getString(R.string.unauthorized_user))
                        }
                        _wating.postValue(false)
                    }
                }
            }
        }
    }

    fun deleteProduct(id: Int?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (id != null) {
                productListUseCase(id).collect { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            if (resource.data != null) {
                                resource.data.let {
                                    productdelete.emit(it)
                                    _wating.postValue(false)
                                }
                            } else {
                                //  _apiErrorToast.postValue(context.getString(R.string.something_went_wrong))
                                _wating.postValue(false)
                            }
                        }

                        Status.LOADING -> {
                            _wating.postValue(true)
                        }

                        Status.ERROR -> {
                            if (resource.code?.equals(401) == true) {
                                //  _apiErrorToast.postValue(context.getString(R.string.unauthorized_user))
                            }
                            _wating.postValue(false)
                        }
                    }
                }
            }
        }
    }




}