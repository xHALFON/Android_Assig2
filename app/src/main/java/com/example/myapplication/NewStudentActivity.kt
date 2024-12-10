package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.data.Student
import com.example.myapplication.data.StudentDatabase
// Code for adding a new student to the database
class NewStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_student)

        val nameInput = findViewById<EditText>(R.id.nameInput)
        val idInput = findViewById<EditText>(R.id.idInput)
        val phoneInput = findViewById<EditText>(R.id.phoneInput)
        val addressInput = findViewById<EditText>(R.id.addressInput)
        val checkedInput = findViewById<CheckBox>(R.id.checkedInput)
        val saveButton = findViewById<Button>(R.id.saveButton)

        saveButton.setOnClickListener {
            // Collect data from inputs
            val name = nameInput.text.toString()
            val id = idInput.text.toString()
            val phone = phoneInput.text.toString()
            val address = addressInput.text.toString()
            val isChecked = checkedInput.isChecked

            // Validate inputs
            if (name.isNotBlank() && id.isNotBlank()) {
                // Create a new Student object
                val newStudent = Student(
                    id = id,
                    name = name,
                    phone = phone,
                    address = address,
                    isChecked = isChecked
                )

                // Add the new student to the database
                StudentDatabase.students.add(newStudent)

                // Close the activity and return to MainActivity
                finish()
            } else {
                // Handle invalid inputs (must have a name and id)
                nameInput.error = "Name is required"
                idInput.error = "ID is required"
            }
        }


    }
}
