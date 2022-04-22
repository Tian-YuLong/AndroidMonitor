package cn.tianyl.monitor

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import cn.tianyl.monitor.databinding.ConentSecondBinding


/**
 * @author tianyl
 * @since 2022/3/10
 */
class SecondActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ConentSecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ConentSecondBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val sp = getSharedPreferences("demo", MODE_PRIVATE).edit()
        Log.d("SecondActivity", "test sp")
        val key = Math.random() * Math.random()
        for (i in 0..1000) {
            sp.putString("demo $key $i", "value $key $i")
        }
        sp.apply()
        finish()
        binding.tv.setOnClickListener { view ->
            Log.d("SecondActivity", "test sp")
            val key = Math.random() * Math.random()
            for (i in 0..1000) {
                sp.putString("demo $key $i", "value $key $i")
            }
            sp.apply()
            Log.d("SecondActivity", "test sp ---------------------")
        }

        binding.tv2.setOnClickListener {
            Toast.makeText(this, "11111111111111111111111", Toast.LENGTH_SHORT).show()
        }
    }

}