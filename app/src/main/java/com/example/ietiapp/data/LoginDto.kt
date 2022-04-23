package com.example.ietiapp.data

class LoginDto  {
    var email:String =""
        get():String = field
        set(value) {
            field=value
        }
    var password:String =""
        get():String=field
        set(value) {
            field=value
        }

    constructor(email:String,password:String){
        this.email=email
        this.password=password

    }
    constructor()

    override fun toString(): String {
        return "LoginDto(email='$email', password='$password')"
    }


}