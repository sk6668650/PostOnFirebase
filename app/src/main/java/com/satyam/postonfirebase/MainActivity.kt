package com.satyam.postonfirebase

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var filePath: Uri

    var imageUrl: Uri? = null

    private var databaseReference: DatabaseReference? = null
    private var storageReference:  StorageReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        databaseReference = FirebaseDatabase.getInstance().getReference("User_Details")
        storageReference = FirebaseStorage.getInstance().reference

        selectPic.setOnClickListener(){
            getPicture()
        }

        downloadPic.setOnClickListener(){

            val i = Intent(this@MainActivity, All::class.java)
            startActivity(i)
        }



       uploadpic.setOnClickListener(){

       if(filePath!=null)
         {


          val ref = storageReference!!.child(

              "upload/" + System.currentTimeMillis() + "." + getFileExt(filepath = filePath)
          )

          ref.putFile(filePath).addOnSuccessListener { taskSnapshot ->
              val firstName: String = firstName.text.toString()
              val lastName: String = lastName.text.toString()

              val data = taskSnapshot.storage.downloadUrl

              while (!data.isSuccessful);
              imageUrl = data.result
              val imageFilePath = imageUrl.toString()
              val product = Product()
              product.name = firstName
              product.last = lastName
              product.url = imageFilePath
              val childId = databaseReference!!.push().key
              databaseReference!!.child(childId!!).setValue(product)
              Toast.makeText(
                  this@MainActivity,
                  "data uploaded successfully",
                  Toast.LENGTH_SHORT
              ).show()
          }.addOnFailureListener { e ->
              Toast.makeText(this@MainActivity, "" + e.message, Toast.LENGTH_SHORT).show()
          }


      }
      else
       {
         Toast.makeText(this,"please select picture",Toast.LENGTH_SHORT).show()
       }

    }


    }



    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            filePath= data?.data!!
            //Toast.makeText(this,""+filePath,Toast.LENGTH_SHORT).show()
            imageV.setImageURI(filePath)
        } else {
            Toast.makeText(this, "Try Again", Toast.LENGTH_SHORT).show()
        }
    }


    private fun getPicture() {
        val i = Intent()
        i.type = "image/*"
        i.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(i, 0)
    }

    private fun getFileExt(filepath: Uri): String? {
        val cr = contentResolver
        val map = MimeTypeMap.getSingleton()

        return map.getExtensionFromMimeType(cr.getType(filepath))
    }

}





