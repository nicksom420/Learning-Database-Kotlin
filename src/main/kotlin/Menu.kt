package org.example

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.example.data.Contact
import org.example.data.contactRepository





fun displayWelcomeMenu()
{
    println("1. Look Up Contact by first name")
    println("2. Add contact")
    println("3. Update Contact")
    println("4. Delete contact")
    println("5. Exit")
}

suspend fun lookUpContact() {

    println("Please enter the first name")
    var firstName = readln()
    val contact = contactRepository.getContactByName(firstName)
    if (contact == null) {
        println("Contact not found.")
    }
    else {

        println(
        "Name: ${contact?.nameFirst}" +
                " ${if (contact?.nameMiddle == null) contact?.nameMiddle else ""}" +
                " ${contact?.nameLast}"
        )
        println("Email: ${contact?.email}")
        println("Phone: ${contact?.phoneNumber}")
        println("Address: ${contact?.address}")
    }
}

suspend fun addContact() {

    println("Enter the first name: ")
    var firstName = readln()
    println("Enter the middle name if they have one: ")
    var middleName = readln()
    println("Enter the last name: ")
    var lastName = readln()
    println("Enter the email: ")
    var email = readln()
    println("Enter the Phone Number: ")
    var phoneNumber = readln()
    println("Enter the address: ")
    var address = readln()

    val newContact = Contact(
        id = null,
        nameFirst = firstName,
        nameMiddle = middleName,
        nameLast = lastName,
        email = email,
        phoneNumber = phoneNumber,
        address = address
    )

    contactRepository.addContact(newContact)


}


suspend fun updateContact()
{
    var contact: Contact? = null
    while (contact == null) {
        println("Enter the name of the contact you want to update? ")
        val contactToLookUp = readln()

        contact = contactRepository.getContactByName(contactToLookUp)

        if (contact == null) {
            println("No contact found")
        }
    }
    val contactId = contact.id!!

    var columnToUpdate = ""
    println("Please enter what you want to update")
    println("1. First Name 2. Middle Name 3. Last Name 4. Email 5. phoneNumber 6. Address")
    var userInput = readln()
    when (userInput) {
        "1" -> columnToUpdate = "first_name"
        "2" -> columnToUpdate = "middle_name"
        "3" -> columnToUpdate = "last_name"
        "4" -> columnToUpdate = "email"
        "5" -> columnToUpdate = "phone_number"
        "6" -> columnToUpdate = "address"
    }

    println("What would you like to change $columnToUpdate to? ")
    val itemToChange = readln()

    contactRepository.updateContact(columnToUpdate, itemToChange,contactId)

    println("Contact updated!")
    println("\n")
}

 suspend fun deleteContact() {

    var contact: Contact? = null
    while (contact == null) {
        println("Enter the name of the contact you want to Delete? ")
        val contactToLookUp = readln()

        contact = contactRepository.getContactByName(contactToLookUp)

        if (contact == null) {
            println("No contact found")
        }
    }
    val contactId = contact.id!!

     var isAuthorized = false

     while(!isAuthorized) {
         println("Are you sure you want to delete ${contact.nameFirst}  ${contact.nameMiddle}  ${contact.nameLast} 's Contact? Yes/No?")
         val userDecision = readln()
         val decision = userDecision.lowercase()
         if (decision == "yes") {
             isAuthorized = true
         }
         else if (decision == "no") {
             return
         }
     }

     contactRepository.deleteContact(contactId)
     println("Contact Deleted!")

}

fun main() {

    var done = false

    while (!done)
    {
        displayWelcomeMenu()
        val selection = readln()

        when (selection) {
            "1" -> runBlocking { lookUpContact() }
            "2" -> runBlocking { addContact() }
            "3" -> runBlocking { updateContact() }
            "4" -> runBlocking { deleteContact() }
            "5" -> done = true
        }
    }
}