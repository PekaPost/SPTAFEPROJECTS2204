package com.example.sptafeprojects2204

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sptafeprojects2204.databinding.ActivityMainBinding
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    // create menus
    private val menuADD = Menu.FIRST+1
    private val menuEDIT = Menu.FIRST+2
    private val menuDELETE = Menu.FIRST+3

    // create an array for employees and IDs

    private var employeeList:MutableList<Employee> = arrayListOf()
    private val idList:MutableList<Int> = arrayListOf()

    // create DBHandler class object
    private val dbh = DBHandler(this,null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Register for context menu
        registerForContextMenu(binding.lstEmployees)
        // load the records in the adapter
        initAdapter()
    }

    private fun initAdapter() {
        try {
            // clear existing valuse
            employeeList.clear()
            idList.clear()
            // get all the employees
            for (employee:Employee in dbh.getAllEmployees()){
                employeeList.add((employee))
                idList.add(employee.id)
            }

            // create a custom adapter
            val adp = CustomAdapter(this,employeeList as ArrayList<Employee>)
            // assign adapter to list view
            binding.lstEmployees.adapter = adp


        }catch (ex:Exception){
            // shor error message
            Toast.makeText(this,"Something has gone wrong: ${ex.message.toString()}", Toast.LENGTH_LONG).show()

        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.add(Menu.NONE,menuADD,Menu.NONE,"ADD")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // create intent to go to AddEdit activity
        val goAddEdit = Intent(this,AddEdit::class.java)
        startActivity(goAddEdit)
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menu.add(Menu.NONE,menuEDIT,Menu.NONE,"EDIT")
        menu.add(Menu.NONE,menuDELETE,Menu.NONE,"DELETE")
        super.onCreateContextMenu(menu, v, menuInfo)
    }
}