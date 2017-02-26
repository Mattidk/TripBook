package dk.mathiaspedersen.tripbook.data.repository

import dk.mathiaspedersen.tripbook.domain.repository.ExampleJobRepository


class ExampleJobRepositoryImpl : ExampleJobRepository {

    override fun getExample(): String {
        return "Mathias is stupid"
    }
}