package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*
import java.lang.reflect.Member

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var auth: FirebaseAuth? = null
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = FirebaseAuth.getInstance()

        if (auth!!.currentUser != null){
            val it = Intent(this, Member::class.java)
            startActivity(it)
            finish()
        }


        re1.setOnClickListener {
            val Email = email1.getText().toString().trim()
            val Pass = pass1.getText().toString().trim()

            if (Email.isEmpty()){
                Toast.makeText(this, "กรุณากรก email.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (Pass.isEmpty()){
                Toast.makeText(this, "กรุณากรอก password.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth!!.createUserWithEmailAndPassword(Email,Pass).addOnCompleteListener{task ->
                if(!task.isSuccessful){
                    if(Pass.length < 8 ){
                        pass1.error="กรุณากรอกรหัสมากกว่า 8 ตัวอักษร."
                    }
                    else{
                        Toast.makeText(this, "Register failed: "+task.exception!!.message, Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(this, "Register Success!", Toast.LENGTH_SHORT).show()
                    val it = Intent(this, Member::class.java)
                    startActivity(it)
                    finish()
                }
            }
        }

        login1.setOnClickListener {
            val it = Intent(this, Login::class.java)
            startActivity(it)
        }

    }
}