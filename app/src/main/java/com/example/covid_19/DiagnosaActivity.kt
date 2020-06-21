package com.example.covid_19

import android.app.Dialog
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.activity_diagnosa.*
import java.util.*

class DiagnosaActivity : AppCompatActivity() {

    lateinit var PopUp: Dialog
    lateinit var imagePopup: ImageView
    lateinit var titlePopup: TextView
    lateinit var messagepopup: TextView
    lateinit var buttonPopUP: Button
    lateinit var buttonPopUPcancel: Button

    lateinit var question: Array<String>
    lateinit var choice: Array<String>
    lateinit var answer: Array<String>

    var soal = 0
    var nomorsoal = 1
    var benar = 0
    var jawaban = ""
    var jawabanno2 = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diagnosa)

        val language = Locale.getDefault().displayLanguage

        if (language == "English") {
            question = arrayOf(
                "Have you just returned from out of town?",
                "Have you ever been in contact with a corona positive patient?",
                "Is your body temperature more than 38C?",
                "Do you have a sore throat?",
                "Are you experiencing shortness of breath that is not from asthma?",
                "Do you experience a dry cough and flu?"
            )

            choice = arrayOf(
                "Yes", "No"
            )

            answer = arrayOf(
                "Yes",
                "Yes",
                "Yes",
                "Yes",
                "Yes",
                "Yes"
            )
        } else {
            question = arrayOf(
                "Apakah kamu baru saja berpergian keluar kota?",
                "Apakah kamu pernah melakukan kontak dengan pasien positif corona?",
                "Apakah suhu badan kamu lebih dari 38C?",
                "Apakah kamu mengalami sakit tenggorokan?",
                "Apakah kamu mengalami sesak nafas yang bukan dari asma?",
                "Apakah kamu mengalami batuk kering dan pilek?"
            )

            choice = arrayOf(
                "Ya", "Tidak"
            )

            answer = arrayOf(
                "Ya",
                "Ya",
                "Ya",
                "Ya",
                "Ya",
                "Ya"
            )
        }

        PopUp = Dialog(this)

        val nomor = nomorsoal.toString()
        nosoal.text = "$nomor/6"

        pertanyaan.text = question[soal]
        option_a.text = choice[0]
        option_b.text = choice[1]

        buttonjawab_a.setOnClickListener {
            jawaban = option_a.text as String
            buttonb.setImageResource(R.drawable.check)
            buttona.setImageResource(R.drawable.check_false)
        }

        buttonjawab_b.setOnClickListener {
            jawaban = option_b.text as String
            buttona.setImageResource(R.drawable.check)
            buttonb.setImageResource(R.drawable.check_false)
        }

        next_button.setOnClickListener {
            if (jawaban == "") {
                Toast.makeText(this, getString(R.string.empty), Toast.LENGTH_LONG).show()
            } else if (jawaban == answer[soal]) {
                if (soal == 1) {
                    jawabanno2 = jawaban == answer[soal]
                }
                benar++
            }

            if (jawaban != "") {
                soal++
                nomorsoal++
                jawaban = ""
                if (soal == 6) {
                    popup(benar, jawabanno2)
                } else {
                    val nomor = nomorsoal.toString()
                    nosoal.text = "$nomor/6"
                    pertanyaan.text = question[soal]
                    option_a.text = choice[0]
                    option_b.text = choice[1]
                    buttonb.setImageResource(R.drawable.check)
                    buttona.setImageResource(R.drawable.check)
                }
            }
        }

        back_diagnosis.setOnClickListener {
            onBackPressed()
        }
    }

    fun popup(nilai: Int, no2: Boolean) {
        PopUp.setContentView(R.layout.popup_donate)
        imagePopup = PopUp.findViewById(R.id.imagepop)
        titlePopup = PopUp.findViewById(R.id.thanks)
        messagepopup = PopUp.findViewById(R.id.text)
        buttonPopUP = PopUp.findViewById(R.id.buttondonate)

        if (no2) {
            imagePopup.setImageResource(R.drawable.warning)
            titlePopup.text = getString(R.string.warning)
            messagepopup.text = getString(R.string.danger)
        } else {
            if (nilai >= 3) {
                imagePopup.setImageResource(R.drawable.warning)
                titlePopup.text = getString(R.string.warning)
                messagepopup.text = getString(R.string.danger)
            } else {
                imagePopup.setImageResource(R.drawable.popup)
                titlePopup.text = getString(R.string.staysafe)
                messagepopup.text = getString(R.string.safe)
            }
        }

        buttonPopUP.setOnClickListener {
            PopUp.dismiss()
            finish()
        }

        PopUp.setCanceledOnTouchOutside(false)
        PopUp.setCancelable(false)

        PopUp.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        PopUp.show()
    }

    override fun onBackPressed() {
        PopUp.setContentView(R.layout.popupexit)
        buttonPopUP = PopUp.findViewById(R.id.buttonexit)
        buttonPopUPcancel = PopUp.findViewById(R.id.buttonno)

        buttonPopUP.setOnClickListener {
            PopUp.dismiss()
            finish()
        }
        buttonPopUPcancel.setOnClickListener {
            PopUp.dismiss()
        }
        PopUp.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        PopUp.show()
    }
}