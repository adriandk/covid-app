package com.example.covid_19.contact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
                nama_feed.error = "ISI DULU"
            } else if (email.isEmpty()) {
                email_feed.error = "ISI DULU"
            } else if (feed.isEmpty()) {
                saran_feedback.error = "ISI DULU"
            } else {
                val database = FirebaseDatabase.getInstance().getReference("Saran dan masukan")
                val saranId = database.push().key
                val saran = DataFeedback(saranId, name, email, feed)

                if (saranId != null) {
                    database.child(name).setValue(saran).addOnCompleteListener {
                        Toast.makeText(this, "ARIGATO", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(this, getString(R.string.feedback), Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}