package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.StudentDatabase
// Code for main activity and actions
class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var goBackButton: Button
    private lateinit var addStudentButton: Button
    private lateinit var viewAllStudentsButton: Button
    private lateinit var buttonsLayout: View
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
// Initiating the recyclerView to hold the Students information
        recyclerView = findViewById(R.id.studentsRecyclerView)
        goBackButton = findViewById(R.id.goBackButton)
        addStudentButton = findViewById(R.id.addStudentButton)
        viewAllStudentsButton = findViewById(R.id.viewAllStudentsButton)
        buttonsLayout = findViewById(R.id.buttonsLayout)
// Setting up the buttons for the student information view.
        adapter = StudentAdapter(StudentDatabase.students) { student, action ->
            when (action) {
                "view" -> {
                    val intent = Intent(this, StudentDetailsActivity::class.java)
                    intent.putExtra("studentId", student.id)
                    startActivity(intent)
                }
                "edit" -> {
                    val intent = Intent(this, EditStudentActivity::class.java)
                    intent.putExtra("studentId", student.id)
                    startActivity(intent)
                }
                "delete" -> {
                    StudentDatabase.students.remove(student)
                    adapter.notifyDataSetChanged()
                    Toast.makeText(this, "Student deleted", Toast.LENGTH_SHORT).show()
                }
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        addStudentButton.setOnClickListener {
            startActivity(Intent(this, NewStudentActivity::class.java))
        }

        viewAllStudentsButton.setOnClickListener {
            showRecyclerView()
        }

        goBackButton.setOnClickListener {
            showMainMenu()
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    override fun onBackPressed() {
        if (recyclerView.visibility == View.VISIBLE) {
            showMainMenu() // Return to the main menu
        } else {
            super.onBackPressed() // Default behavior (exit the app)
        }
    }

    private fun showRecyclerView() {
        if (StudentDatabase.students.isEmpty()) {
            Toast.makeText(this, "No students available", Toast.LENGTH_SHORT).show()
            return
        }
        recyclerView.visibility = View.VISIBLE
        goBackButton.visibility = View.VISIBLE // Show "Go Back to Main Menu" button
        buttonsLayout.visibility = View.GONE
    }

    private fun showMainMenu() {
        recyclerView.visibility = View.GONE
        goBackButton.visibility = View.GONE // Hide "Go Back to Main Menu" button
        buttonsLayout.visibility = View.VISIBLE
    }
}
