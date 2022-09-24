package com.mobile.micosecha.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mobile.micosecha.data.api.VariablesData
import com.mobile.micosecha.data.api.asMessage
import com.mobile.micosecha.databinding.FragmentBrightBinding
import com.mobile.micosecha.util.GraphResponse
import io.ktor.util.*
import kotlinx.coroutines.*


class VarietyResumeFragment : Fragment() {

    private var _binding: FragmentBrightBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val animationDuration = 1000L

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentBrightBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.lineChartFirst.animation.duration = animationDuration

        var variety: String? = arguments?.getString("variety")
        if (variety == null) {
            variety = "F60"
        }

        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                val graphResponse: VariablesData = GraphResponse().graphResponse(variety).asMessage()
                binding.seedTextView.text = "Semilla " + variety.lowercase()
                                                .replaceFirstChar { it.uppercase() }
                val lineSet = graphResponse.lineSet
                if (lineSet.size > 1){
                    binding.lineChartFirst.animate(graphResponse.lineSet)
                }

                binding.minimalTemperatureTextView.text =
                    ("Temperatura mínima: "+graphResponse.min_temp + " °C")
                    .also { "Temperatura mínima: NaN" }
                binding.maximumTemperatureTextView.text =
                    ("Temperatura máxima: "+graphResponse.max_temp + " °C")
                    .also { "Temperatura máxima: NaN" }
                binding.relativeHumidityTextView.text =
                    ("Humedad relativa: "+graphResponse.rhum + " %")
                    .also { "Humedad relativa: NaN" }
                binding.sunBrightTextView.text =
                    ("Brillo del sol: "+graphResponse.sbright + " w.m-2")
                    .also { "Brillo del sol: NaN" }
                binding.precipitationTextView.text =
                    ("Precipitación: "+graphResponse.prec + " mm")
                    .also { "Precipitación: NaN" }
                binding.productionTextView.text =
                    ("Producción: "+graphResponse.prod + " Ha")
                    .also { "Producción: NaN Ha" }
            }
        }

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}