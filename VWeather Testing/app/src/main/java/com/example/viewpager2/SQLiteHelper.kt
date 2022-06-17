package com.example.viewpager2

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.icu.text.CaseMap
import java.lang.Exception

class SQLiteHelper (context: Context): SQLiteOpenHelper(context, DATABASE_NAME,null,1){
    companion object{
        private val DATABASE_NAME = "MyWeatherDB"
        private val TABLE_NAME = "Cities"
        private val COL_ID = "id"
        private val COL_NAME = "name"
//        private val COL_IMAGE = "image"
        private val COL_LAT = "lat"
        private val COL_LON = "lon"


        private val TABLE_NOTE_NAME = "Notes"
        private val COL_NOTE_ID = "id"
        private val COL_TITLE = "title"
        private val COL_CONTENT = "content"
        private val COL_TIME = "time"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTblCity = "CREATE TABLE " + TABLE_NAME +" ("+
                COL_ID + " NVARCHAR(256),"+
                COL_NAME + " NVARCHAR(256),"+
                COL_LON + " NVARCHAR(256),"+
                COL_LAT + " NVARCHAR(256))"

        val createTblNote = "CREATE TABLE "+ TABLE_NOTE_NAME +" ("+ COL_NOTE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+ COL_TITLE + " NVARCHAR(256),"+ COL_CONTENT + " TEXT,"+ COL_TIME + " NVARCHAR(256))"



        db?.execSQL(createTblCity)
        db?.execSQL(createTblNote)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun insertCity(city: City): Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_NAME, city.name)
        contentValues.put(COL_ID,city.id)
        contentValues.put(COL_LON, city.lon)
        contentValues.put(COL_LAT,city.lat)
        val success = db.insert(TABLE_NAME, null, contentValues)
        db.close()
        return success

    }

    fun insertNote(note: Note): Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        //contentValues.put(COL_NOTE_ID, note.id)
        contentValues.put(COL_TITLE,note.title)
        contentValues.put(COL_CONTENT, note.content)
        contentValues.put(COL_TIME,note.time)
        val success = db.insert(TABLE_NOTE_NAME, null, contentValues)
        //db.close()
        return success

    }


    @SuppressLint("Range")
    fun getAllNote(): ArrayList<Note>{
        val noteList: ArrayList<Note> = ArrayList()
        val selectQuery = "SELECT * FROM $TABLE_NOTE_NAME"
        val db = this.readableDatabase

        val cursor: Cursor?

        try{
            cursor = db.rawQuery(selectQuery,null)
        }catch (e: Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: String
        var title: String
        var content: String
        var time : String
        if(cursor.moveToFirst()){
            do{
                id = cursor.getString(cursor.getColumnIndex(COL_NOTE_ID))
                title = cursor.getString(cursor.getColumnIndex(COL_TITLE))
                content = cursor.getString(cursor.getColumnIndex(COL_CONTENT))
                time = cursor.getString(cursor.getColumnIndex(COL_TIME))
                val note = Note(id = id ,title = title, content = content,time=time)
                noteList.add(note)
            }while (cursor.moveToNext())
        }
        return noteList
    }


    fun updateNote(note: Note): Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(COL_TITLE,note.title)
        contentValues.put(COL_CONTENT,note.content)
        contentValues.put(COL_TIME,note.time)
        //contentValues.put(COL_IMAGE,city.image)
        val success = db.update(TABLE_NOTE_NAME, contentValues, "id="+note.id,null)
        //db.close()
        return success
    }

    fun deleteNote(id: String): Int{
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(COL_NOTE_ID,id)
        val success = db.delete(TABLE_NOTE_NAME, "id='$id'",null)
        //db.close()
        return success
    }



//    fun insertCityImage(city: City,image :String): Int{
//        val db = this.writableDatabase
//        val contentValues = ContentValues()
//
//        contentValues.put(COL_IMAGE,image)
//
//
//        val success = db.update(TABLE_NAME, contentValues, "name="+ city.name,null)
//        db.close()
//        return success
//
//    }



    @SuppressLint("Range")
    fun getAllCity(): ArrayList<City>{
        val cityList: ArrayList<City> = ArrayList()
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val db = this.readableDatabase

        val cursor: Cursor?

        try{
            cursor = db.rawQuery(selectQuery,null)
        }catch (e: Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var name: String
        var lat: String
        var lon: String
        var id : String
        if(cursor.moveToFirst()){
            do{
                id = cursor.getString(cursor.getColumnIndex(COL_ID))
                name = cursor.getString(cursor.getColumnIndex(COL_NAME))
                lat = cursor.getString(cursor.getColumnIndex(COL_LAT))
                lon = cursor.getString(cursor.getColumnIndex(COL_LON))
                val city = City(id = id ,name = name, lat = lat,lon=lon)
                cityList.add(city)
            }while (cursor.moveToNext())
        }
        return cityList
    }

    fun updateCity(city: City, name: String): Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(COL_NAME,name)

        //contentValues.put(COL_IMAGE,city.image)
        val success = db.update(TABLE_NAME, contentValues, "id="+ city.id,null)
        //db.close()
        return success
    }

    fun deleteCity(name: String): Int{
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(COL_NAME,name)
        val success = db.delete(TABLE_NAME, "name='$name'",null)
        //db.close()
        return success
    }
}