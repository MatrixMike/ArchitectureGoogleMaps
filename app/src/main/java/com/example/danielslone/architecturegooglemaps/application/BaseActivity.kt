package com.example.danielslone.architecturegooglemaps.application

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.danielslone.architecturegooglemaps.application.injection.component.ActivityComponent
import com.example.danielslone.architecturegooglemaps.application.injection.component.DaggerActivityComponent
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

/**
 * Created by danielslone on 2/23/18.
 */
abstract class BaseActivity : AppCompatActivity() {

    protected abstract val presenter: Presenter

    // region Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(ArchitectureGoogleMapApplication.applicationComponent)
                .build()

        inject(activityComponent)
    }

    protected abstract fun inject(activityComponent: ActivityComponent)

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        presenter.onStart()
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    override fun onPause() {
        super.onPause()

        presenter.onPause()
    }

    override fun onResume() {
        super.onResume()

        presenter.onResume()
    }

    override fun onStop() {
        super.onStop()

        presenter.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.onStop()
    }
    // endregion

    // region UI Interactions
    protected fun showKeyboard(view: View) {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }

    protected fun dismissKeyboard() {
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

        currentFocus?.let { inputMethodManager.hideSoftInputFromWindow(currentFocus.windowToken, 0) }
    }
    // endregion
}