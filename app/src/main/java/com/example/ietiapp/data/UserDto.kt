package com.example.ietiapp.data

import java.time.LocalDate

class UserDto {
    private var id: String? = null
    private var name: String? = null
    private var email: String? = null
    private var lastName: String? = null
    private var createdAt: LocalDate? = null
    private var passwordHash: String? = null
    private var password: String? = null
    private var roles: List<RoleEnum>? = null

    constructor() {}

     constructor(name: String, lastName: String, email: String, password: String) {
        this.name = name
        this.lastName = lastName
        this.email = email
        this.password = password
    }

    fun getPassword(): String? {
        return password
    }

    fun setPassword(password: String?) {
        this.password = password
    }

    fun getPasswordHash(): String? {
        return passwordHash
    }

    fun setPasswordHash(passwordHash: String?) {
        this.passwordHash = passwordHash
    }

    fun getRoles(): List<RoleEnum?>? {
        return roles
    }

    fun setRoles(roles: List<RoleEnum>?) {
        this.roles = roles
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String?) {
        this.email = email
    }

    fun getLastName(): String? {
        return lastName
    }

    fun setLastName(lastName: String?) {
        this.lastName = lastName
    }

    fun getId(): String? {
        return id.toString()
    }

    fun setId(id: String?) {
        this.id = id
    }

    override fun toString(): String {
        return "UserDto(id=$id, name=$name, email=$email, lastName=$lastName, createdAt=$createdAt, passwordHash=$passwordHash, password=$password, roles=$roles)"
    }


}