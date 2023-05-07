package com.example.flowdemo2

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MyViewModel: ViewModel() {
    val myFlow = flow<Int> {
        for (i in 1..100){
            emit(i)
            delay(1000L)
        }
    }
    init {
        backPressureDemo()
    }

    private fun backPressureDemo(){
        val myFlow1 = flow<Int> {
            for (i in 1..100){
                Log.i("MYTAG","Produced $i")
                emit(i)
                delay(1000L)
            }
        }
        viewModelScope.launch {
            myFlow1
                .map {
                    it -> showMessage(it)
                }
                .collect {
                Log.i("MYTAG","Consumed $it")

            }
        }
    }

    fun showMessage(count:Int):String{
        return "Hello $count"
    }


}