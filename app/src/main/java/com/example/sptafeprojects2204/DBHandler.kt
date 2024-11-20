package com.example.sptafeprojects2204

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHandler(
    context: Context?,
    factory: SQLiteDatabase.CursorFactory?
) : SQLiteOpenHelper(context, DBname, factory, version) {

    companion object{
        const val DBname ="HRManager.db"
        const val version = 1
    }

    // Declare Table name and FIELDS
    private val TableName = "EMPLOYEE"
    private val KeyID = "ID"
    private val KeyName = "NAME"
    private val KeyMobile = "MOBILE"
    private val KeyEmail = "EMAIL"
    private val KeyAddress = "ADDRESS"
    private val KeyImage = "IMAGE"

    override fun onCreate(db: SQLiteDatabase) {
        // create the SQL statement for table
        val createTable = "CREATE TABLE $TableName ($KeyID INTEGER PRIMARY KEY AUTOINCREMENT, $KeyName TEXT, $KeyMobile TEXT, $KeyEmail TEXT, $KeyAddress TEXT, $KeyImage TEXT)"
        // execute SQL
        db.execSQL(createTable)
        // add some sample data using ContentValue
        val cv = ContentValues()
        // use put method to add field values
        cv.put(KeyName, "Steve Perkins")
        cv.put(KeyMobile, "0490366534")
        cv.put(KeyEmail, "stevepp@hotmail.com")
        cv.put(KeyAddress, "Sydney")
        cv.put(KeyImage, "first")
        // use insert method to add to table
        db.insert(TableName,null,cv)


    }

    override fun onUpgrade(db: SQLiteDatabase, old: Int, new: Int) {
        // drop the existing table and recreate
        db.execSQL("DROP TABLE IF EXISTS $TableName")
        onCreate(db)
    }

    // get all the records from database
    fun getAllEmployees():ArrayList<Employee>{
        // declare an array list to fill employee objects from DB
        val employeeList = ArrayList<Employee>()
        // SQL query to select all employees
        val selectQuery = "SELECT * FROM $TableName"
        // get readable database
        val db = this.readableDatabase
        // run query and get the result in cursor
        val cursor = db.rawQuery(selectQuery,null)
        // move through cursor to read all records
        if(cursor.moveToFirst()){
            // loop thru all possible records
            do {
                // create an employee object to put all the valuse
                val employee = Employee()
                employee.id = cursor.getInt(0)
                employee.name = cursor.getString(1)
                employee.mobile = cursor.getString(2)
                employee.email = cursor.getString(3)
                employee.address = cursor.getString(4)
                employee.imageFile = cursor.getString(5)
                // add to array list
                employeeList.add(employee)


            }while (cursor.moveToNext())
        }
        // close the database
        cursor.close()
        db.close()
        // return all the employee list from db
        return employeeList
    }



}