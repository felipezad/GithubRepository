package com.example.lookatxing.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.RequestManager
import javax.inject.Inject

abstract class BaseActivity<VM : ViewModel, VB : ViewBinding> : AppCompatActivity() {

    @Inject
    protected lateinit var requestManagerGlide: RequestManager

    protected abstract val mViewModel: VM

    protected lateinit var mViewBinding: VB

    abstract fun getViewBinding(): VB

    abstract fun setupViewModel()

    abstract fun setupObservers()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = getViewBinding()
        setContentView(mViewBinding.root)
        setupObservers()
        setupViewModel()
    }
}