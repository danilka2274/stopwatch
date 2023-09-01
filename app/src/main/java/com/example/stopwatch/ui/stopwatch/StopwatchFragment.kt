package com.example.stopwatch.ui.stopwatch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.stopwatch.R
import com.example.stopwatch.di.app
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class StopwatchFragment : Fragment() {

    private lateinit var viewModel: StopwatchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_stopwatch, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, app.stopwatchViewModelFactory)
            .get(StopwatchViewModel::class.java)

        setListeners()
        observeTime()
    }

    private fun setListeners() {
        view?.findViewById<Button>(R.id.button_start)?.setOnClickListener {
            viewModel.onStartClicked()
        }
        view?.findViewById<Button>(R.id.button_pause)?.setOnClickListener {
            viewModel.onPauseClicked()
        }
        view?.findViewById<Button>(R.id.button_stop)?.setOnClickListener {
            viewModel.onStopClicked()
        }
    }

    private fun observeTime() {
        val textView = view?.findViewById<TextView>(R.id.text_time)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.ticker.collect {
                    textView?.text = it
                }
            }
        }
    }

}