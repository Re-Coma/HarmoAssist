package kr.sweetcase.harmoassist
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        EnterButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity,
                MusicListActivity::class.java)
            startActivity(intent)
        })

        val text = findViewById<TextView>(R.id.title_text)

    }
}