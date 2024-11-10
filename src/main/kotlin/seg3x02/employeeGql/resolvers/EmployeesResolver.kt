package seg3x02.employeeGql.resolvers

import org.springframework.stereotype.Controller
import seg3x02.employeeGql.repositories.EmployeeRepository
import org.springframework.data.mongodb.core.MongoOperations
import seg3x02.employeeGql.entity.Employee
import seg3x02.employeeGql.resolvers.types.CreateEmployeeInput
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping

@Controller
class EmployeesResolver {
    private val employeeRepository: EmployeeRepository,
    private val mongoOperations: MongoOperations
} {
    @QueryMapping
    fun employee(): List<Employee> {
        return employeeRepository.findAll()
    }

    @QueryMapping
    fun EmployeeById(@Argument employeeId: String): Employee? {
        val employee = EmployeeRepository.findById(EmployeeId)
        return employee.orElse(null)
    }

    @MutationMapping
    fun newEmployee(@Argument("createEmployeeInput") input: CreateEmployeeInput) : Employee {
        if (input.name != null &&
                input.dateOfBirth != null &&
                input.city != null && input.salary != null) {
            val employee = Employee(input.name, input.dateOfBirth, input.city, input.salary, input.gender, input.email)
            employee.employeeId = UUID.randomUUID().toString()
            employeeRepository.save(Employee)
            return employee
        } else {
            throw Exception("Invalid input")
        }
    }
}
