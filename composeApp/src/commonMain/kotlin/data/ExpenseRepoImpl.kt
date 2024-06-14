package data

import com.expenseApp.db.AppDatabase
import domain.ExpenseRepository
import model.Expense
import model.ExpenseCategory

class ExpenseRepoImpl(
    private val appDatabase: AppDatabase
) : ExpenseRepository {

    private val queries = appDatabase.expensesDBQueries
    override fun getAllExpenses(): List<Expense> {
        return queries.selectAll().executeAsList().map {
            Expense(
                id = it.id,
                amount = it.amount,
                category = ExpenseCategory.valueOf(it.categoryName),
                description = it.description
            )
        }
        //return expenseManger.fakeExpenseList
    }

    override fun addExpense(expense: Expense) {
        //expenseManger.addNewExpense(expense)
        queries.transaction {
            queries.insert(
                amount = expense.amount,
                categoryName = expense.category.name,
                description = expense.description
            )
        }
    }

    override fun editExpense(expense: Expense) {
        //expenseManger.editExpense(expense)

        queries.transaction {
            queries.update(
                id = expense.id,
                amount = expense.amount,
                categoryName = expense.category.name,
                description = expense.description
            )
        }
    }

    override fun getCategories(): List<ExpenseCategory> {
        return queries.categories().executeAsList().map {
            ExpenseCategory.valueOf(it)
        }
        //return expenseManger.getCategories()
    }

    override fun deleteExpense(expense: Expense): List<Expense> {
        TODO("Not yet implemented")
    }

}