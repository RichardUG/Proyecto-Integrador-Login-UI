package com.example.ietiapp.storage

interface Storage {
    fun saveToken(token:String)
    fun getToken():String
}