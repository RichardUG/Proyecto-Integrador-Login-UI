package com.example.ietiapp.data

import java.time.LocalDate
import java.util.*
import java.util.EnumSet.of
import java.util.List.of

class TokenDto {
    var token:String =""
        get():String = field
        set(value) {
            field=value
        }
    var expirationDate:Date=Date(2000, 1, 1)
        get():Date=field
        set(value) {
            field=value
        }

    constructor(token:String,expirationDate:Date){
        this.token=token
        this.expirationDate=expirationDate

    }
    constructor()

    override fun toString(): String {
        return "TokenDto(token='$token', expirationDate=$expirationDate)"
    }


}