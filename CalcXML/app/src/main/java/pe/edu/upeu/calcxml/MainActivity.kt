package pe.edu.upeu.calcxml

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.*

class MainActivity : AppCompatActivity() {

    private lateinit var txtResultado:EditText
    private var valAnt = 0.0
    private var valAct = 0.0
    private var operador = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        txtResultado = findViewById(R.id.txtResult)
        btns()
    }

    private fun btns(){
        var buttons = arrayOf(R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5,
            R.id.btn6, R.id.btn7, R.id.btn9, R.id.btn0, R.id.btnMult, R.id.btnSum, R.id.btnRaiz,
            R.id.btnCuandrado, R.id.btnDivid, R.id.btnMod, R.id.btnPi, R.id.btnIgual,
            R.id.btnPunto, R.id.btnDel)

        for (buttonId in buttons){
            var btn = findViewById<Button>(buttonId)
            btn.setOnClickListener{ onClickListener(btn) }
        }
    }

    private fun onClickListener(view:View){
        when(view.id){
            R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5,
            R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btn0 -> {
                var btnX = findViewById<Button>(view.id)
                appendContent(btnX.text.toString())
            }
            R.id.btnSum, R.id.btnMult -> {
                var btnX = findViewById<Button>(view.id)
                setOperador(btnX.text.toString())
            }
            R.id.btnIgual -> {
                operation()
            }
        }

    }

    private fun appendContent(valor:String){
        txtResultado.append(valor)
    }

    private fun setOperador(oper:String){
        operador = oper
        valAnt = txtResultado.text.toString().toDouble()
        txtResultado.text.clear()
    }

    private fun operation(){
        valAct = txtResultado.text.toString().toDouble()
        var result = when(operador){
            "*" -> valAnt * valAct
            "+" -> valAnt + valAct
            "χ2" -> valAnt.pow(2)
            "/" -> valAnt / valAct
            "√" -> sqrt(valAct)
            "%" -> valAnt % valAct
            "pi" -> 3.1416
            else -> valAct
        }
        txtResultado.setText(result.toString())
        operador=""
    }

}