package com.example.covid_19.contact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.covid_19.R
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_feedback.*

class FeedbackActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        button_feed.setOnClickListener {
            val name = nama_feed.text.toString().trim()
            val email = email_feed.text.toString().trim()
            val feed = saran_feedback.text.toString()
            if (name.isEmpty()) {
                nama_feed.error = getString(R.string.error)
            } else if (email.isEmpty()) {
                email_feed.error = getString(R.string.error)
            } else if (feed.isEmpty()) {
                saran_feedback.error = getString(R.string.error)
            } else {
                val database = FirebaseDatabase.getInstance().getReference("Saran dan masukan")
                val saranId = database.push().key
                val insertData = DataFeedback(saranId, name, email, feed)

                if (saranId != null) {
                    loading.visibility = View.VISIBLE
                    database.child(saranId).setValue(insertData).addOnCompleteListener {
                        loading.visibility = View.GONE
                        Toast.makeText(this, getString(R.string.thankyou), Toast.LENGTH_LONG).show()
                        onBackPressed()
                    }
                } else {
                    Toast.makeText(this, getString(R.string.masukan), Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}