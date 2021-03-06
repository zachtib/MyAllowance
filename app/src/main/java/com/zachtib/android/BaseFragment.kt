package com.zachtib.android

import android.os.Bundle
import android.view.*
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class BaseFragment(
    @LayoutRes private val layoutId: Int? = null,
    @MenuRes private val menuId: Int? = null
) : Fragment(), CoroutineScope {

    private val job = Job()
    override val coroutineContext = Dispatchers.Main + job

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutId?.let { inflater.inflate(it, container, false) }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        if (menuId != null) {
            inflater?.inflate(menuId, menu)
        }
    }

    protected fun <T> LiveData<T>.observeWith(observer: (T) -> Unit) {
        this.observe(this@BaseFragment, Observer { observer(it) })
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}