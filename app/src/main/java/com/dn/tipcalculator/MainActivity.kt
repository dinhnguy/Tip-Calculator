package com.dn.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dn.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

@Suppress("UNUSED_ANONYMOUS_PARAMETER")
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var total = -1.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { calculateTip() }
        binding.roundUpSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (total > 0) {
                calculateTip()
            }
        }


    }

    private fun calculateTip() {
        val amount = binding.serviceCost.text.toString().toDoubleOrNull()
        if (amount == null) {
            binding.total.text = ""
            binding.tipResult.text = "Tip amount"
        } else {
            val quality = binding.tipOptions.checkedRadioButtonId
            val percentage = when (quality) {
                R.id.excellent -> .20
                R.id.good -> .18
                else -> .15
            }
            val tip = amount * percentage
            total = amount + tip

            binding.tipResult.text = "Tip: " + NumberFormat.getCurrencyInstance().format(tip).toString()
            if (binding.roundUpSwitch.isChecked) {
                total = ceil(total)
            }


            binding.total.text = getString(R.string.total_amount, NumberFormat.getCurrencyInstance().format(total).toString())
        }
    }
}