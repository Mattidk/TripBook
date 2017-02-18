package dk.mathiaspedersen.tripbook.data.repository

import dk.mathiaspedersen.tripbook.domain.repository.ExampleRepository


class ExampleRepositoryImpl : ExampleRepository {

    override fun getExample(): String {
        return "Mathias is stupid"
    }
}