package coxtunes.com.test

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions


class MainActivity : AppCompatActivity() {


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            scanCode()
        }
    }

    private fun scanCode() {
        val options = ScanOptions()
        options.setPrompt("Volume up to flash on")
        options.setBeepEnabled(true)
        options.setTorchEnabled(false)
        options.setOrientationLocked(false)
        options.captureActivity = CaptureAct::class.java
        barLaucher.launch(options)
    }

    private var barLaucher = registerForActivityResult(
        ScanContract()
    ) { result: ScanIntentResult ->
        if (result.contents != null) {
            val builder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(this@MainActivity)
            builder.setTitle("Result")
            builder.setMessage(result.contents)
            builder.setPositiveButton("OK"
            ) { dialogInterface, i -> dialogInterface.dismiss() }
                .show()
        }
    }
}