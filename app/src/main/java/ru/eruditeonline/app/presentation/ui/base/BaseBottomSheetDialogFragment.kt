package ru.eruditeonline.app.presentation.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class BaseBottomSheetDialogFragment(@LayoutRes private val layoutRes: Int) : BottomSheetDialogFragment() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutRes, container, false)
    }

    protected infix fun <T> LiveData<T>.observe(block: (T) -> Unit) {
        observe(this@BaseBottomSheetDialogFragment.viewLifecycleOwner) { t -> block.invoke(t) }
    }
}
