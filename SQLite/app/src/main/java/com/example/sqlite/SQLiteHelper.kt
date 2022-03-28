package com.example.sqlitedemo

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class SQLiteHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory: null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "student.db"
        private const val ID ="id"
        private const val TBL STUDENT = "tbl_student"
        private const val NAME ="name"
        private const val EMAIL = "email"
        private const val CONTACT = "contact"
        private const val ADDRESS = "address"
    }

        override fun onCreate(db: SQLiteDatebase?) {
            val createTblStudent =
                ("CREATE TABLE " + TBL_STUDENT + "(" + ID + "INTEGER PRIMARY KEY," + NAME + "TEXT,"
                        + EMAIL + "TEXT" + CONTACT + "TEXT" + ADDRESS + "TEXT" + ")")
            db?.excelSQL(createTblStudent)
        }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int){
        db!!.excelSQL(sql "DROP TABLE IF EXISTS $TBL_STUDENT")
        oncreate(db)
        }

    fun insertStudent()



    }