package app.nikhil.kotlinflows

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import app.nikhil.kotlinflows.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {

  private lateinit var binding: ActivityMainBinding

  companion object {
    private const val TAG = "MainActivity"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(LayoutInflater.from(this))

    if (savedInstanceState == null) {
      supportFragmentManager.commit {
        setReorderingAllowed(true)
        add<FlowCollectFragment>(R.id.fragment_container_view)
      }
    }
  }
}