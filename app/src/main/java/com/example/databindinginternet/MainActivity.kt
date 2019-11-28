package com.example.databindinginternet

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.databindinginternet.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityMainBinding
    private  val myContact = Contact("See","012345678")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)


        binding.contact = myContact

        binding.buttonSave.setOnClickListener {
            with(binding){
                contact?.name = editTextName.text.toString()
                contact?.phone = editTextPhone.text.toString()
                invalidateAll()
            }
        }

        binding.buttonSend.setOnClickListener {
            //Create an Explicit Intent
            val intent = Intent(this,SecondActivity::class.java)

            //Prepare Extra data for the intent
            intent.putExtra("EXTRA_NAME",binding.contact?.name)
            intent.putExtra("EXTRA_PHONE",binding.contact?.phone)

            startActivity(intent)//your parext expect no return value
            startActivityForResult(intent, REQUEST_REPLY )//PTPTN expects results
            }
    }

    override fun onActivityResult(requestCode: Int,
                                  resultCode: Int,
                                  data: Intent?) {
        if(requestCode == REQUEST_REPLY){
            if(requestCode == Activity.RESULT_OK){
                val reply = data?.getStringExtra(EXTRA_REPLY)
                textViewReply.text = String.format(getString(R.string.Reply)+": %s",reply)
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    companion object{
        const val EXTRA_NAME = "com.example.databindinginternet.NAME"
        const val EXTRA_PHONE = "com.example.databindinginternet.PHONE"
        const val REQUEST_REPLY = 1
        const val EXTRA_REPLY = "com.example.databindinginternet.EXTRA_REPLY"
    }

}//End of the Class
