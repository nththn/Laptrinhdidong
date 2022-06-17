package com.example.viewpager2

class Note {
    var id: String =""
    var title: String = ""
    var content: String = ""
    var time: String = ""
    constructor(id: String ,title : String,content: String,time: String){
        this.id=id
        this.title = title
        this.content = content
        this.time = time
    }
    constructor(title : String,content: String,time: String){
        this.title = title
        this.content = content
        this.time = time
    }
}