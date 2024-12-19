package classes

import java.time.LocalDate
import java.time.LocalTime
import kotlin.system.exitProcess

lateinit var account: Account
fun main() {
    account = Account(1001, "Vijay", 0.0)

    showMenu()
}

fun showMenu() {
    do {
        println("1 - Deposit\n2 - Withdraw\n3 - Check balance\n4 - Transaction history\n5 - Exit")
        print("Choose the choice: ")
        try {
            when (val choice = readln().toInt()) {
                1 -> deposit(choice)
                2 -> withdraw(choice)
                3 -> displayBalance(account)
                4 -> displayTransactions(account)
                5 -> exitProcess(0)
                else -> println("Invalid choice")
            }
        } catch (e: NumberFormatException) {
            println("Invalid input! Please enter a valid integer.")
        }
        println()
    } while (true)
}

fun withdraw(choice: Int) {
    println(TransactionManager.withdraw(userInput(choice), account).message)
}

fun deposit(choice: Int) {
    println(TransactionManager.deposit(userInput(choice), account).message)
}

fun userInput(check: Int): Double {
    when (check) {
        1 -> print("Enter the Deposit amount    :")
        2 -> print("Enter the withdrawal amount :")
    }
    return readln().toDouble()
}

fun displayBalance(target: Account) {
    println("Available Balance: ${target.balance}")
}

fun displayTransactions(target: Account) {
    println("Transaction history for user: ${target.userName}")
    println("Account number: ${target.accountNumber}")
    target.transactions.forEachIndexed { index, transaction ->
        println("${index + 1}. $transaction")
    }
}

data class Account(
    val accountNumber: Int,
    val userName: String,
    var balance: Double,
    val transactions: MutableList<String> = mutableListOf(),
)

data class Status(
    val isSuccess: Boolean,
    val message: String?
)

object TransactionManager {

    fun deposit(money: Double, target: Account): Status {
        if (money > 0) {
            target.balance += money
            target.transactions.add("Amount of Rs.$money credited. Date :${LocalDate.now()} and Time:${LocalTime.now()}")
            return Status(true, "Amount of Rs.$money deposited successfully!")
        } else {
            return Status(false, "The value is negative and precision")
        }
    }

    fun withdraw(money: Double, target: Account): Status {
        if (target.balance >= money) {
            target.balance -= money
            target.transactions.add("Amount of Rs.$money debited. Date :${LocalDate.now()} and Time:${LocalTime.now()}")
            return Status(
                true,
                "Amount of Rs.$money debited successfully!"
            )
        } else {
            return Status(false, "Insufficient balance. Available balance ${target.balance}")
        }
    }

}
