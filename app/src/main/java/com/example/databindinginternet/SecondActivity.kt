package com.example.databindinginternet

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val name = intent.getStringExtra(MainActivity.EXTRA_NAME)
        textViewReceiveName.text = name
        val phone = intent.getStringExtra(MainActivity.EXTRA_PHONE)
        textViewReceivePhone.text = name

        buttonDone.setOnClickListener {
            val intent = getIntent()

            if(!editTextReply.text.isEmpty()){
                val reply = editTextReply.text.toString()
                intent.putExtra(MainActivity.EXTRA_REPLY,reply)
                setResult(Activity.RESULT_OK,intent)
            }
            else{
                setResult(Activity.RESULT_CANCELED,intent)
            }
        }

        buttonCall.setOnClickListener {
            val phone = Uri.parse("tel:"+ textViewReceivePhone.text.toString())

            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(phone)

            if(intent.resolveActivity(packageManager)!= null){
                startActivity(intent)
            }
        }

    }
}
