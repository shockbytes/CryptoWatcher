package at.shockbytes.coins.ui.fragment

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.View
import android.widget.Toast
import at.shockbytes.coins.core.CryptoWatcherApp
import at.shockbytes.coins.dagger.AppComponent
import butterknife.ButterKnife
import butterknife.Unbinder

/**
 * @author Martin Macheiner
 * Date: 29.11.2017.
 */
abstract class BaseFragment : Fragment() {

    private var unbinder: Unbinder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectToGraph((activity.application as CryptoWatcherApp).appComponent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        unbinder = ButterKnife.bind(this, view)
        setupViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unbinder?.unbind()
    }

    protected abstract fun setupViews()

    protected abstract fun injectToGraph(appComponent: AppComponent)

    @JvmOverloads
    protected fun showSnackbar(text: String, actionText: String,
                               showIndefinite: Boolean = false,  action: () -> Unit) {
        if (view != null) {
            val duration = if (showIndefinite) Snackbar.LENGTH_INDEFINITE else Snackbar.LENGTH_LONG
            Snackbar.make(view!!, text, duration)
                    .setAction(actionText) {
                        action()
                    }.show()
        }
    }

    @JvmOverloads
    protected fun showSnackbar(text: String, showLong: Boolean = true) {
        if (view != null) {
            val duration = if (showLong) Snackbar.LENGTH_LONG else Snackbar.LENGTH_SHORT
            Snackbar.make(view!!, text, duration).show()
        }
    }

    @JvmOverloads
    protected fun showToast(text: String, showLong: Boolean = true) {
        val duration = if (showLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
        Toast.makeText(context, text, duration).show()
    }

}