package com.example.covid_19

import android.app.Dialog
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.activity_diagnosa.*

class DiagnosaActivity : AppCompatActivity() {

    var question = arrayOf(
        "1. Apakah kamu baru saja berpergian keluar kota?",
        "2. Apakah kamu pernah melakukan kontak dengan pasien positif corona?",
        "3. Apakah suhu badan kamu lebih dari 38C?",
        "4. Apakah kamu mengalami sakit tenggorokan?",
        "5. Apakah kamu mengalami sesak nafas yang bukan dari asma?",
        "6. Apakah kamu mengalami batuk kering dan pilek?"
    )

    var choice = arrayOf(
        "Ya", "Tidak"
    )

    var answer = arrayOf(
        "Ya",
        "Ya",
        "Ya",
        "Ya",
        "Ya",
        "Ya"
    )

    lateinit var PopUp: Dialog
    lateinit var imagePopup: ImageView
    lateinit var titlePopup: TextView
    lateinit var messagepopup: TextView
    lateinit var buttonPopUP: Button
    lateinit var buttonPopUPcancel: Button

    var soal = 0
    var benar = 0
    var jawaban = ""
    var jawabanno2 = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diagnosa)

        PopUp = Dialog(this)

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
                jawaban = ""
                if (soal == 6) {
                    popup(benar, jawabanno2)
                } else {
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
        PopUp.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        PopUp.show()
    }

    override fun onBackPressed() {
        PopUp.setContentView(R.layout.popupexit)
        buttonPopUP = PopUp.findViewById(R.id.buttonexit)
        buttonPopUPcancel = PopUp.findViewById(R.id.buttonno)

        buttonPopUP.setOnClickListener {
            PopUp.dismiss()
            overridePendingTransition(R.anim.fadein, R.anim.fadeout)
            finish()
        }
        buttonPopUPcancel.setOnClickListener {
            PopUp.dismiss()
        }
        PopUp.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        PopUp.show()
    }
}