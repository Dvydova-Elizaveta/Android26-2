package ru.urfu.droidpractice1

import androidx.activity.ComponentActivity
import android.os.Bundle
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding
import android.content.Intent
import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.core.content.res.ResourcesCompat

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding

    private val photos = listOf(
        R.drawable.photo1,
        R.drawable.photo2,
        R.drawable.photo3,
        R.drawable.photo4,
        R.drawable.photo5
    )

    private var currentPhoto = 0

    private val photos2 = listOf(
        R.drawable.photo6,
        R.drawable.photo7,
        R.drawable.photo8,
        R.drawable.photo9
    )

    private var currentPhoto2 = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val isRead = intent.getBooleanExtra("is_read", false)
        binding.readSwitch.isChecked = isRead


        binding.nextButton.setOnClickListener {
            if (currentPhoto < photos.size - 1) {
                currentPhoto++
                showPhoto()
            }
        }

        binding.previousButton.setOnClickListener {
            if (currentPhoto > 0) {
                currentPhoto--
                showPhoto()
            }
        }


        val captions2 = listOf(
            getString(R.string.phototext2),
            getString(R.string.phototext3),
            getString(R.string.phototext4),
            getString(R.string.phototext5)
        )

        binding.nextButton2.setOnClickListener {
            if (currentPhoto2 < photos2.size - 1) {
                currentPhoto2++
                showPhoto2(captions2)
            }
        }

        binding.previousButton2.setOnClickListener {
            if (currentPhoto2 > 0) {
                currentPhoto2--
                showPhoto2(captions2)
            }
        }

        binding.toolbar.setNavigationOnClickListener {
            returnResult()
        }
    }

    private fun showPhoto() {
        binding.articleImage.setImageResource(photos[currentPhoto])
        binding.photoCaption.text = getString(R.string.phototext1)

        binding.previousButton.isEnabled = currentPhoto > 0
        binding.nextButton.isEnabled = currentPhoto < photos.size - 1
    }

    private fun showPhoto2(captions2: List<String>) {
        binding.articleImage2.setImageResource(photos2[currentPhoto2])
        binding.photoCaption2.text = captions2[currentPhoto2]

        binding.previousButton2.isEnabled = currentPhoto2 > 0
        binding.nextButton2.isEnabled = currentPhoto2 < photos2.size - 1
    }


    private fun returnResult() {
        val resultIntent = Intent().apply {
            putExtra("is_read", binding.readSwitch.isChecked)
        }

        setResult(RESULT_OK, resultIntent)
        finish()
    }
}