package com.mobile.micosecha.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mobile.micosecha.data.api.VariablesData
import com.mobile.micosecha.data.api.asMessage
import com.mobile.micosecha.databinding.FragmentBrightBinding
import com.mobile.micosecha.util.GraphResponse
import kotlinx.coroutines.*


class VarietyResumeFragment : Fragment() {

    private var _binding: FragmentBrightBinding? = null

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
                val lineSet: LinkedHashMap<String, Float> = graphResponse.lineSet
                val predictedLineSet: LinkedHashMap<String, Float> = graphResponse.predictedLineSet

                if (lineSet.size > 1){
                    binding.lineChartFirst.animate(graphResponse.lineSet)
                } else {
                    binding.lineChartFirst.visibility = GONE
                }
                if (predictedLineSet.size > 1){
                    var yieldPredicted: String = ""
                    for (predictedYield in predictedLineSet) {
                        yieldPredicted += "\n\t\t\t\t"+predictedYield.key +": "+predictedYield.value+" Ha"
                    }
                    binding.yieldPredictionTextView.text =
                        ("Producción estimada\n\t\tpara los siguientes "+predictedLineSet.size
                                +" años:\n" + yieldPredicted).also { "" }
                } else {
                    binding.yieldPredictionTextView.visibility = GONE
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
                binding.yieldTextView.text =
                    ("Producción: "+graphResponse.prod + " Ha")
                    .also { "Producción: NaN Ha" }
                binding.yieldSowingEstimation.text =
                    ("Se estima que la mejor fecha para la siembra se da en el "+graphResponse.yield_data[0] + " bisemestre del año ya que tiene el mayor promedio de producción con "+graphResponse.yield_data[1]+" Ha")
                    .also { "" }
            }
        }

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}