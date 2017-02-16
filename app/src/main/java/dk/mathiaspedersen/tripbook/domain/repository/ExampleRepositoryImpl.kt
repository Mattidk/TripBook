package dk.mathiaspedersen.tripbook.domain.repository

class ExampleRepositoryImpl : ExampleRepository{

    override fun getExample(): String {
        return "It's working"
    }
}