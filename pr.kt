import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class Expense(
    val amount: Double,
    val category: String,
    val date: LocalDate
) {
    fun displayInfo() {
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        println("Сумма: $amount, Категория: $category, Дата: ${date.format(formatter)}")
    }
}

class ExpenseTracker {
    private val expenses = mutableListOf<Expense>()

    fun addExpense(amount: Double, category: String, date: LocalDate) {
        val expense = Expense(amount, category, date)
        expenses.add(expense)
        println("Расход добавлен: $amount в категории '$category' на дату ${date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))}")
    }

    fun displayAllExpenses() {
        if (expenses.isEmpty()) {
            println("Нет зарегистрированных расходов.")
            return
        }
        println("Список всех расходов:")
        expenses.forEach { it.displayInfo() }
    }

    fun calculateTotalByCategory() {
        val totalByCategory = expenses.groupBy { it.category }
            .mapValues { (_, expenses) -> expenses.sumOf { it.amount } }

        if (totalByCategory.isEmpty()) {
            println("Нет зарегистрированных расходов.")
            return
        }

        println("Сумма расходов по категориям:")
        totalByCategory.forEach { (category, total) ->
            println("Категория: $category, Сумма: $total")
        }
    }
}

fun main() {
    val tracker = ExpenseTracker()

    while (true) {
        println("\nВыберите действие:")
        println("1. Добавить расход")
        println("2. Показать все расходы")
        println("3. Показать сумму расходов по категориям")
        println("4. Выход")

        when (readLine()) {
            "1" -> {
                println("Введите сумму:")
                val amount = readLine()!!.toDouble()
                println("Введите категорию:")
                val category = readLine()!!
                println("Введите дату (дд-ММ-гггг):")
                val dateInput = readLine()!!
                val date = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                tracker.addExpense(amount, category, date)
            }
            "2" -> tracker.displayAllExpenses()
            "3" -> tracker.calculateTotalByCategory()
            "4" -> {
                println("Выход из программы.")
                return
            }
            else -> println("Некорректный ввод, попробуйте снова.")
        }
    }
}
