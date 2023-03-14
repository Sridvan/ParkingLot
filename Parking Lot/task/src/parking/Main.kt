package parking

import java.util.*

data class Car(val name: String, val color: String)

class Park() {
    private var size: Int = 0
    val list = mutableListOf<Car?>()

    fun setSize(x: Int) {
        size = x
    }

    fun getSize(): Int {
        return size
    }


    fun createParkLot() {
        for (i in 0 until size) {
            list.add(null)
        }
    }

}


fun main() {

    var park = Park()
    var commandList = readln().split(" ")

    while (commandList[0] != "exit") {
        if (commandList[0] == "create") {
            park = create(commandList)
        }

        if (commandList[0] == "park") {
            park(park, commandList)
        }

        if (commandList[0] == "leave") {
            leave(park, commandList)
        }

        if (commandList[0] == "status") {
            status(park)
        }

        if (commandList[0] == "reg_by_color") {
            regByColor(park, commandList)
        }
        if (commandList[0] == "spot_by_color") {
            spotByColor(park, commandList)
        }
        if (commandList[0] == "spot_by_reg") {
            spotByReg(park, commandList)
        }

        commandList = readln().split(" ")
    }

}

fun regByColor(park: Park, commandList: List<String>) {
    if (park.list.size > 0) {
        val finalList = mutableListOf<String>()
        var found = false
        for (i in 0 until park.getSize()) {
            if (park.list[i] != null) {
                if (park.list[i]!!.color.uppercase(Locale.getDefault())
                        .equals(commandList[1].uppercase(Locale.getDefault()))
                ) {
                    found = true
                    finalList.add(park.list[i]!!.name)
                }
            }
        }
        if (found) {
            println(finalList.joinToString())
        } else {
            println("No cars with color ${commandList[1].uppercase()} were found.")
        }
    } else {
        println("Sorry, a parking lot has not been created.")
    }
}

fun spotByColor(park: Park, commandList: List<String>) {
    if (park.list.size > 0) {
        val finalList = mutableListOf<Int>()
        var found = false
        for (i in 0 until park.getSize()) {
            if (park.list[i] != null) {
                if (park.list[i]!!.color.uppercase(Locale.getDefault())
                        .equals(commandList[1].uppercase(Locale.getDefault()))
                ) {
                    found = true
                    finalList.add(i + 1)
                }
            }
        }
        if (found) {
            println(finalList.joinToString())
        } else {
            println("No cars with color ${commandList[1].uppercase()} were found.")
        }
    } else {
        println("Sorry, a parking lot has not been created.")
    }
}

fun spotByReg(park: Park, commandList: List<String>) {
    if (park.list.size > 0) {
        var found = false
        for (i in 0 until park.getSize()) {
            if (park.list[i] != null) {
                if (park.list[i]!!.name.uppercase(Locale.getDefault())
                        .equals(commandList[1].uppercase(Locale.getDefault()))
                ) {
                    found = true
                    println(i + 1)
                }
            }
        }
        if (!found) {
            println("No cars with registration number ${commandList[1].uppercase()} were found.")
        }
    } else {
        println("Sorry, a parking lot has not been created.")
    }
}


fun status(park: Park) {
    if (park.list.size > 0) {
        var isEmpty = true
        for (i in 0 until park.getSize()) {
            if (park.list[i] != null) {
                isEmpty = false
                println("${i + 1} ${park.list[i]}")
            }
            if (i == park.getSize() - 1 && isEmpty) {
                println("Parking lot is empty.")
            }
        }
    } else {
        println("Sorry, a parking lot has not been created.")
    }
}

private fun create(commandList: List<String>): Park {
    val park = Park()
    park.setSize(commandList[1].toInt())
    park.createParkLot()
    println("Created a parking lot with ${commandList[1].toInt()} spots.")
    return park
}

private fun leave(park: Park, commandList: List<String>) {
    if (park.list.size > 0) {
        val x = commandList[1].toInt() - 1
        if (park.list[x] != null) {
            park.list[x] = null
            println("Spot ${x + 1} is free.")
        } else {
            println("There is no car in spot ${x + 1}.")
        }
    } else {
        println("Sorry, a parking lot has not been created.")
    }
}

private fun park(park: Park, commandList: List<String>) {
    if (park.list.size > 0) {
        for (i in 0 until park.getSize()) {
            if (park.list[i] == null) {
                park.list[i] = Car(commandList[1], commandList[2])
                println("${commandList[2]} car parked in spot ${(i + 1)}.")
                break
            }
            if (i == park.getSize() - 1) {
                println("Sorry, the parking lot is full.")
            }
        }
    } else {
        println("Sorry, a parking lot has not been created.")
    }
}
