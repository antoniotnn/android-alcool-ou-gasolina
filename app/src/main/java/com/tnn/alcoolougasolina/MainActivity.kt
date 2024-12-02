package com.tnn.alcoolougasolina

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var textInputAlcool: TextInputLayout
    private lateinit var editAlcool: TextInputEditText

    private lateinit var textInputGasolina: TextInputLayout
    private lateinit var editGasolina: TextInputEditText

    private lateinit var btnCalcular: Button
    private lateinit var textResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarComponentesInterface();
        btnCalcular.setOnClickListener() {
            calcularMelhorPreco();
        }
    }

    private fun calcularMelhorPreco() {
        var precoAlcool = editAlcool.text.toString();
        var precoGasolina = editGasolina.text.toString();

        val resultadoValidacao = validarCampos(precoAlcool, precoGasolina);
        if (resultadoValidacao) {
            /* Cálculo de melhor preço
            * Se (precoAlcool / precoGasolina) >= 0.7 é melhor abastecer com gasolina
            * Senão é melhor abastecer com álcool
             */
            val resultado = precoAlcool.toDouble() / precoGasolina.toDouble();

            if (resultado >= 0.7) {
                textResultado.text = "Melhor abastecer com Gasolina";
            } else {
                textResultado.text = "Melhor abastecer com Álcool";
            }
        }
    }

    private fun validarCampos(pAlcool: String, pGasolina: String): Boolean {
        textInputAlcool.error = null
        textInputGasolina.error = null

        if (pAlcool.isEmpty()) {
            textInputAlcool.error = "Digite o preço do Álcool"
            return false;
        } else if (pGasolina.isEmpty()) {
            textInputGasolina.error = "Digite o preço da Gasolina"
            return false;
        }

        return true;
    }

    private fun inicializarComponentesInterface() {
        textInputAlcool = findViewById(R.id.text_input_alcool);
        editAlcool = findViewById(R.id.edit_alcool);

        textInputGasolina = findViewById(R.id.text_input_gasolina);
        editGasolina = findViewById(R.id.edit_gasolina);

        btnCalcular = findViewById(R.id.btn_calcular);
        textResultado = findViewById(R.id.text_resultado);
    }
}
