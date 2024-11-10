package seg3x02.employeeGql

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
class EmployeeGqlApplication

fun main(args: Array<String>) {
	runApplication<EmployeeGqlApplication>(*args)
}
