package com.example.covid_19.contact

import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.covid_19.PhotoViewer
import com.example.covid_19.R
import kotlinx.android.synthetic.main.fragment_contact.*

class ContactFragment : Fragment() {

    lateinit var PopUp: Dialog
    lateinit var closeButton: ImageButton
    lateinit var okbutton: Button

    lateinit var whatsApp: ImageButton
    lateinit var instaGram: ImageButton
    lateinit var gitHub: ImageButton
    lateinit var Email: ImageButton


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
            popUpDonate()
        }

        help_feedback.setOnClickListener {
            val pindah = Intent(context, FeedbackActivity::class.java)
            activity!!.overridePendingTransition(R.anim.fadein, R.anim.fadeout)
            startActivity(pindah)
        }
    }

    private fun popUpDonate() {
        PopUp.setContentView(R.layout.popup_donate)
        okbutton = PopUp.findViewById(R.id.buttondonate)
        okbutton.setOnClickListener {
            PopUp.dismiss()
        }
        PopUp.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        PopUp.show()
    }

    private fun popUpContact() {
        PopUp.setContentView(R.layout.popup_contact)
        closeButton = PopUp.findViewById(R.id.close_button)
        whatsApp = PopUp.findViewById(R.id.whatsapp)
        instaGram = PopUp.findViewById(R.id.instagram)
        gitHub = PopUp.findViewById(R.id.github)
        Email = PopUp.findViewById(R.id.email)
        val intent = Intent(Intent.ACTION_VIEW)

        whatsApp.setOnClickListener {
            intent.data = Uri.parse("https://wa.me/+628818117213")
            startActivity(intent)
        }

        instaGram.setOnClickListener {
            intent.data = Uri.parse("https://www.instagram.com/adriandk_/?hl=en")
            startActivity(intent)
        }

        gitHub.setOnClickListener {
            intent.data = Uri.parse("https://github.com/adriandk")
            startActivity(intent)
        }

        Email.setOnClickListener {
            val sendEmail = Intent(Intent.ACTION_SENDTO)
            sendEmail.data = Uri.parse("mailto:adriandaniel1803@gmail.com")
            try {
                startActivity(sendEmail)
            } catch (ex: ActivityNotFoundException) {
                Toast.makeText(context, "Tidak bisa mengirim email", Toast.LENGTH_LONG).show()
            }
        }

        closeButton.setOnClickListener {
            PopUp.dismiss()
        }
        PopUp.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        PopUp.show()

    }
}