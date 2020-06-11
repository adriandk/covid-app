package com.example.covid_19.contact

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.example.covid_19.PhotoViewer
import com.example.covid_19.R
import kotlinx.android.synthetic.main.fragment_contact.*

class ContactFragment : Fragment() {

    lateinit var PopUp: Dialog
    lateinit var closeButton: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        PopUp = Dialog(view.context)

        gambar.setOnClickListener {
            val pass = Intent(context, PhotoViewer::class.java)
            pass.putExtra("Gambar", "BRUH")
            startActivity(pass)
        }

        about_dev.setOnClickListener {
            popUpContact()
        }

        donate.setOnClickListener {

        }

        help_feedback.setOnClickListener {

        }
    }

    private fun popUpContact() {
        PopUp.setContentView(R.layout.popup_contact)
        closeButton = PopUp.findViewById(R.id.close_button)



        closeButton.setOnClickListener {
            PopUp.dismiss()
        }
        PopUp.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        PopUp.show()

    }
}