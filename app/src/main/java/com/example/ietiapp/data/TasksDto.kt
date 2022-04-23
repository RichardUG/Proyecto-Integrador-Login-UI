package com.example.ietiapp.data

class TasksDto {
    var name:String =""
        get():String = field
        set(value) {
            field=value
        }
    var description:String =""
        get():String=field
        set(value) {
            field=value
        }
    var status:String =""
        get():String=field
        set(value) {
            field=value
        }
    var assignedTo:String =""
        get():String=field
        set(value) {
            field=value
        }
    var dueDate:String =""
        get():String=field
        set(value) {
            field=value
        }

    constructor(name:String,description:String,status:String,assignedTo:String,dueDate:String){
        this.name=name
        this.description=description
        this.status=status
        this.assignedTo=assignedTo
        this.dueDate=dueDate

    }
    constructor()

    override fun toString(): String {
        return "TasksDto(name='$name', description='$description', status='$status', assignedTo='$assignedTo', dueDate='$dueDate')"
    }


}