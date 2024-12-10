package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.data.StudentDatabase

class StudentDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        val studentId = intent.getStringExtra("studentId")
        val student = StudentDatabase.students.find { it.id == studentId }

        findViewById<TextView>(R.id.studentNameTextView).text = student?.name
        findViewById<TextView>(R.id.studentIdTextView).text = student?.id
        findViewById<TextView>(R.id.studentPhoneTextView).text = student?.phone
        findViewById<TextView>(R.id.studentAddressTextView).text = student?.address

        // Set the checked status
        val checkedBox = findViewById<CheckBox>(R.id.studentChecked)
        checkedBox.isChecked = student?.isChecked == true

        // Disable the checkbox to prevent editing
        checkedBox.isEnabled = false

        // Go Back button
        findViewById<Button>(R.id.goBackButton).setOnClickListener {
            finish()
        }
    }
}
