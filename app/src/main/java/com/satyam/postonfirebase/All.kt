package com.satyam.postonfirebase

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_all.*


class All : AppCompatActivity() {

    private  lateinit var adapterr: adapter

    lateinit var databaseReference: DatabaseReference
    lateinit var list1:ArrayList<Product>
    lateinit var gg:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all)

        databaseReference = FirebaseDatabase.getInstance().getReference("User_Details")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                list1 = ArrayList()

                for ( ds in dataSnapshot.children )
                {
                    var product = Product()
                     product = ds.getValue(Product::class.java)!!

                     list1.add(product)

                }

                recyclerView.layoutManager = LinearLayoutManager(this@All)
                adapterr = adapter(applicationContext,list1)

                recyclerView.adapter = adapterr



            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@All, "" + databaseError.message, Toast.LENGTH_SHORT)
                    .show()
            }
        })

    }


    }