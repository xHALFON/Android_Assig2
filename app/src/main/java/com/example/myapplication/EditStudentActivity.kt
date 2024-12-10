package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.data.StudentDatabase
// Code for edit student's information
class EditStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        val studentId = intent.getStringExtra("studentId")
        val student = StudentDatabase.students.find { it.id == studentId }

        val nameInput = findViewById<EditText>(R.id.nameInput)
        val idInput = findViewById<EditText>(R.id.idInput)
        val phoneInput = findViewById<EditText>(R.id.phoneInput)
        val addressInput = findViewById<EditText>(R.id.addressInput)
        val checkedInput = findViewById<CheckBox>(R.id.checkedInput)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val goBackButton = findViewById<Button>(R.id.goBackButton)
        val deleteButton = findViewById<Button>(R.id.deleteButton) // New Delete Button

        // Pre-fill student data - shows the existing information of the student
        nameInput.setText(student?.name)
        idInput.setText(student?.id)
        phoneInput.setText(student?.phone)
        addressInput.setText(student?.address)
        checkedInput.isChecked = student?.isChecked == true

        // Save button
        saveButton.setOnClickListener {
            student?.name = nameInput.text.toString()
            student?.id = idInput.text.toString()
            student?.phone = phoneInput.text.toString()
            student?.address = addressInput.text.toString()
            student?.isChecked = checkedInput.isChecked
            finish() // Return to the students list after saving with the changes
        }

        // Go Back button
        goBackButton.setOnClickListener {
            finish() // Return to the students list without saving
        }

        // Delete button logic
        deleteButton.setOnClickListener {
            if (student != null) {
                StudentDatabase.students.remove(student)
            }

            // Determine where to navigate based on remaining students
            if (StudentDatabase.students.isEmpty()) {
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent) // Go back to main menu if no students left
            } else {
                finish() // Go back to the "View All Students" screen
            }
        }
    }
}
