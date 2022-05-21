package com.example.ietiapp.data

import java.time.LocalDate
import java.util.*
import java.util.EnumSet.of
import java.util.List.of

class TokenDto {
    var token: String=""
        get():String = field
        set(value) {
            field=value
        }
    var expirationDate: Long = 0
        get():Long=field
        set(value) {
            field=value
        }
    constructor(){}

    constructor(token: String, expirationDate: Long) {
        this.token = token
        this.expirationDate = expirationDate
    }

    override fun toString(): String {
        return "TokenDto(token='$token', expirationDate=$expirationDate)"
    }


}