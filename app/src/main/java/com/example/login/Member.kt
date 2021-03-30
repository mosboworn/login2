package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_member.*

class Member : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var auth: FirebaseAuth? = null
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member)
        auth = FirebaseAuth.getInstance()

        val userData = auth!!.currentUser
        textView3.text = userData?.uid.toString()+" "+ userData!!.email

       logout.setOnClickListener {
            auth!!.signOut()
            Toast.makeText(this, "Logout Complete", Toast.LENGTH_SHORT).show()

            val it = Intent(this, MainActivity::class.java)
            startActivity(it)
            finish()
        }

    }
}