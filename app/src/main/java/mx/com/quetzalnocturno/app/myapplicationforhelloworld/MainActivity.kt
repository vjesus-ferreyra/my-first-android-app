package mx.com.quetzalnocturno.app.myapplicationforhelloworld

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.net.toUri

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.button_email).setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = "mailto:".toUri()
                putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(R.string.email)))
                putExtra(Intent.EXTRA_SUBJECT, getString(R.string.subject_to_email))
            }

            try {
                startActivity(emailIntent)
            } catch (e : ActivityNotFoundException) {
                Toast.makeText(
                    this,
                    R.string.no_email_app,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        findViewById<Button>(R.id.button_go_github_profile).setOnClickListener {
            val browse = Intent(Intent.ACTION_VIEW, getString(R.string.github_profile).toUri())

            try {
                startActivity(browse)
            } catch (e : ActivityNotFoundException) {
            Toast.makeText(
                this,
                R.string.no_action,
                Toast.LENGTH_SHORT
            ).show()
        }
        }
    }
}